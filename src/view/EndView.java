package view;

import java.util.List;

import model.entity.Users;
import model.entity.Vaccine;

public class EndView {
	public static void showNextVaccineDate(Users user) {
		
		System.out.println(user.getUserName()+"�� 1�� ��� ��¥ :"+user.getDate1() +"2�� ��� �������� :" +user.getDate2()+" �Դϴ�.");
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


	/*EndView +VaccineDAO
	 * -showNextVaccineDate
	 * -showVaccinList
	 * -addVaccineView
	 * -getVaccine
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
	
}
