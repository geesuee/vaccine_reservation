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
	 * - getHospital
	 * - getHospitalLocation
	 * - getHospitalVaccine
	 * - addHospital
	 * - updateHospital
	 * - updateHospitalLocation
	 * - updateHospitalAllVaccine
	 * - updateHospitalVaccine
	 * - deleteHospital
	 */
	
	//모든 병원 검색
	public static void getAllHospital(){
		List<Hospital> hos = HospitalDAO.getAllHospital();
		
		if(hos.size() > 0) {
			EndView.showAll(hos);
		}else {
			EndView.errorMessage("병원이 하나도 없습니다");
		}
	}
	
	//병원 이름으로 병원 검색
	public void getHospital(String hospitalName) {
		if(hospitalName != null && !hospitalName.equals("")) {
			Hospital hos = HospitalDAO.getHospitalByName(hospitalName);
			
			if(hos != null) {
				EndView.showOne(hos);
			}else {
				EndView.errorMessage("검색한 이름의 병원은 존재하지 않습니다");
			}
		}else {
			EndView.nullMessage();	
			
		}
	}
	
	//지역으로 병원 검색
	public static void getHospitalLocation(String location) {
		if(location != null && !location.equals("")) {
			Hospital hos = HospitalDAO.getHospitalByLocation(location);
			
			if(hos != null) {
				EndView.showOne(hos);
			}else {
				EndView.errorMessage("검색한 지역에 있는 병원이 없습니다");
			}
		}else {
			EndView.nullMessage();
		}
	}
	
	//백신이 있는 병원 검색
	public void getHospitalVaccine(String vaccineName) {
		if(vaccineName != null && !vaccineName.equals("")) {
			ArrayList<Hospital> hos = HospitalDAO.getHospitalByVaccine(vaccineName);
			
			if(hos.size() > 0) {
				EndView.showAll(hos);
			}else {
				EndView.errorMessage("해당하는 백신이 있는 병원이 하나도 없습니다");
			}
		}else {
			EndView.nullMessage();
		}
	}
	
	//새로운 병원 저장
	public void addHospital(Hospital hospital) {
		if(hospital != null) {
			boolean result = HospitalDAO.addHospital(hospital);
			
			if(result == true) {
				EndView.successMessage("병원 : " + hospital.getHospitalName() + "] 저장");
			}else {
				EndView.failMessage("병원 : " + hospital.getHospitalName() + "] 저장");
			}
		}else {
			EndView.errorMessage("해당 병원은 존재하지 않습니다");  // 보류
		}
	}
	
	//병원 이름으로 모든 병원 정보 수정
	public void updateHospital(String hospitalName, String location, int pfizer, int moderna, int az) {
		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
			boolean result1 = HospitalDAO.updateHospitalLocation(hospitalName, location);
			boolean result2 = HospitalDAO.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
			
			if(result1 == true && result2 == true) {
				EndView.successMessage("병원 : " + hospitalName + "] 정보 수정");
			}else {
				EndView.failMessage("병원 : " + hospitalName + "] 정보 수정");
			}
		}else {
			EndView.nullMessage();
		}
	}
	
	//병원 이름으로 지역 수정
	public void updateHospitalLocation(String hospitalName, String location) {
		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
			boolean result = HospitalDAO.updateHospitalLocation(hospitalName, location);
			
			if(result == true) {
				EndView.successMessage("병원 : " + hospitalName + "] 지역 정보 수정");
			}else {
				EndView.failMessage("병원 : " + hospitalName + "] 지역 정보 수정");
			}
		}else {
			EndView.nullMessage();
		}
	}
	
	//병원 이름으로 모든 백신 수량 수정
	public void updateHospitalAllVaccine(String hospitalName, int pfizer, int moderna, int az) {
		if(hospitalName != null && !hospitalName.equals("")) {
			boolean result = HospitalDAO.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
			
			if(result == true) {
				EndView.successMessage("병원 : " + hospitalName + "] 백신 정보 수정");
			}else {
				EndView.failMessage("병원 : " + hospitalName + "] 백신 정보 수정");
			}
		}else {
			EndView.nullMessage();
		}
	}
	
	//병원 이름으로 백신 수량 수정
	public void updateHospitalVaccine(String hospitalName, String vaccineName, int num) {
		if(hospitalName != null && !hospitalName.equals("") && vaccineName != null && !vaccineName.equals("")) {
			boolean result = HospitalDAO.updateHospitalVaccine(hospitalName, vaccineName, num);
			
			if(result == true) {
				EndView.successMessage("병원 : " + hospitalName + "] 백신 [" + vaccineName +"] 정보 수정");
			}else {
				EndView.failMessage("병원 : " + hospitalName + "] 백신 [" + vaccineName +"] 정보 수정");
			}
		}else {
			EndView.nullMessage();
		}
	}
	
	//병원 이름으로 병원 삭제
	public void deleteHospital(String hospitalName) {
		if(hospitalName != null && !hospitalName.equals("")) {
			boolean result = HospitalDAO.deleteHospital(hospitalName);
			
			if(result == true) {
				EndView.successMessage("병원 : " + hospitalName + "] 삭제");
			}else {
				EndView.failMessage("병원 : " + hospitalName + "] 삭제");
			}
		}else {
			EndView.nullMessage();
		}
	}
}
