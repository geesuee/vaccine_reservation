package view;

import model.entity.Users;

public class EndView {
	public static void showNextVaccineDate(Users user) {
		
		System.out.println(user.getUserName()+"님 1차 백신 날짜 :"+user.getDate1() +"2차 백신 예정일은 :" +user.getDate2()+" 입니다.");
	}

	public static void ErroMessage(String string) {
		System.out.println(string);
	}
	
	public static void showResult(String string) {
		System.out.println(string);
	}

	public static void showUser(Users user) {
		System.out.println(user);
	}

	
	
}
