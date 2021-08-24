package view;

import controller.Controller;

public class StartView {

	public static void main(String[] args) {
		Controller con = Controller.getInstance();
		
//		System.out.println("--- 모든 병원 검색 ---");
//		con.getAllHospital();
//		
//		System.out.println("--- 지역으로 검색 ---");
//		con.getHospitalLocation("송파구");
//		
//		System.out.println("--- 백신으로 검색 ---");
//		con.getHospitalVaccine("화이자");
//		
//		System.out.println("--- 새로운 병원 저장 ---");
//		Hospital hospital = new Hospital("야매병원", "지하", 2, 0, 0);
//		con.addHospital(hospital);
//		
//		System.out.println("--- 저장 후 검색 ---");
//		con.getAllHospital();
//		
//		System.out.println("--- 지역 수정 ---");
//		con.updateLocation("야매병원", "지상");
//		
//		System.out.println("--- 수정 후 ---");
//		con.getHospital("야매병원");
//		
//		System.out.println("--- 모두 수정 ---");
//		con.updateHospital("야매병원", "바다위", 1, 0, 3);
//		
//		System.out.println("--- 수정 후 ---");
//		con.getHospital("야매병원");
//		
//		System.out.println("--- 삭제 ---");
//		con.deleteHospital("야매병원");
//		
//		System.out.println("--- 삭제 후 ---");
//		con.getHospital("야매병원");
		con.getHospitalVaccine("모름");
	}

}
