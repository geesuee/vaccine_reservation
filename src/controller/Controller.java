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
		EndView.hospitalAllView(hos);
	}
	
	//병원 이름으로 병원 검색
	public void getHospital(String hospitalName) {
		Hospital hos = hd.getHospital(hospitalName);
		
		if(hos != null) {
			EndView.hospitalView(hos);
		}else {
			System.out.println("검색한 이름의 병원은 존재하지 않습니다");
		}
	}
	
	//지역으로 병원 검색
	public void getHospitalLocation(String location) {
		Hospital hos = hd.getLocation(location);
		
		if(hos != null) {
			EndView.hospitalView(hos);
		}else {
			System.out.println("검색한 지역에 있는 병원이 없습니다");
		}
	}
	
	//백신이 있는 병원 검색
	public void getVaccine(String vaccineName) {
		ArrayList<Hospital> hos = hd.getVaccine(vaccineName);
		EndView.hospitalAllView(hos);
	}
	
	//새로운 병원 저장
	public void addHospital(Hospital hospital) {
		boolean result = hd.addHospital(hospital);
		EndView.insertHospitalView(result, hospital.getHospitalName());
	}
	
	//병원 이름으로 모든 병원 정보 수정
	public void updateHospital(String hospitalName, String location, int pfizer, int moderna, int az) {
		boolean result1 = hd.updateLocation(hospitalName, location);
		boolean result2 = hd.updateAllVaccine(hospitalName, pfizer, moderna, az);
		
		if(result1 == true && result2 == true) {
			EndView.updateLocationView(true, hospitalName);
		}else {
			System.out.println("수정할 병원과 정보를 다시 한번 확인해 주세요");
		}
	}
	
	//병원 이름으로 지역 수정
	public void updateLocation(String hospitalName, String location) {
		boolean result = hd.updateLocation(hospitalName, location);
		EndView.updateLocationView(result, hospitalName);
	}
	
	//병원 이름으로 모든 백신 수량 수정
	public void updateAllVaccine(String hospitalName, int pfizer, int moderna, int az) {
		boolean result = hd.updateAllVaccine(hospitalName, pfizer, moderna, az);
		EndView.updateVaccineView(result, hospitalName);
	}
	
	//병원 이름으로 백신 수량 수정
	public void updateVaccine(String hospitalName, String vaccineName, int num) {
		boolean result = hd.updateVaccine(hospitalName, vaccineName, num);
		EndView.updateVaccineView(result, hospitalName);
	}
	
	//병원 이름으로 병원 삭제
	public void deleteHospital(String hospitalName) {
		boolean result = hd.deleteHospital(hospitalName);
		EndView.deleteSellerView(result, hospitalName);
	}
	
	
}
