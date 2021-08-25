package model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import exception.NotExistException;
import model.entity.Hospital;
import model.entity.Vaccine;
import util.PublicCommon;

public class HospitalDAO {
	/**
	 * Hospital DAO 
	 * - notExistHospital
	 * 
	 * - getAllHospital
	 * - getHospital -> getHospitalByName
	 * - getLocation -> getHospitalByLocation
	 * - getHospitalVaccine -> getHospitalByVaccine
	 * 
	 * - addHospital
	 * 
	 * - updateHospitalLocation
	 * - updateHospitalAllVaccine
	 * - updateHospitalVaccine
	 * 
	 * - deleteHospital
	 */
	
	//병원 유무
	//Exception처리를 왜 별도로..?
	public static void notExistHospital(String hospitalName) throws NotExistException{
		Hospital hospital = getHospitalByName(hospitalName);
		if(hospital == null) {
			System.out.println("검색한 병원은 존재하지 않습니다");
		}
	}
	
	//모든 병원 검색
	public static List<Hospital> getAllHospital(){
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Hospital> list = null;
		tx.begin();
		
		try {
			list = em.createQuery("select h from Hospital h").getResultList();
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return list;
	}
	
	//병원 이름으로 병원 검색
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
	
	//지역으로 병원 검색
	public static List<Hospital> getHospitalByLocation(String location) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Hospital> hospital = null;
		tx.begin();
		
		try {
			hospital = em.createNamedQuery("Hospital.findByLocation").setParameter("location", location).getResultList();
			
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
	
	//백신이 있는 병원 검색
	public static ArrayList<Hospital> getHospitalByVaccine(String vaccineName) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		ArrayList<Hospital> list = new ArrayList<>(); 
		tx.begin();
		
		try {
			List<Hospital> all = getAllHospital();
			
			if(vaccineName.equals("화이자")) {
				all.stream().filter(v -> v.getPfizer() > 0).forEach(v -> list.add(v));
			}else if(vaccineName.equals("모더나")) {
				all.stream().filter(v -> v.getModerna() > 0).forEach(v -> list.add(v));
			}else if(vaccineName.equals("AZ") | vaccineName.equals("az")) {
				all.stream().filter(v -> v.getAz() > 0).forEach(v -> list.add(v));
			}
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return list;
	}
	
	//백신이 있는 병원 검색
	public static ArrayList<Hospital> getHospitalByVaccine(List<Vaccine> vaccine) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		ArrayList<Hospital> list = new ArrayList<>(); 
		tx.begin();
		
		try {
			List<Hospital> all = getAllHospital();
			
			vaccine.stream().forEach(v -> {
			if(v.getVaccineName().equals("화이자")) {
				all.stream().filter(h -> h.getPfizer() > 0).forEach(h -> list.add(h));
			}else if(v.getVaccineName().equals("모더나")) {
				all.stream().filter(h -> h.getModerna() > 0).forEach(h -> list.add(h));
			}else if(v.getVaccineName().equals("AZ") | v.getVaccineName().equals("az")) {
				all.stream().filter(h -> h.getAz() > 0).forEach(h -> list.add(h));
			}});
			
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		
		return list;
	}
	
	//새로운 병원 저장
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
	
	
	//병원 이름으로 지역 수정
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
	
	
	//병원 이름으로 모든 백신 수량 수정
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
	
	
	//병원 이름으로 백신 수량 수정
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
	
	//병원 이름으로 병원 삭제
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
