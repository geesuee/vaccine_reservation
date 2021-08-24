package model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import exception.NotExistException;
import model.entity.Hospital;
import util.PublicCommon;

public class HospitalDAO {
	private static HospitalDAO instance = new HospitalDAO();
	
	private HospitalDAO() {};
	
	public static HospitalDAO getInstance() {
		return instance;
	}
	
	//병원 유무
	public void notExistHospital(String hospitalName) throws NotExistException{
		Hospital hos = getHospital(hospitalName);
		if(hos == null) {
			System.out.println("검색한 병원은 존재하지 않습니다");
		}
	}
	
	//모든 병원 검색
	public List<Hospital> getAllHospital(){
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
	public Hospital getHospital(String hospitalName) {
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
	public Hospital getLocation(String location) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Hospital hospital = null;
		tx.begin();
		
		try {
			hospital = (Hospital)em.createNamedQuery("Hospital.findByLocation").setParameter("location", location).getSingleResult();
			
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
	public ArrayList<Hospital> getHospitalVaccine(String vaccineName) {
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
	
	//새로운 병원 저장
	public boolean addHospital(Hospital hospital) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		tx.begin();
		
		try {
			em.persist(hospital);
			
			if(hospital != null) {
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
	
	//병원 이름으로 지역 수정
	public boolean updateLocation(String hospitalName, String location) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		tx.begin();
		
		try {
			Hospital hos = em.find(Hospital.class, hospitalName);
			
			if(hos != null) {
				hos.setLocation(location);
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
	public boolean updateHospitalAllVaccine(String hospitalName, int pfizer, int moderna, int az) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		tx.begin();
		
		try {
			Hospital hos = em.find(Hospital.class, hospitalName);
			
			hos.setPfizer(pfizer);
			hos.setModerna(moderna);
			hos.setAz(az);
			
			if(hos != null) {
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
	public boolean updateHospitalVaccine(String hospitalName, String vaccineName, int num) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		tx.begin();
		
		try {
			Hospital hos = em.find(Hospital.class, hospitalName);
			
			if(hos != null) {
				if(vaccineName.equals("화이자")) {
					hos.setPfizer(num);
				}else if(vaccineName.equals("모더나")) {
					hos.setModerna(num);
				}else if(vaccineName.equals("AZ") | vaccineName.equals("az")) {
					hos.setAz(num);
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
	public boolean deleteHospital(String hospitalName) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		tx.begin();
		
		try {
			Hospital hos = em.find(Hospital.class, hospitalName);
			
			if(hos != null) {
				em.remove(hos);
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
