package model.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import model.entity.Vaccine;
import util.PublicCommon;
import view.EndView;

public class VaccineDAO {
	/*
	 * VaccineDAO 
	 * -addVaccine 
	 * -getAllVaccine
	 * -getVaccine 
	 * -findByVaccineName
	 * -findByVaccineTagetAge
	 * -updateVaccine 
	 * -updateVaccineAge
	 *-deleteVaccine
	 */
	public static Vaccine addVaccine(Vaccine vaccine) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		if (vaccine != null) {
			em.persist(vaccine);

			tx.commit();
			em.close();
			em = null;

			return vaccine;
		} else {
			tx.rollback();

			em.close();
			em = null;
			return null;
		}
	}

	public static List<Vaccine> getAllVaccine() {

		EntityManager em = PublicCommon.getEntityManager();
		String query = "select v from Vaccine v";
		List<Vaccine> vaccines = em.createQuery(query).getResultList();

		if (vaccines.size() >0 ) {
			em.close();
			em = null;
			return vaccines;
			
		} else {
			// 예외 발생 백신이 없는 상황
			System.out.println("백신이 존재하지 않습니다.");
			em.close();
			em = null;
			return null;
		}
	}

	public static Vaccine getVaccine(String vaccinName) {
		if (vaccinName != null && !vaccinName.equals("")) {
			EntityManager em = PublicCommon.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			Vaccine vaccine = null;
			tx.begin();

			try {
				vaccine = em.find(Vaccine.class, vaccinName);

				if (vaccine == null) {
					System.out.println("백신이 존재하지 않습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				em.close();
				em = null;
			}

			return vaccine;
		} else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
			return null;
		}

	}

	public static Vaccine findByVaccineName(String vaccinName) {
		if (vaccinName != null && !vaccinName.equals("")) {
			EntityManager em = PublicCommon.getEntityManager();
			try {
				Vaccine vaccine = (Vaccine) em.createNamedQuery("Vaccine.findByVaccineName")
						.setParameter("name", vaccinName).getSingleResult();
				em.close();
				em = null;
				return vaccine;
			} catch (Exception e) {
				System.out.println("백신 정보가 존재하지 않습니다.");
				e.printStackTrace();
			}
		} else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
			return null;
		}
		return null;
	}

	public static List<Vaccine> findByVaccineTagetAge(int targetAge) {
		if ((Integer) targetAge != null) {
			EntityManager em = PublicCommon.getEntityManager();
			try {
				List<Vaccine> vaccines = em.createNamedQuery("Vaccine.findByVaccineTargetAge")
						.setParameter("target_age", targetAge).getResultList();
				em.close();
				em = null;
				return vaccines;
			} catch (Exception e) {
				System.out.println("해당 연령의 백신 정보가 존재하지 않습니다.");
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void updateVaccine(Vaccine updateVaccine) {
		if (updateVaccine != null) {
			EntityManager em = PublicCommon.getEntityManager();
			EntityTransaction tx = em.getTransaction();

			tx.begin();
			Vaccine curVaccine = em.find(Vaccine.class, updateVaccine.getVaccineName());
			if (curVaccine != null) {

				curVaccine.setVaccineName(updateVaccine.getVaccineName());
				curVaccine.setTargetAge(updateVaccine.getTargetAge());
				curVaccine.setPeriod(updateVaccine.getPeriod());
				curVaccine.setPlatform(updateVaccine.getPlatform());
				curVaccine.setTemperature(updateVaccine.getTemperature());
				curVaccine.setStorage(updateVaccine.getStorage());

				em.persist(curVaccine);
				tx.commit();
				em.close();
				em = null;

			} else {

				System.out.println("백신 정보가 존재하지 않아 수정이 불가능 합니다.");
				tx.commit();
				em.close();
				em = null;
			}
		} else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
		}

	}

	public static boolean updateVaccineAge(String vaccineName, int age) {
		if (vaccineName != null && !vaccineName.equals("")) {

			EntityManager em = PublicCommon.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			boolean result = false;

			tx.begin();
			try {
				Vaccine curVaccine = em.find(Vaccine.class, vaccineName);

				if (curVaccine != null) {
					curVaccine.setTargetAge(age);
					result = true;
				}

				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				em.close();
				em = null;
			}

			return result;
		} else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
			return false;
		}
	}

	public static void deleteVaccine(String vaccineName) {
		if (vaccineName != null && !vaccineName.equals("")) {
			EntityManager em = PublicCommon.getEntityManager();
			EntityTransaction tx = em.getTransaction();

			tx.begin();
			try {
				Vaccine curVaccine = em.find(Vaccine.class, vaccineName);

				if (curVaccine != null) {
					em.remove(curVaccine);
				}

				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			} finally {
				em.close();
				em = null;
			}
		}else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
		}
	}

}
