package model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entity.Vaccine;
import util.PublicCommon;

public class VaccineDAO {

	/**
	 * VaccineDAO 
	 * - getAllVaccine 
	 * - getVaccine
	 * - getVaccineByTargetAge
	 * 
	 * - updateVaccine 
	 * - updateVaccineTargetAge
	 */

	public static List<Vaccine> getAllVaccine() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Vaccine> vaccineList = new ArrayList<>();

		try {
			vaccineList = em.createQuery("select v from Vaccine v").getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return vaccineList;
	}


	public static Vaccine getVaccine(String vaccinName) {
		EntityManager em = PublicCommon.getEntityManager();
		Vaccine vaccine = null;
		
		try {
			vaccine = (Vaccine) em.createNamedQuery("Vaccine.findByVaccineName")
					.setParameter("name", vaccinName).getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return vaccine;
	}

	
	public static List<Vaccine> getVaccineByTargetAge(int targetAge) {
		EntityManager em = PublicCommon.getEntityManager();
		List<Vaccine> vaccineList = new ArrayList<>();
		
		try {
			vaccineList = em.createNamedQuery("Vaccine.findByVaccineTargetAge")
					.setParameter("target_age", targetAge).getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return vaccineList;
	}


	public static boolean updateVaccine(String vaccineName, String platform, String temper) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;

		tx.begin();
		
		try {
			Vaccine curVaccine = em.find(Vaccine.class, vaccineName);
			if (curVaccine != null) {

				curVaccine.setPlatform(platform);
				curVaccine.setTemperature(platform);
				result = true;

				tx.commit();
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}

		return result;
	}


	public static boolean updateVaccineTargetAge(String vaccineName, int age) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;

		tx.begin();
		
		try {
			Vaccine curVaccine = em.find(Vaccine.class, vaccineName);

			if (curVaccine != null) {
				curVaccine.setTargetAge(age);
				result = true;
				
				tx.commit();
			}

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}

		return result;

	}


	
//	public static boolean addVaccine(Vaccine vaccine) {
//		EntityManager em = PublicCommon.getEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		boolean result = false;
//		
//		tx.begin();
//		
//		try {
//			em.persist(vaccine);
//
//			tx.commit();
//			result = true;
//		} catch (Exception e) {
//			tx.rollback();
//			e.printStackTrace();
//		} finally {
//			em.close();
//			em = null;
//		}
//		
//		return result;
//	}
	
	
//	public static boolean deleteVaccine(String vaccineName) {
//		EntityManager em = PublicCommon.getEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		boolean result = false;
//
//		tx.begin();
//		try {
//			Vaccine curVaccine = em.find(Vaccine.class, vaccineName);
//
//			if (curVaccine != null) {
//				em.remove(curVaccine);
//				result = true;
//			}
//
//			tx.commit();
//		} catch (Exception e) {
//			tx.rollback();
//			e.printStackTrace();
//		} finally {
//			em.close();
//			em = null;
//		}
//		
//		return result;
//	}

}
