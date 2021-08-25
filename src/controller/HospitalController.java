//package controller;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//import model.dao.HospitalDAO;
//import model.entity.Hospital;
//import model.entity.Vaccine;
//import view.EndView;
//
//public class HospitalController {
//
//	private static HospitalController instance = new HospitalController();
//
//	private HospitalController() {};
//
//	public static HospitalController getInstance() {
//		return instance;
//	}
//	
//	/** 
//	 * Hospital Controller
//	 * - getAllHospital
//	 * - getHospital
//	 * - **getHospitalName
//	 * - getHospitalByLocation
//	 * - getHospitalVaccine
//	 * - **getReservationHospital
//	 * - **getHospitalVaccineTotal
//	 * - addHospital
//	 * - updateHospital
//	 * - updateHospitalLocation
//	 * - updateHospitalAllVaccine
//	 * - updateHospitalVaccine
//	 * - deleteHospital
//	 */
//	
//
//	public void getAllHospital(){
//		List<Hospital> hospitalList = HospitalDAO.getAllHospital();
//		
//		if(hospitalList.size() > 0) {
//			EndView.showAll(hospitalList);
//		}else {
//			EndView.errorMessage("병원 정보가 존재하지 않습니다.");
//		}
//	}
//	
//
//	public void getHospital(String hospitalName) {
//		if(hospitalName != null && !hospitalName.equals("")) {
//			Hospital hospital = HospitalDAO.getHospitalByName(hospitalName);
//			
//			if(hospital != null) {
//				EndView.showOne(hospital);
//			}else {
//				EndView.errorMessage("해당 이름의 병원 정보가 존재하지 않습니다.");
//			}
//		}else {
//			EndView.nullMessage();	
//		}
//	}
//	
//
////	public Hospital getHospitalName(String hospitalName) {
////		return HospitalDAO.getHospitalByName(hospitalName);
////	}
//	
//
//	public void getHospitalByLocation(String location) {
//		if(location != null && !location.equals("")) {
//			List<Hospital> hospitalList = HospitalDAO.getHospitalByLocation(location);
//			
//			if(hospitalList != null) {
//				EndView.showAll(hospitalList);
//			}else {
//				EndView.errorMessage("해당 지역에 위치한 병원 정보가 존재하지 않습니다.");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
//	public void getHospitalByVaccineName(String vaccineName) {
//		if(vaccineName != null && !vaccineName.equals("")) {
//			ArrayList<Hospital> hospitalList = HospitalDAO.getHospitalByVaccineName(vaccineName);
//			
//			if(hospitalList.size() > 0) {
//				EndView.showAll(hospitalList);
//			}else {
//				EndView.errorMessage("해당 백신을 보유한 병원 정보가 존재하지 않습니다.");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
////	//해당하는 백신이 있고 주소가 같은 병원 반환
////	public HashSet<Hospital> getReservationHospital(List<Vaccine> vaccine, String location){
////		List<Hospital> hospital1 = HospitalDAO.getHospitalByLocation(location);
////		List<Hospital> hospital2 = HospitalDAO.getHospitalByVaccine(vaccine);
////		HashSet<Hospital> result = new HashSet<>();
////		
////		for(Hospital h : hospital1) {
////			for(int i=0; i<hospital2.size(); i++) {
////				if(h.getHospitalName().equals(hospital2.get(i).getHospitalName())) {
////					result.add(h);
////				}
////			}
////		}
////		return result;
////	}
//	
//	//해당 백신 수량 반환
////	public int getHospitalVaccineTotal(String hospitalName, String vaccineName) {
////		return HospitalDAO.getHospitalVaccineTotal(hospitalName, vaccineName);
////	}
////	
////
//	public void addHospital(Hospital hospital) {
//		if(hospital != null) {
//			
//			if(HospitalDAO.addHospital(hospital)) {
//				EndView.successMessage("병원 : " + hospital.getHospitalName() + "] 저장");
//			}else {
//				EndView.failMessage("병원 : " + hospital.getHospitalName() + "] 저장");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
//
//	public void updateHospital(String hospitalName, String location, int pfizer, int moderna, int az) {
//		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
//			boolean result1 = HospitalDAO.updateHospitalLocation(hospitalName, location);
//			boolean result2 = HospitalDAO.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
//			
//			if(result1 && result2) {
//				EndView.successMessage("병원 : " + hospitalName + "] 정보 수정");
//			}else {
//				EndView.failMessage("병원 : " + hospitalName + "] 정보 수정");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
//
//	public void updateHospitalLocation(String hospitalName, String location) {
//		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
//			
//			if(HospitalDAO.updateHospitalLocation(hospitalName, location)) {
//				EndView.successMessage("병원 : " + hospitalName + "] 지역 정보 수정");
//			}else {
//				EndView.failMessage("병원 : " + hospitalName + "] 지역 정보 수정");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
//
//	public void updateHospitalAllVaccine(String hospitalName, int pfizer, int moderna, int az) {
//		if(hospitalName != null && !hospitalName.equals("")) {
//			
//			if(HospitalDAO.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az)) {
//				EndView.successMessage("병원 : " + hospitalName + "] 백신 정보 수정");
//			}else {
//				EndView.failMessage("병원 : " + hospitalName + "] 백신 정보 수정");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
//
//	public void updateHospitalVaccine(String hospitalName, String vaccineName, int num) {
//		if(hospitalName != null && !hospitalName.equals("") && vaccineName != null && !vaccineName.equals("")) {
//			
//			if(HospitalDAO.updateHospitalVaccine(hospitalName, vaccineName, num)) {
//				EndView.successMessage("병원 : " + hospitalName + "] 백신 [" + vaccineName +"] 정보 수정");
//			}else {
//				EndView.failMessage("병원 : " + hospitalName + "] 백신 [" + vaccineName +"] 정보 수정");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
//
//	public void deleteHospital(String hospitalName) {
//		if(hospitalName != null && !hospitalName.equals("")) {
//			
//			if(HospitalDAO.deleteHospital(hospitalName)) {
//				EndView.successMessage("병원 : " + hospitalName + "] 삭제");
//			}else {
//				EndView.failMessage("병원 : " + hospitalName + "] 삭제");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//}
