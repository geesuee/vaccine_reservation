package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.HospitalDAO;
import model.entity.Hospital;
import view.EndView;

public class HospitalController {

	private static HospitalController instance = new HospitalController();

	private HospitalController() {};

	public static HospitalController getInstance() {
		return instance;
	}
	
	/**
	 * Hospital Controller
	 * - getAllHospital
	 * - getHospital -> getHospitalByName
	 * - getHospitalLocation -> getHospitalByLocation
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
	
	//모든 병원 검색
	public static void getAllHospital(){
		List<Hospital> hospitalList = HospitalDAO.getAllHospital();
		
		if(hospitalList.size() > 0) {
			EndView.hospitalAllView(hospitalList);
		}else {
			EndView.errorMessage("병원 정보가 없습니다.");
		}
	}
	
	
	//병원 이름으로 병원 검색
	public static void getHospitalByName(String hospitalName) {
		if(hospitalName != null && !hospitalName.equals("")) {
			Hospital hospital = HospitalDAO.getHospitalByName(hospitalName);
			
			if(hospital != null) {
				EndView.hospitalView(hospital);
			}else {
				EndView.errorMessage("해당 이름의 병원 정보가 없습니다.");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다.");	
			
		}
	}
	
	
	//지역으로 병원 검색
	public static void getHospitalByLocation(String location) {
		if(location != null && !location.equals("")) {
			Hospital hospital = HospitalDAO.getHospitalByLocation(location);
			
			if(hospital != null) {
				EndView.hospitalView(hospital);
			}else {
				EndView.errorMessage("해당 지역에 위치한 병원이 없습니다.");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다.");
		}
	}
	
	
	//백신이 있는 병원 검색
	public static void getHospitalByVaccine(String vaccineName) {
		if(vaccineName != null && !vaccineName.equals("")) {
			ArrayList<Hospital> hospital = HospitalDAO.getHospitalByVaccine(vaccineName);
			
			if(hospital.size() > 0) {
				EndView.hospitalAllView(hospital);
			}else {
				EndView.errorMessage("해당 백신을 보유한 병원이 없습니다.");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다.");
		}
	}
	
	/**
	 * 방식 통일 고려
	 */
	//새로운 병원 저장
	public static void addHospital(Hospital hospital) {
		if(hospital != null) {
			if(HospitalDAO.addHospital(hospital)) {
				EndView.insertHospitalView(true, hospital.getHospitalName());
			}else {
				EndView.errorMessage("새로운 병원 추가를 실패하였습니다.");
			}
		}else {
			EndView.errorMessage("유효하지 않은 병원 정보입니다.");
		}
	}
	
	/**
	 * 방식 통일 고려?
	 */
	//병원 이름으로 모든 병원 정보 수정
	public static void updateHospital(String hospitalName, String location, int pfizer, int moderna, int az) {
		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
			boolean result1 = HospitalDAO.updateHospitalLocation(hospitalName, location);
			boolean result2 = HospitalDAO.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
			
			if(result1 == true && result2 == true) {
				EndView.updateLocationView(true, hospitalName);
			}else {
				EndView.errorMessage("수정할 병원과 정보를 다시 한번 확인해 주세요");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다.");
		}
	}
	
	
	//병원 이름으로 지역 수정
	public static void updateHospitalLocation(String hospitalName, String location) {
		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
			if(HospitalDAO.updateHospitalLocation(hospitalName, location)) {
				EndView.updateLocationView(true, hospitalName);
			}else {
				EndView.errorMessage("병원 지역 수정을 실패하였습니다.");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다.");
		}
	}
	
	
	//병원 이름으로 모든 백신 수량 수정
	public static void updateHospitalAllVaccine(String hospitalName, int pfizer, int moderna, int az) {
		if(hospitalName != null && !hospitalName.equals("")) {
			if(HospitalDAO.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az)) {
				EndView.updateVaccineView(true, hospitalName);
			}else {
				EndView.errorMessage(hospitalName + " 내 전체 백신 수량 수정을 실패하였습니다.");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다.");
		}
	}
	
	
	//병원 이름으로 백신 수량 수정
	public static void updateHospitalVaccine(String hospitalName, String vaccineName, int num) {
		if(hospitalName != null && !hospitalName.equals("") && vaccineName != null && !vaccineName.equals("")) {
			if(HospitalDAO.updateHospitalVaccine(hospitalName, vaccineName, num)) {
				EndView.updateVaccineView(true, hospitalName);
			}else {
				EndView.errorMessage(hospitalName + " 내 " + vaccineName + " 백신 수량 수정을 실패하였습니다.");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다.");
		}
	}
	
	
	//병원 이름으로 병원 삭제
	public static void deleteHospital(String hospitalName) {
		if(hospitalName != null && !hospitalName.equals("")) {
			if(HospitalDAO.deleteHospital(hospitalName)) {
				EndView.deleteSellerView(true, hospitalName);
			}else {
				EndView.errorMessage("병원 정보 삭제를 실패하였습니다.");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다.");
		}
	}

}
