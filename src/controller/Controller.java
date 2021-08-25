package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.HospitalDAO;
import model.dao.UsersDAO;
import model.dao.VaccineDAO;
import model.entity.Hospital;
import model.entity.Users;
import model.entity.Vaccine;
import view.EndView;

public class Controller {
	private static Controller instance = new Controller();
//
//	private static HospitalDAO hDAO = HospitalDAO.getInstance();
////	private static VaccineDAO vDAO = VaccineDAO.getInstance();
////	private static UsersDAO uDAO = UsersDAO.getInstance();
//	
	private Controller() {};
	
	public static Controller getInstance() {
		return instance;
	}
//	
//	// 마지막에 다 같이 수정해요!!!
//	
//	/**
//	 * User Controller
//	 * - nextVaccineDate
//	 * - getUser
//	 * - createUser
//	 * - updateUserDate
//	 * - updateUserAddress
//	 * - deleteUser
//	 */
//	
//	public static void nextVaccineDate(int idNum) {
//		if((Integer)idNum != null) {
//			Users user = UsersDAO.getUserNextVaccineDate(idNum);
//			if(user != null) {
//				EndView.showNextVaccineDate(user);
//			}
//		}else {
//			EndView.errorMessage("주민등록번호 앞자리는 null일 수 없습니다.");
//		}
//	}
//	
//	
//	public static void getUser(String name, int idNum) {
//		if(name != null && !name.equals("") && (Integer)idNum != null) {
//			Users user = UsersDAO.getUser(name, idNum);
//			if(user != null) {
//				EndView.showUser(user);
//			}else {
//				EndView.errorMessage("입력하신 정보와 일치하는 접종 예약자 정보가 없습니다.");
//			}
//		}else {
//			EndView.errorMessage("이름과 주민등록번호 앞자리는 null일 수 없습니다.");
//		}
//	}
//
//	
//	public static void createUser(Users user) {
//		if(user != null) {
//			if(UsersDAO.createUser(user)) {
//				EndView.showResult(user.getUserName() + " 님의 백신 접종 예약이 완료되었습니다.");
//			}else {
//				EndView.errorMessage("접종 예약을 실패하였습니다.");
//			}
//		}else {
//			EndView.errorMessage("접종 예약자 정보는 null일 수 없습니다.");
//		}
//	}
//
//
//	public static void updateUserDate(int idNum, int dateNum, String date) {
//		if((Integer)idNum != null && (Integer)dateNum != null && date != null && !date.equals("")) {
//			if(UsersDAO.updateUserDate(idNum, dateNum, date)) {
//				EndView.showResult(dateNum + "차 백신 접종 일자가 " + date + "로 수정되었습니다.");
//			}else {
//				EndView.errorMessage("접종 예약 수정을 실패하였습니다.");
//			}
//		}else {
//			EndView.errorMessage("주민등록번호 앞자리, 수정할 접종 예약 차시, 수정할 접종 일자는 null일 수 없습니다.");
//		}
//	}
//
//	
//	public static void updateUserAddress(int idNum, String address) {
//		if((Integer)idNum != null && address != null && !address.equals("")) {
//			if(UsersDAO.updateUserAddress(idNum, address)) {
//				EndView.showResult("주소 수정이 완료되었습니다.");
//			}else {
//				EndView.errorMessage("주소 수정을 실패하였습니다.");
//			}
//		}else {
//			EndView.errorMessage("주민등록번호 앞자리, 수정할 주소 정보는 null일 수 없습니다.");
//		}
//	}
//
//	
//	public static void deleteUser(int idNum) {
//		if((Integer)idNum != null) {
//			if(UsersDAO.deleteUser(idNum)) {
//				EndView.showResult("백신 접종 예약 취소가 완료되었습니다.");
//			}else {
//				EndView.errorMessage("접종 예약 취소를 실패하였습니다.");
//			}
//		}else {
//			EndView.errorMessage("주민등록번호 앞자리는 null일 수 없습니다.");
//		}
//	}
//
//	
//	
//	/** 
//	 * Vaccine Controller
//	 * - getAllVaccine
//	 * - findByVaccineName
//	 * - findByVaccineTagetAge
//	 * - addVaccine
//	 * - updateVaccine
//	 * - updateVaccineAge
//	 * - deleteVaccine
//	 */
//	
//	public static void getAllVaccine() {
//		List<Vaccine> vaccineList = null;
//				
//		try {
//			vaccineList = VaccineDAO.getAllVaccine();
//			if(vaccineList.size() > 0) {
//				EndView.showVaccinList(vaccineList);
//			}else {
//				EndView.errorMessage("백신 정보가 없습니다.");
//			}
//		}catch(Exception e) {
//			EndView.errorMessage("<< 백신 정보 전체 조회 실패 >>");
//		}
//		
//	}
//
//
//	public static void findByVaccineName(String vaccinName) {
//		if(vaccinName != null && !vaccinName.equals("")) {
//			Vaccine vaccine = null;
//			
//			try {
//				vaccine = VaccineDAO.findByVaccineName(vaccinName);
//				if(vaccine != null) {
//					EndView.getVaccine(vaccine);
//				}else {
//					EndView.errorMessage("해당 이름의 백신 정보가 없습니다.");
//				}
//			}catch(Exception e) {
//				EndView.errorMessage("<< 백신 정보 단일 조회 실패 >>");
//				e.getStackTrace();
//			}
//			
//		}else {
//			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
//		}
//	}
//
//	
//	public static void findByVaccineTagetAge(int targetAge) {
//		if((Integer)targetAge !=null) {
//			try {
//				EndView.showVaccinList(VaccineDAO.findByVaccineTagetAge(targetAge));
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//		}else {
//			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
//		}
//		
//	}
//
//	
//	public static void addVaccine() {
//		// 데이터를 받아오는 곳.
//
////			Scanner sc = new Scanner(System.in);
////			
////			System.out.println("백신 명을 입력하세요");
////			String vaccineName = sc.nextLine();
////			System.out.println("백신의 접종 연령을 입력하세요");
////			int targetAge = sc.nextInt();
////			System.out.println("백신의 접종간격을 입력하세요");
////			int period = sc.nextInt();
////			System.out.println("플랫폼을 입력하세요");
////			String platform = sc.nextLine();
////			System.out.println("백신의 보관 온도를 입력하세요");
////			String temperature = sc.nextLine();
////			System.out.println("보관 기간을 숫자로 입력하세요");
////			int storage = sc.nextInt();
//		
////			Vaccine vaccine = new Vaccine(vaccineName, targetAge, period, platform, temperature, storage);
//		Vaccine vaccine = new Vaccine("얀센",50,0,"바이러스 벡터","-25도 ~ 15도",8);
//		EndView.addVaccineView(VaccineDAO.addVaccine(vaccine));
//				
//	}
//	
//	
//	public static void updateVaccine(String vaccineName) {
//		if(vaccineName != null && !vaccineName.equals("")) {
//			//수정 데이터, 객체 입력 
//			Vaccine updateVaccine = new Vaccine(vaccineName,30,0,"바이러스 벡터","-25도 ~ 15도",8);
//			VaccineDAO.updateVaccine(updateVaccine);
//		}else {
//			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
//		}
//		
//	}
//	public static void updateVaccineAge(String vaccineName, int age) {
//		if(vaccineName != null && !vaccineName.equals("") && (Integer)age !=null) {
//			boolean result = VaccineDAO.updateVaccineAge(vaccineName, age);
//			
//			if(result == true) {
//				System.out.println("변경 성공");
//			}else {
//				System.out.println("변경 실패");
//			}
//		}else {
//			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
//		}
//		
//	}
//
//	public static void deleteVaccine(String vaccineName) {
//		if(vaccineName != null && !vaccineName.equals("")) {
//			VaccineDAO.deleteVaccine(vaccineName);
//		}else {
//			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
//		}
//	}
//	
//	
//	
//	/** 
//	 * Hospital Controller
//	 * - getAllHospital
//	 * - getHospital
//	 * - getHospitalLocation
//	 * - getHospitalVaccine
//	 * - addHospital
//	 * - updateHospital
//	 * - updateHospitalLocation
//	 * - updateHospitalAllVaccine
//	 * - updateHospitalVaccine
//	 * - deleteHospital
//	 */
//	
//	//모든 병원 검색
//	public void getAllHospital(){
//		List<Hospital> hos = HospitalDAO.getAllHospital();
//		
//		if(hos.size() > 0) {
//			EndView.hospitalAllView(hos);
//		}else {
//			EndView.errorMessage("병원이 하나도 없습니다");
//		}
//	}
//	
//	//병원 이름으로 병원 검색
//	public void getHospital(String hospitalName) {
//		if(hospitalName != null && !hospitalName.equals("")) {
//			Hospital hos = HospitalDAO.getHospitalByName(hospitalName);
//			
//			if(hos != null) {
//				EndView.hospitalView(hos);
//			}else {
//				EndView.errorMessage("검색한 이름의 병원은 존재하지 않습니다");
//			}
//		}else {
//			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");	
//			
//		}
//	}
//	
//	//지역으로 병원 검색
//	public void getHospitalLocation(String location) {
//		if(location != null && !location.equals("")) {
//			Hospital hos = HospitalDAO.getHospitalByLocation(location);
//			
//			if(hos != null) {
//				EndView.hospitalView(hos);
//			}else {
//				EndView.errorMessage("검색한 지역에 있는 병원이 없습니다");
//			}
//		}else {
//			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
//		}
//	}
//	
//	//백신이 있는 병원 검색
//	public void getHospitalVaccine(String vaccineName) {
//		if(vaccineName != null && !vaccineName.equals("")) {
//			ArrayList<Hospital> hos = HospitalDAO.getHospitalByVaccine(vaccineName);
//			
//			if(hos.size() > 0) {
//				EndView.hospitalAllView(hos);
//			}else {
//				EndView.errorMessage("해당하는 백신이 있는 병원이 하나도 없습니다");
//			}
//		}else {
//			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
//		}
//	}
//	
//	//새로운 병원 저장
//	public void addHospital(Hospital hospital) {
//		if(hospital != null) {
//			boolean result = HospitalDAO.addHospital(hospital);
//			
//			if(result == true) {
//				EndView.hospitalImpormationView(hospital.getHospitalName() + "] insert");
//			}else {
//				EndView.errorMessage("\n요청하신 [병원 : " + hospital.getHospitalName() + "] insert 실패, 병원을 재확인 하세요");
//			}
//		}else {
//			EndView.errorMessage("해당 병원은 존재하지 않습니다");
//		}
//	}
//	
//	//병원 이름으로 모든 병원 정보 수정
//	public void updateHospital(String hospitalName, String location, int pfizer, int moderna, int az) {
//		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
//			boolean result1 = HospitalDAO.updateHospitalLocation(hospitalName, location);
//			boolean result2 = HospitalDAO.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
//			
//			if(result1 == true && result2 == true) {
//				EndView.hospitalImpormationView(hospitalName + "] 정보 수정");
//			}else {
//				EndView.errorMessage("수정할 병원과 정보를 다시 한번 확인해 주세요");
//			}
//		}else {
//			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
//		}
//	}
//	
//	//병원 이름으로 지역 수정
//	public void updateHospitalLocation(String hospitalName, String location) {
//		if(hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {
//			boolean result = HospitalDAO.updateHospitalLocation(hospitalName, location);
//			
//			if(result == true) {
//				EndView.hospitalImpormationView(hospitalName + "] 지역 정보 수정");
//			}else {
//				EndView.errorMessage("\n요청하신 [병원 : " + hospitalName + "] 수정 실패, 병원을 재확인 하세요");
//			}
//		}else {
//			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
//		}
//	}
//	
//	//병원 이름으로 모든 백신 수량 수정
//	public void updateHospitalAllVaccine(String hospitalName, int pfizer, int moderna, int az) {
//		if(hospitalName != null && !hospitalName.equals("")) {
//			boolean result = HospitalDAO.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
//			
//			if(result == true) {
//				EndView.hospitalImpormationView(hospitalName + "] 백신 정보 수정");
//			}else {
//				EndView.errorMessage("\n요청하신 [병원 : " + hospitalName + "] 수정 실패, 병원을 재확인 하세요");
//			}
//		}else {
//			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
//		}
//	}
//	
//	//병원 이름으로 백신 수량 수정
//	public void updateHospitalVaccine(String hospitalName, String vaccineName, int num) {
//		if(hospitalName != null && !hospitalName.equals("") && vaccineName != null && !vaccineName.equals("")) {
//			boolean result = HospitalDAO.updateHospitalVaccine(hospitalName, vaccineName, num);
//			
//			if(result == true) {
//				EndView.hospitalImpormationView(hospitalName + "] 백신 [" + vaccineName +"] 정보 수정");
//			}else {
//				EndView.errorMessage("\n요청하신 [병원 : " + hospitalName + "] 수정 실패, 병원을 재확인 하세요");
//			}
//		}else {
//			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
//		}
//	}
//	
//	//병원 이름으로 병원 삭제
//	public void deleteHospital(String hospitalName) {
//		if(hospitalName != null && !hospitalName.equals("")) {
//			boolean result = HospitalDAO.deleteHospital(hospitalName);
//			
//			if(result == true) {
//				EndView.hospitalImpormationView(hospitalName + "] delete");
//			}else {
//				EndView.errorMessage("\n요청하신 [병원 : " + hospitalName + "] delete 실패, 병원을 재확인 하세요");
//			}
//		}else {
//			EndView.errorMessage("null값이거나 아무것도 입력하지 않았습니다");
//		}
//	}
}
