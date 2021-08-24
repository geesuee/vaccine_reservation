package model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.entity.Users;
import util.PublicCommon;

public class UsersDAO {
	
	// 1차 접종일로 2차 접종일 계산해서 반환하는 메소드
	public static String getNextVaccineDate(String date1, int period) {
		LocalDate dateOne = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate dateTwo = dateOne.plusDays(period);

		return dateTwo.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

	/**
	 * 영훈님 다음 접종일 가져오기
	 */	
	public static Users getUserNextVaccineDate(int idNum) {
		EntityManager em = PublicCommon.getEntityManager();
		Users user = em.find(Users.class, idNum);
		if(user == null) {
			System.out.println("정보가 없습니다.");
			em.close();
			em = null;

			return null;
		}else {
			try {
				user = nextVaccineDate(user);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			em.close();
			em = null;
			return user;
		}
	}

	/**
	 * 영훈님 다음 접종일 계산
	 */	
	private static Users nextVaccineDate(Users user) throws ParseException {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
		String userName = user.getUserName();
		String VaccineName = user.getVaccine().getVaccineName();
		String date1 = user.getDate1();
		String date2 = user.getDate2();

		if(date2 != null || !date2.equals("")) {
			return user;
		}else {
			Calendar cal = Calendar.getInstance();
			Date dt = dtFormat.parse(date1);
			cal.setTime(dt);
			int day = 0;
			if("az".equals(VaccineName) || "AZ".equals(VaccineName)) {
				day = 84;
			}else if("화이자".equals(VaccineName)) {
				day = 42;
			}else if("모더나".equals(VaccineName)) {
				day = 42;
			}else {
				System.out.println("잘못된 입력");
			}
			cal.add(Calendar.DATE,day );
			user.setDate2(dtFormat.format(cal.getTime()));
			return user;
		}
	}

	/** 
	 * UserDAO
	 * - getUser
	 * - (private) getUserById
	 * - deleteUser
	 * - updateUserDate
	 * - updateUserAdd
	 */
	public static Users getUser(String name, int idNum) {
		EntityManager em = PublicCommon.getEntityManager();

		List<Users> userList = em.createNamedQuery("Users.findByUserName").setParameter("name", name).getResultList();

		em.close();
		em = null;

		if(userList.size() == 1) {
			return userList.get(0);
		} else {
			return getUserById(idNum);
		}
	}


	private static Users getUserById(int idNum) {
		EntityManager em = PublicCommon.getEntityManager();

		Users user = (Users) em.createNamedQuery("Users.findByIdNum").setParameter("id", idNum).getSingleResult();

		em.close();
		em = null;

		if(user != null) {
			return user;
		} 
		return null;
	}


	public static boolean createUser(Users user) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {
			em.persist(user);
			tx.commit();
			return true;
		}catch(Exception e) {
			tx.rollback();
			e.getStackTrace();
		}finally {
			em.close();
			em = null;
		}
		return false;
	}


	public static boolean deleteUser(int idNum) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {
			Users user = (Users) em.createNamedQuery("Users.findByIdNum").setParameter("id", idNum).getSingleResult();
			em.remove(user);

			tx.commit();
			return true;
		}catch(Exception e) {
			tx.rollback();
			e.getStackTrace();
		}finally {
			em.close();
			em = null;
		}
		return false;
	}


	public static boolean updateUserDate(int idNum, int dateNum, String date) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {
			Users user = (Users) em.createNamedQuery("Users.findByIdNum").setParameter("id", idNum).getSingleResult();
			LocalDate todaysDate = LocalDate.now();
			LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);

			if(user != null) {
				LocalDate date1 = LocalDate.parse(user.getDate1(), DateTimeFormatter.BASIC_ISO_DATE);
				LocalDate date2 = LocalDate.parse(user.getDate2(), DateTimeFormatter.BASIC_ISO_DATE);

				//1차 접종일 변경
				if(dateNum == 1) {
					if(date1.isAfter(todaysDate) && newDate.isAfter(date1) && newDate.isAfter(todaysDate)) {
						user.setDate1(newDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
						user.setDate2(getNextVaccineDate(user.getDate1(), user.getVaccine().getPeriod()));

						tx.commit();
						return true;
					}else {  // 1차 접종 날짜가 이미 지났거나, 새로운 날짜가 오늘보다 이전일 경우
						return false;
					}

				//2차 접종일 변경
				}else if(dateNum == 2) {
					LocalDate maxDate = date1.plusMonths(3);

					if(date2.isAfter(todaysDate) && newDate.isAfter(date2) && newDate.isAfter(todaysDate) && newDate.isBefore(maxDate)) {
						user.setDate2(newDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

						tx.commit();
						return true;
					}else {  // 2차 접종 날짜가 이미 지났거나,  기존 예약일보다 전이거나, 새로운 날짜가 오늘보다 이전이거나, 1차 접종으로부터 3달 넘게 지난 날일 경우
						return false;  
					}

				}else {  // 날짜 선택 값을 잘 못 넣은 경우
					return false;
				}

			}else {  // idNum으로 찾은 user가 null일 경우
				return false;
			}

		}catch (Exception e) {
			tx.rollback();
			e.getStackTrace();
		}finally {
			em.close();
			em = null;
		}
		return false;
	}


	public static boolean updateUserAddress(int idNum, String address) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {
			Users user = (Users) em.createNamedQuery("Users.findByIdNum").setParameter("id", idNum).getSingleResult();
			user.setUserAddress(address);

			tx.commit();
			return true;
		}catch(Exception e) {
			tx.rollback();
			e.getStackTrace();
		}finally {
			em.close();
			em = null;
		}
		return false;
	}
}
