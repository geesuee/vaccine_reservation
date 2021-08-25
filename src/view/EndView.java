package view;

import java.util.List;

import model.entity.Hospital;
import model.entity.Users;
import model.entity.Vaccine;

public class EndView {

	// 메소드명 방식 통일 필요
	// 공통으로 쓸 수 있는 메소드 통합 필요
	
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
		if(allVaccine != null) {
			int length = allVaccine.size();
			
			if( length != 0 ){
				for(int index = 0; index < length; index++){			
					System.out.println("검색정보 " + (index+1) + " - " + allVaccine.get(index));
				}
			}else {
				System.out.println("요청하신 백신 정보는 존재하지 않습니다.");
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
		if (hospital != null) {
			int length = hospital.size(); // 저장된 데이터 개수 반환

			if (length != 0) {
				hospital.forEach(v -> System.out.println(v));
			} else {
				System.out.println("요청하신 정보는 없습니다");
			}
		} else {
			System.out.println("요청하신 정보는 없습니다");
		}
	}

	//특정 병원 출력
	public static void hospitalView(Hospital hospital) {
		if(hospital != null) {
			System.out.println(hospital);
		}else {
			System.out.println("해당 병원은 없습니다");
		}
	}
	
	// Hospital insert 성공, 실패시 메시지 출력 -
	public static void insertHospitalView(boolean result, String hospitalName) {
		if (result == true) {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "] insert 완료");
		} else {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "] insert 실패, id 재확인 하세요");
		}
	}
	
	// Hospital update 성공, 실패시 메시지 출력 -
	public static void updateHospitalView(boolean result, String hospitalName) {
		if (result == true) {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "]의 병원 정보 수정 완료");
		} else {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "] 수정 실패, 병원을 재확인 하세요");
		}
	}
	
	// Hospital update 성공, 실패시 메시지 출력 -
	public static void updateLocationView(boolean result, String hospitalName) {
		if (result == true) {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "]의 지역 정보 수정 완료");
		} else {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "] 수정 실패, 병원을 재확인 하세요");
		}
	}
	
	// Hospital update 성공, 실패시 메시지 출력 -
	public static void updateVaccineView(boolean result, String hospitalName) {
		if (result == true) {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "]의 백신 정보 수정 완료");
		} else {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "] 수정 실패, 병원을 재확인 하세요");
		}
	}
	
	// Hospital delete 성공, 실패시 메시지 출력 -
	public static void deleteSellerView(boolean result, String hospitalName) {
		if (result == true) {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "] delete 완료");
		} else {
			System.out.println("\n요청하신 [병원 : " + hospitalName + "] delete 실패, id 재확인 하세요");
		}
	}
	
}
