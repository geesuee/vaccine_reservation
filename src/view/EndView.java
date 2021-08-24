package view;

import java.util.ArrayList;
import java.util.List;

import model.entity.Hospital;

public class EndView {
	
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
	
	//모든 정보 출력
	public static void hospitalAllView(ArrayList<Hospital> hospital) {
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
	
	//에러 메시지
	public static void errorMessage(String message) {
		System.out.println(message);
	}

}
