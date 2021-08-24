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
	
	// 1�� �����Ϸ� 2�� ������ ����ؼ� ��ȯ�ϴ� �޼ҵ�
	public static String getNextVaccineDate(String date1, int period) {
		LocalDate dateOne = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate dateTwo = dateOne.plusDays(period);
		
		return dateTwo.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	/**
	 * ���ƴ� ���� ������ ��������
	 */	
	public static Users getUserNextVaccineDate(int idNum) {
		EntityManager em = PublicCommon.getEntityManager();
		Users user = em.find(Users.class, idNum);
		if(user == null) {
			System.out.println("������ �����ϴ�.");
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
	 * ���ƴ� ���� ������ ���
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
			}else if("ȭ����".equals(VaccineName)) {
				day = 42;
			}else if("�����".equals(VaccineName)) {
				day = 42;
			}else {
				System.out.println("�߸��� �Է�");
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
				
				//1�� ������ ����
				if(dateNum == 1) {
					if(date1.isAfter(todaysDate) && newDate.isAfter(date1) && newDate.isAfter(todaysDate)) {
						user.setDate1(newDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
						user.setDate2(getNextVaccineDate(user.getDate1(), user.getVaccine().getPeriod()));
						
						tx.commit();
						return true;
					}else {  // 1�� ���� ��¥�� �̹� �����ų�, ���ο� ��¥�� ���ú��� ������ ���
						return false;
					}

				//2�� ������ ����
				}else if(dateNum == 2) {
					LocalDate maxDate = date1.plusMonths(3);
					
					if(date2.isAfter(todaysDate) && newDate.isAfter(date2) && newDate.isAfter(todaysDate) && newDate.isBefore(maxDate)) {
						user.setDate2(newDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
						
						tx.commit();
						return true;
					}else {  // 2�� ���� ��¥�� �̹� �����ų�,  ���� �����Ϻ��� ���̰ų�, ���ο� ��¥�� ���ú��� �����̰ų�, 1�� �������κ��� 3�� �Ѱ� ���� ���� ���
						return false;  
					}
					
				}else {  // ��¥ ���� ���� �� �� ���� ���
					return false;
				}
				
			}else {  // idNum���� ã�� user�� null�� ���
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
