//package controller;
//
//import java.util.List;
//
//import model.dao.VaccineDAO;
//import model.entity.Vaccine;
//import view.EndView;
//
//public class VaccineController {
//	
//	private static VaccineController instance = new VaccineController();
//	
//	private VaccineController() {};
//	
//	public static VaccineController getInstance() {
//		return instance;
//	}
//	
//	/** 
//	 * Vaccine Controller
//	 * - getAllVaccine
//	 * - getVaccineByName
//	 * - **getVaccineName
//	 * - getVaccineByTargetAge
//	 * - **getVaccineAge
//	 * - addVaccine
//	 * - updateVaccine
//	 * - updateVaccineTargetAge
//	 * - deleteVaccine
//	 */
//	
//	public void getAllVaccine() {
//		List<Vaccine> vaccineList = VaccineDAO.getAllVaccine();
//		
//		if(vaccineList.size() > 0) {
//			EndView.showAll(vaccineList);
//		}else {
//			EndView.errorMessage("백신 정보가 존재하지 않습니다.");
//		}
//	}
//	
//	
//	public void getVaccineByName(String vaccinName) {
//		if(vaccinName != null && !vaccinName.equals("")) {
//			Vaccine vaccine = VaccineDAO.getVaccineByName(vaccinName);
//			if(vaccine != null) {
//				EndView.showOne(vaccine);
//			}else {
//				EndView.errorMessage("해당 이름의 백신 정보가 존재하지 않습니다.");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
////	//**예외처리 필
////	public Vaccine getVaccineName(String vaccinName) {
////		return VaccineDAO.getVaccineByName(vaccinName);
////	}
//
//	
//	public void getVaccineByTargetAge(int targetAge) {
//		if((Integer)targetAge !=null) {
//			List<Vaccine> vaccineList = VaccineDAO.getVaccineByTargetAge(targetAge);
//			
//			if(vaccineList.size() > 0) {
//				EndView.showAll(vaccineList);
//			}else {
//				EndView.errorMessage("해당 연령 대상 백신 정보가 존재하지 않습니다.");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
////	//**예외 처리 필
////	public List<Vaccine> getVaccineAge(int age) {
////		return VaccineDAO.getVaccineByTargetAge(age);
////	}
//
//	
//	public void addVaccine(Vaccine vaccine) {
//		if(vaccine != null) {
//			if(VaccineDAO.addVaccine(vaccine)) {
//				EndView.successMessage("백신 : " + vaccine.getVaccineName() + "] 저장");
//			}else {
//				EndView.failMessage("백신 : " + vaccine.getVaccineName() + "] 저장");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	} 
//	
//	
//
//	public void updateVaccine(String vaccineName,String platform,String temper) {
//		if(vaccineName != null && !vaccineName.equals("")) {
//			
//			if(VaccineDAO.updateVaccine(vaccineName,platform,temper)) {
//				EndView.successMessage("백신 : " + vaccineName + "] 정보 수정");
//			}else {
//				EndView.failMessage("백신 : " + vaccineName + "] 정보 수정");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
//	
//
//	public void updateVaccineTargetAge(String vaccineName, int age) {
//		if(vaccineName != null && !vaccineName.equals("") && (Integer)age !=null) {
//			
//			if(VaccineDAO.updateVaccineTargetAge(vaccineName, age)) {
//				EndView.successMessage("백신 : " + vaccineName + "] 접종 연령 수정");
//			}else {
//				EndView.failMessage("백신 : " + vaccineName + "] 접종 연령 수정");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//
//
//	public void deleteVaccine(String vaccineName) {
//		if(vaccineName != null && !vaccineName.equals("")) {
//			
//			if(VaccineDAO.deleteVaccine(vaccineName)) {
//				EndView.successMessage("백신 : " + vaccineName + "] 삭제");
//			}else {
//				EndView.failMessage("백신 : " + vaccineName + "] 삭제");
//			}
//		}else {
//			EndView.nullMessage();
//		}
//	}
//	
//}
