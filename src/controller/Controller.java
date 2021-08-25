package controller;

public class Controller {
	
	private static Controller instance = new Controller();
	
	private Controller() {};
	
	public static Controller getInstance() {
		return instance;
	}
	
	private HospitalController hc = HospitalController.getInstance();
	private UsersController uc = UsersController.getInstance();
	private VaccineController vc = VaccineController.getInstance();
	
	/**
	 * 1. 접종 예약자인지 확인, 관리자 인지 확인 -> EndView 출력 / Controller
	 * 
	 * 2. 접종 예약자 기능
	 * 	- getUser로 예약 확인
	 * 		<예약 X -> insert 하는 과정>
	 * 		1.
	 * 		- 나이로 맞을 수 있는 백신 확인
	 * 		- 주소로 병원 확인
	 * 		- 그 병원 중에 위의 백신이 있는 곳 출력
	 * 		=> 선택 <백신 종류, 병원, 접종날짜 선택> => 예약자 정보 추가 + 해당 병원 백신 수량 조절
	 * 
	 * 		<예약 O>
	 * 		2. 수정
	 * 		- 백신 날짜 확인
	 * 		- 백신 날짜 수정
	 * 		- 자기 주소 수정
	 * 		
	 * 		3. 삭제 = 예약 취소
	 * 
	 * 3. 관리자 기능
	 * 	<병원>
	 * 		- 병원 전체 조회(타병원 백신 갯수 확인 가능)
	 * 		- 병원 추가
	 * 		- 병원 삭제
	 * 		- 병원 지역 수정
	 * 		- 병원 백신 수량 수정 / 전체-개별
	 * 		- 해당 병원 예약자 전체 검색
	 * 		
	 *	<백신>
	 *		- 백신 전체 검색
	 *		- #백신 추가 -> .........
	 *		- #백신 삭제 -> ......... 
	 *		- 백신 전체 수정<플랫폼, 온도>
	 *		- 백신 접종 연령 수정
	 *	<접종>
	 *		- 접종 예약자 전체 조회
	 */

}
