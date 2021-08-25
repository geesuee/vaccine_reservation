package view;

import java.util.List;

import model.entity.Hospital;
import model.entity.Users;
import model.entity.Vaccine;

public class EndView {

	// 메소드명 방식 통일 필요
	// 공통으로 쓸 수 있는 메소드 통합 필요
	
	public static void allView(List all) {
		all.forEach(v -> System.out.println(v));
	}
	/**
	 * Users EndView
	 * - showNextVaccineDate
	 * - showUser
	 * - errorMessage (공통)
	 * - showResult (공통 - 아직 통합 전)
	 * - showUsersList
	 */
	
	public static void showNextVaccineDate(Users user) {

		System.out.println(user.getUserName()+"님 1차 백신 날짜 :"+user.getDate1() +"2차 백신 예정일은 :" +user.getDate2()+" 입니다.");
	}

	public static void errorMessage(String string) {
		System.out.println(string);
	}
	
	public static void showResult(String string) {
		System.out.println(string);
	}

	public static void showUser(Users user) {
		System.out.println(user);
	}
	
	public static void showUsersList(List<Users> userList) {
		userList.forEach(v -> System.out.println(v));
	}


	/**
	 * Vaccine EndView
	 * - showVaccinList
	 * - addVaccineView
	 * - getVaccine
	 */

	public static void showVaccinList(List<Vaccine> allVaccine) {
		int length = allVaccine.size();
		
		if( length != 0 ){
			for(int index = 0; index < length; index++){			
				System.out.println("검색정보 " + (index+1) + " - " + allVaccine.get(index));
			}
		}else {
			System.out.println("요청하신 백신 정보는 존재하지 않습니다.");
		}
	}
	

	public static void addVaccineView(Vaccine vaccine) {
		if(vaccine == null) {
			System.out.println("백신 등록 실패하였습니다.");
		}else {
			System.out.println(vaccine+"백신 등록 성공");
		}
	}

	public static void getVaccine(Vaccine vaccine) {
		if(vaccine == null) {
			System.out.println("요청하신 백신 정보는 존재하지 않습니다.");
		}else {
			System.out.println(vaccine);
		}
	}
	
	
	/**
	 * Hospital EndView
	 * - hospitalAllView
	 * - hospitalView
	 * - insertHospitalView
	 * - updateHospitalView
	 * - updateLocationView
	 * - updateVaccineView
	 * - deleteSellerView
	 */
	
	//모든 정보 출력
	public static void hospitalAllView(List<Hospital> hospital) {
		hospital.forEach(v -> System.out.println(v));
	}

	//특정 병원 출력
	public static void hospitalView(Hospital hospital) {
		if(hospital != null) {
			System.out.println(hospital);
		}else {
			System.out.println("해당 병원은 없습니다");
		}
	}
	
	//어떤 작업을 했는지 출력
	public static void hospitalImpormationView(String impormation) {
		System.out.println("\n요청하신 [병원 : " + impormation + " 완료");
	}
}
