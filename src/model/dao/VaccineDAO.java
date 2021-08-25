package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.entity.Vaccine;
import util.PublicCommon;
import view.EndView;

public class VaccineDAO {

	/**
	 * VaccineDAO - getAllVaccine - ??중복?? getVaccine - findByVaccineName ->
	 * getVaccineByName - findByVaccineTagetAge -> getVaccineByTargetAge
	 * 
	 * - addVaccine
	 * 
	 * - updateVaccine - updateVaccineAge -> updateVaccineTargetAge
	 * 
	 * - deleteVaccine
	 */

	public static List<Vaccine> getAllVaccine() {

		EntityManager em = PublicCommon.getEntityManager();
		String query = "select v from Vaccine v";
		List<Vaccine> vaccines = em.createQuery(query).getResultList();

		if (vaccines.size() > 0) {
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

	// 입력값 null 여부는 controller에서 체크하고 들어오지 않나용!?
	public static Vaccine getVaccineByName(String vaccinName) {
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
		return null;
	}

	// 바로 위 메소드와 동일한 null 처리 방식!
	public static List<Vaccine> getVaccineByTargetAge(int targetAge) {
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
			return null;
		}
	}

	// 추가 시 boolean 반환? 혹은 해당 객체 반환? 통일 필요!
	public static boolean addVaccine(Vaccine vaccine) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		try {
			tx.begin();
			em.persist(vaccine);
			tx.commit();
			result = true;
		} catch (Exception e) {
			tx.rollback();
			result = false;
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

	// 여기도 내부에서 null 처리를 하시는군용! //백신으로 들어오기 때문에 null처리..
	public static boolean updateVaccine(String vaccineName, String platform, String temper) {
		boolean result = false;
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Vaccine curVaccine = em.find(Vaccine.class, vaccineName);
			if (curVaccine != null) {

				curVaccine.setPlatform(platform);
				curVaccine.setTemperature(platform);

				tx.commit();
				result = true;

			} else {
				System.out.println("백신 정보가 존재하지 않아 수정이 불가능 합니다.");
				tx.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			em = null;
		}

		return result;
	}

	// null처리 동일
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

	}

	// boolean으로 바꿔서 성공 여부를 return해야할 것 같아요! //수정
	public static boolean deleteVaccine(String vaccineName) {
		boolean result = false;
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			Vaccine curVaccine = em.find(Vaccine.class, vaccineName);

			if (curVaccine != null) {
				em.remove(curVaccine);
			}

			tx.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			result = false;
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

}
