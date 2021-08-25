package view;

import java.util.List;

import model.entity.Users;

public class EndView {

	
	//다중 정보 출력
	public static void showAll(List object) {
		System.out.println();
		object.forEach(v -> System.out.println(v));
	}
	
	//단일 정보 출력
	public static void showOne(Object object) {
		System.out.println("\n" + object);
	}
	
	//성공 메세지 출력
	public static void successMessage(String function) {
		System.out.println("\n요청하신 [" + function + " 완료");
	}
	
	//실패 메세지 출력
	public static void failMessage(String function) {
		System.out.println("\n요청하신 [" + function + " 실패");
	}
	
	//에러 메세지 출력
	public static void errorMessage(String string) {
		System.out.println("\n<< 에러 발생 >> " + string);
	}
	
	//null값 입력 시 출력
	public static void nullMessage() {
		System.out.println("\n입력값이 잘못 되었습니다. 재확인 부탁드립니다.");
	}
	
	//다음 백신 날짜 출력
	public static void showNextVaccineDate(Users user) {
		System.out.println("\n" + user.getUserName()+"님 1차 백신 날짜 : "+user.getDate1() +"2차 백신 날짜 : " +user.getDate2()+" 입니다.");
	}

}
