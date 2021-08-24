package view;

import controller.Controller;
import model.dao.UsersDAO;
import model.entity.Hospital;
import model.entity.Users;
import model.entity.Vaccine;

public class StartView {
	
	
	public static void main(String[] args) {
//		System.out.println("=== user 검색 ===");
//		Controller.getUser("배지수", 971202);
//		
//		System.out.println("\n=== 새로운 user insert ===");
//		Vaccine vaccine = new Vaccine("화이자", 12, 21, "mRNA", "-90도 ~ -60도", 6);
//		Hospital hospital = new Hospital("아산병원", "송파구", 0, 4, 5);
//		Users user = new Users(970326, "이백신", 20, "송파구", "20210801", UsersDAO.getNextVaccineDate("20210801", vaccine.getPeriod()), vaccine, hospital);
		/**
		 * ***새로운 예약이 insert 되면,
		 * 해당 병원의 해당 백신 수 -1;
		 */
//		Controller.createUser(user);
		
//		System.out.println("\n=== 접종 예약 취소 delete ===");
//		Controller.deleteUser(970324);
		/**
		 * ***예약이 취소되면,
		 * 해당 병원의 해당 백신 수 +1;
		 */
		
//		System.out.println("\n=== 접종 예약 수정 update ===");
//		System.out.println("** 접종 일자 수정은 <<접종 연기>>만 가능합니다.");
		/**
		 * 수정할 때, User를 찾는 과정을 getUser로 하고 user 객체를 넘길지?
		 * 이렇게 되면 controller에서 "없는 user 정보입니다."와 같은 방법으로 예외 처리 가능
		 * 
		 * 1. user 정보가 있는지 없는지
		 * 2. 수정할 접종 차시를 1, 2 중 하나로 입력 했는지
		 * 3. 1차시 예약을 수정할 경우
		 * 		- 1차 접종 기존 날짜가 아직 오지 않은 것이 맞는지
		 * 		- 예약 변경 일자가 오늘 이후가 맞는지
		 * 		- *** 1차 접종 날짜 수정되면 자동으로 2차 날짜도 수정되도록 연결??
		 * 4. 2차시 예약을 수정할 경우
		 * 		- 2차 접종 기존 날짜가 아직 오지 않은 것이 맞는지
		 * 		- 예약 변경 일자가 오늘 이후가 맞는지
		 * 		- *** 1차 접종일로부터 얼마나 먼지 확인해서 안되게하는 제약 조건 필요!! (임의 날짜 이후는 안되게 입력-3달)
		 */
//		System.out.println("\n1) 1차 예약 수정 선택, 기존 날짜보다 예전 날짜 입력");
//		Controller.updateUserDate(971202, 1, "20210702");
//		
//		System.out.println("\n2) 1차 예약 수정 선택, 오늘 날짜보다 예전 날짜 입력");
//		Controller.updateUserDate(971202, 1, "20210823");
//		
//		System.out.println("\n3) 2차 예약 수정 선택, 기존 날짜보다 예전 날짜 입력");
//		Controller.updateUserDate(971202, 2, "20210915");
//		
//		System.out.println("\n3) 2차 예약 수정 선택, 1차 접종 날짜보다 예전 날짜 입력");
//		Controller.updateUserDate(971202, 2, "20210722");
//		
//		System.out.println("\n4) 2차 예약 수정 선택, 오늘 날짜보다 예전 날짜 입력");
//		Controller.updateUserDate(971202, 2, "20210823");
//		
//		System.out.println("\n=== 접종 예약 수정 성공 케이스 ===");
//		Controller.updateUserDate(971202, 2, "20210918");
//		Controller.nextVaccineDate(971202);
		
		System.out.println("\n=== 사용자 주소 수정 update ===");
		Controller.updateUserAddress(971202, "마포구");
	}

}
