package view;

import model.entity.Users;

public class EndView {
	public static void showNextVaccineDate(Users user) {
		
		System.out.println(user.getUserName()+"�� 1�� ��� ��¥ :"+user.getDate1() +"2�� ��� �������� :" +user.getDate2()+" �Դϴ�.");
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
