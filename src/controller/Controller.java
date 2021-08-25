package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
	
	private HospitalController hc = HospitalController.getInstance();
	private UsersController uc = UsersController.getInstance();
	private VaccineController vc = VaccineController.getInstance();
	
	public static void vaccineStart() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String esc = null;

		try {
			while (esc == null) {
				System.out.println("===================사용자 유형을 선택하세요.===================");
				System.out.println("1) 접종 예약자 \n 2) 관리자 \n 0) 시스템 끄기");				
				System.out.println("======================================");
				
				int inputNum = Integer.parseInt(br.readLine());
				
				if(inputNum == 1) {
					//접종
					
				}else if(inputNum == 2) {
					System.out.println("===================검색 분야를 선택하세요.===================");
					System.out.println("1) 병원 \n 2) 백신");	
					int inputNum2 = Integer.parseInt(br.readLine());
					
					if(inputNum2 == 1) {
						// 병원
						//해당병원만 조회 / 전체 조회
					}else if(inputNum2 == 2) {
						//백신
					}else {
						//에러메세지
					}
					
				}else {
					//에러메세지
				}
				
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			br.close();
		}
			
	}
	
	
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
	 *		- 접종 예약자 전체 조회 -> 병원
	 */
}
