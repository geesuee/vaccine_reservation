package model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entity.Hospital;
import model.entity.Vaccine;
import util.PublicCommon;

public class HospitalDAO {
	
	/**
	 * Hospital DAO 
	 * - getAllHospital
	 * - getHospitalByName
	 * - getHospitalByLocation
	 * - getHospitalByVaccine
	 * - getHospitalByVaccineName
	 * - getHospitalVaccineTotal
	 * - addHospital
	 * - updateHospitalLocation
	 * - updateHospitalAllVaccine
	 * - updateHospitalVaccine
	 * - deleteHospital
	 */
	
	public static List<Hospital> getAllHospital(){
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Hospital> hospitalList = new ArrayList<>();
		
		tx.begin();
		
		try {
			hospitalList = em.createQuery("select h from Hospital h").getResultList();
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return hospitalList;
	}
	
	
	public static Hospital getHospitalByName(String hospitalName) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Hospital hospital = null;
		
		tx.begin();
		
		try {
			hospital = em.find(Hospital.class, hospitalName);
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return hospital;
	}
	

	public static List<Hospital> getHospitalByLocation(String location) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Hospital> hospitalList = new ArrayList<>();
		
		tx.begin();
		
		try {
			hospitalList = em.createNamedQuery("Hospital.findByLocation").setParameter("location", location).getResultList();
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return hospitalList;
	}
	
	
	public static ArrayList<Hospital> getHospitalByVaccineName(String vaccineName) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		ArrayList<Hospital> hospitalList = new ArrayList<>(); 
		
		tx.begin();
		
		try {
			List<Hospital> all = getAllHospital();
			
			if(vaccineName.equals("화이자")) {
				all.stream().filter(v -> v.getPfizer() > 0).forEach(v -> hospitalList.add(v));
			}else if(vaccineName.equals("모더나")) {
				all.stream().filter(v -> v.getModerna() > 0).forEach(v -> hospitalList.add(v));
			}else if(vaccineName.equals("AZ") | vaccineName.equals("az")) {
				all.stream().filter(v -> v.getAz() > 0).forEach(v -> hospitalList.add(v));
			}
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return hospitalList;
	}
	

	public static ArrayList<Hospital> getHospitalByVaccine(List<Vaccine> vaccine) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		ArrayList<Hospital> hospitalList = new ArrayList<>(); 
		
		tx.begin();
		
		try {
			List<Hospital> all = getAllHospital();
			
			vaccine.stream().forEach(v -> {
			if(v.getVaccineName().equals("화이자")) {
				all.stream().filter(h -> h.getPfizer() > 0).forEach(h -> hospitalList.add(h));
			}else if(v.getVaccineName().equals("모더나")) {
				all.stream().filter(h -> h.getModerna() > 0).forEach(h -> hospitalList.add(h));
			}else if(v.getVaccineName().equals("AZ") | v.getVaccineName().equals("az")) {
				all.stream().filter(h -> h.getAz() > 0).forEach(h -> hospitalList.add(h));
			}});
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return hospitalList;
	}
	

	public static int getHospitalVaccineTotal(String hospitalName, String vaccineName) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		int result = 0;
		
		tx.begin();
		
		try {
			Hospital hospital = em.find(Hospital.class, hospitalName);
			
			if(vaccineName.equals("화이자")) {
				result = hospital.getPfizer();
			}else if(vaccineName.equals("모더나")) {
				result = hospital.getModerna();
			}else if(vaccineName.equals("AZ") | vaccineName.equals("az")) {
				result = hospital.getAz();
			}
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}
	
	
	public static boolean addHospital(Hospital hospital) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			em.persist(hospital);
			
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
	
	
	public static boolean updateHospitalLocation(String hospitalName, String location) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			Hospital hospital = em.find(Hospital.class, hospitalName);
			
			if(hospital != null) {
				hospital.setLocation(location);
				result = true;
			}
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}
	
	

	public static boolean updateHospitalAllVaccine(String hospitalName, int pfizer, int moderna, int az) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			Hospital hospital = em.find(Hospital.class, hospitalName);
			
			if(hospital != null) {
				hospital.setPfizer(pfizer);
				hospital.setModerna(moderna);
				hospital.setAz(az);
				result = true;
			}
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}
	
	
	public static boolean updateHospitalVaccine(String hospitalName, String vaccineName, int num) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			Hospital hospital = em.find(Hospital.class, hospitalName);
			
			if(hospital != null) {
				if(vaccineName.equals("화이자")) {
					hospital.setPfizer(num);
				}else if(vaccineName.equals("모더나")) {
					hospital.setModerna(num);
				}else if(vaccineName.equals("AZ") | vaccineName.equals("az")) {
					hospital.setAz(num);
				}
				
				result = true;
			}
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}
	

	public static boolean deleteHospital(String hospitalName) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		
		tx.begin();
		
		try {
			Hospital hospital = em.find(Hospital.class, hospitalName);
			
			if(hospital != null) {
				em.remove(hospital);
				result = true;
			}
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return result;
	}
}
