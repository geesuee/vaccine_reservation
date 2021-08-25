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
	 * 
	 * - getAllHospital
	 * - getHospitalByName
	 * - getHospitalByLocation
	 * - getHospitalByVaccine
	 * - getHospitalVaccine
	 * 
	 * - addHospital
	 * 
	 * - updateHospitalLocation
	 * - updateHospitalAllVaccine
	 * - updateHospitalVaccine
	 * 
	 * - deleteHospital
	 */
	
	// 병원 정보 다중(전체) 조회
	public static List<Hospital> getAllHospital(){
		EntityManager em = PublicCommon.getEntityManager();
		List<Hospital> hospitalList = new ArrayList<>();
		
		try {
			hospitalList = em.createQuery("select h from Hospital h").getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return hospitalList;
	}
	
	// 병원 정보 단일 조회 - 병원 이름
	public static Hospital getHospitalByName(String hospitalName) {
		EntityManager em = PublicCommon.getEntityManager();
		Hospital hospital = null;
		
		try {
			hospital = em.find(Hospital.class, hospitalName);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return hospital;
	}
	
	// 병원 정보 다중 조회 - 병원 위치
	public static List<Hospital> getHospitalByLocation(String location) {
		EntityManager em = PublicCommon.getEntityManager();
		List<Hospital> hospitalList = new ArrayList<>();
		
		try {
			hospitalList = em.createNamedQuery("Hospital.findByLocation").setParameter("location", location).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return hospitalList;
	}
	
	// 병원 정보 다중 조회 - 보유 백신
	public static ArrayList<Hospital> getHospitalByVaccine(List<Vaccine> vaccine) {
		EntityManager em = PublicCommon.getEntityManager();
		ArrayList<Hospital> hospitalList = new ArrayList<>(); 
		
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
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return hospitalList;
	}
	
	// 병원 보유 백신 개수 조회
	public static int getHospitalVaccine(String hospitalName, String vaccineName) {
		EntityManager em = PublicCommon.getEntityManager();
		int vaccineCount = -1;
		
		try {
			Hospital hospital = em.find(Hospital.class, hospitalName);
			if(vaccineName == "화이자") {
				vaccineCount = hospital.getPfizer();
			}else if(vaccineName == "모더나") {
				vaccineCount = hospital.getModerna();
			}else if(vaccineName == "AZ") {
				vaccineCount = hospital.getAz();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return vaccineCount;
	}

	// 병원 추가
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

	// 병원 위치 수정
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

	// 병원 보유 백신 개수 다중(전체) 수정
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
	
	// 병원 보유 백신 개수 단일 수정
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
	
	// 병원 삭제
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
