package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.HospitalDAO;
import model.entity.Hospital;
import view.EndView;

public class Controller {
	private static Controller instance = new Controller();

	private HospitalDAO hd = HospitalDAO.getInstance();
	
	private Controller() {};
	
	public static Controller getInstance() {
		return instance;
	}
	
	//모든 병원 검색
	public void getAllHospital(){
		List<Hospital> hos = hd.getAllHospital();
		
		if(hos.size() > 0) {
			EndView.hospitalAllView(hos);
		}else {
			EndView.errorMessage("병원이 하나도 없습니다");
		}
	}
	
	//병원 이름으로 병원 검색
	public void getHospital(String hospitalName) {
		if(hospitalName != null && !hospitalName.equals("")) {
			Hospital hos = hd.getHospital(hospitalName);
			
			if(hos != null) {
				EndView.hospitalView(hos);
			}else {
				EndView.errorMessage("검색한 이름의 병원은 존재하지 않습니다");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");	
			
		}
	}
	
	//지역으로 병원 검색
	public void getHospitalLocation(String location) {
		if(location != null && !location.equals("")) {
			Hospital hos = hd.getLocation(location);
			
			if(hos != null) {
				EndView.hospitalView(hos);
			}else {
				EndView.errorMessage("검색한 지역에 있는 병원이 없습니다");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
		}
	}
	
	//백신이 있는 병원 검색
	public void getHospitalVaccine(String vaccineName) {
		if(vaccineName != null && !vaccineName.equals("")) {
			ArrayList<Hospital> hos = hd.getHospitalVaccine(vaccineName);
			
			if(hos.size() > 0) {
				EndView.hospitalAllView(hos);
			}else {
				EndView.errorMessage("해당하는 백신이 있는 병원이 하나도 없습니다");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
		}
	}
	
	//새로운 병원 저장
	public void addHospital(Hospital hospital) {
		if(hospital != null) {
			boolean result = hd.addHospital(hospital);
			EndView.insertHospitalView(result, hospital.getHospitalName());
		}else {
			EndView.errorMessage("해당 병원은 존재하지 않습니다");
		}
	}
	
	//병원 이름으로 모든 병원 정보 수정
	public void updateHospital(String hospitalName, String location, int pfizer, int moderna, int az) {
		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
			boolean result1 = hd.updateLocation(hospitalName, location);
			boolean result2 = hd.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
			
			if(result1 == true && result2 == true) {
				EndView.updateLocationView(true, hospitalName);
			}else {
				EndView.errorMessage("수정할 병원과 정보를 다시 한번 확인해 주세요");
			}
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
		}
	}
	
	//병원 이름으로 지역 수정
	public void updateLocation(String hospitalName, String location) {
		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
			boolean result = hd.updateLocation(hospitalName, location);
			EndView.updateLocationView(result, hospitalName);
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
		}
	}
	
	//병원 이름으로 모든 백신 수량 수정
	public void updateHospitalAllVaccine(String hospitalName, int pfizer, int moderna, int az) {
		if(hospitalName != null && !hospitalName.equals("")) {
			boolean result = hd.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
			EndView.updateVaccineView(result, hospitalName);
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
		}
	}
	
	//병원 이름으로 백신 수량 수정
	public void updateHospitalVaccine(String hospitalName, String vaccineName, int num) {
		if(hospitalName != null && !hospitalName.equals("") && vaccineName != null && !vaccineName.equals("")) {
			boolean result = hd.updateHospitalVaccine(hospitalName, vaccineName, num);
			EndView.updateVaccineView(result, hospitalName);
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
		}
	}
	
	//병원 이름으로 병원 삭제
	public void deleteHospital(String hospitalName) {
		if(hospitalName != null && !hospitalName.equals("")) {
			boolean result = hd.deleteHospital(hospitalName);
			EndView.deleteSellerView(result, hospitalName);
		}else {
			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
		}
	}
	
	
}
