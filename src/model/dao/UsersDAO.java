package model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entity.Users;
import util.PublicCommon;

public class UsersDAO {
	
	/** 
	 * UsersDAO
	 * 
	 * - getUserNextVaccineDate
	 * - (private) nextVaccineDate
	 * 
	 * - getAllUsers
	 * - getAllUsersByHospital
	 * 
	 * - getUser
	 * - (private)getUserById
	 * 
	 * - addUser
	 * 
	 * - updateUserDate
	 * - calNextVaccineDate
	 * - updateUserAddress
	 * 
	 * - deleteUser
	 */
	

	// 접종 예약자 별 접종 예약일 조회1
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

	// 접종 예약자 별 접종 예약일 조회2 (위 메소드와 연결)
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
	
	// 접종 예약자 정보 다중(전체) 조회
	public static List<Users> getAllUsers() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Users> userList = new ArrayList<>();
		
		try {
			userList = em.createQuery("select u from Users u").getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return userList;
	}
	
	// 병원 별 접종 예약자 정보 다중 조회
	public static List<Users> getAllUsersByHospital(String hospitalName) {
		EntityManager em = PublicCommon.getEntityManager();
		List<Users> userList = new ArrayList<>();
		
		try {
			userList = em.createQuery("select u from Users u where hospital_name = :hospital").setParameter("hospital", hospitalName).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return userList;
	}
	
	// 접종 예약자 정보 단일 조회1
	public static Users getUser(String name, int idNum) {
		EntityManager em = PublicCommon.getEntityManager();
		List<Users> userList = new ArrayList<>();
		
		try {
			userList = em.createNamedQuery("Users.findByUserName").setParameter("name", name).getResultList();
			
			if(userList.size() == 1) {
				return userList.get(0);
			} else {
				return getUserById(idNum);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return null;
	}

	// 접종 예약자 정보 단일 조회2 (위 메소드와 연결)
	private static Users getUserById(int idNum) {
		EntityManager em = PublicCommon.getEntityManager();
		Users user = null;
		
		try {
			user = (Users) em.createNamedQuery("Users.findByIdNum").setParameter("id", idNum).getSingleResult();

			if(user != null) {
				return user;
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return null;
	}

	// 접종 예약자 추가
	public static boolean addUser(Users user) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();

		try {
			em.persist(user);
			
			tx.commit();
			result = true;
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}

	// 접종 날짜 수정
	public static boolean updateUserDate(int idNum, int dateNum, String date) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
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
						user.setDate2(calNextVaccineDate(user.getDate1(), user.getVaccine().getPeriod()));

						tx.commit();
						result = true;
					}

				//2차 접종일 변경
				}else if(dateNum == 2) {
					LocalDate maxDate = date1.plusMonths(3);

					if(date2.isAfter(todaysDate) && newDate.isAfter(date2) && newDate.isAfter(todaysDate) && newDate.isBefore(maxDate)) {
						user.setDate2(newDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

						tx.commit();
						result = true;
					}
				}
			}

		}catch (Exception e) {
			tx.rollback();
			e.getStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}

	// 백신 종류별 2차 접종일 연산 및 반환
	public static String calNextVaccineDate(String date1, int period) {
		String nextVaccineDate = null;
		
		LocalDate todaysDate = LocalDate.now();
		LocalDate dateOne = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate dateTwo = dateOne.plusDays(period);
		
		if(dateOne.isAfter(todaysDate)) {
			nextVaccineDate = dateTwo.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		}
		
		return nextVaccineDate;
	}

	// 접종 예약자 주소 수정
	public static boolean updateUserAddress(int idNum, String address) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();

		try {
			Users user = (Users) em.createNamedQuery("Users.findByIdNum").setParameter("id", idNum).getSingleResult();
			
			if(user != null) {
				user.setUserAddress(address);
				result = true;
			}
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.getStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}
	
	// 접종 예약자 삭제
	public static boolean deleteUser(int idNum) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();

		try {
			Users user = (Users) em.createNamedQuery("Users.findByIdNum").setParameter("id", idNum).getSingleResult();
			
			if(user != null) {
				em.remove(user);
				result = true;
			}

			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.getStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}
}
