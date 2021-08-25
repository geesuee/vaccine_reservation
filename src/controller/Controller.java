package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
	
	private static HospitalController hc = HospitalController.getInstance();
	private static UsersController uc = UsersController.getInstance();
	private static VaccineController vc = VaccineController.getInstance();
	
	public static void vaccineStart() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String esc = null;

		try {
			while (esc == null) {
				System.out.println("┌───────────────사용자 유형을 선택하세요.─────┐");
				System.out.println("│1) 접종 예약자                                                            │");				
				System.out.println("│2) 관리자                                                                   │");				
				System.out.println("│0) 시스템 끄기                                                            │");				
				System.out.println("└─────────────────────────────────────┘");
				
				int inputNum = Integer.parseInt(br.readLine());
				
				if(inputNum == 1) {
					System.out.println("1) 접종 예약자를 선택하셨습니다.");
					
					System.out.println("예약 조회를 위해 이름을 입력해주세요");
					String userName = br.readLine();
					
					System.out.println("예약 조회를 위해 주민등록 번호를 입력해주세요");
					int idnum = Integer.parseInt(br.readLine());
					
					uc.getUser(userName,idnum);
					System.out.println("예약이 되어있지 않다면  1번 입력\n예약이 되어있다면 2번 입력  ");
					int inputNum3 = Integer.parseInt(br.readLine());
					if(inputNum3 == 1) {
						//x
						System.out.println("1.나이로 맞을 수 있는 백신확인");
						System.out.println("2.주소로 병원 확인");
						System.out.println("3.나이로 맞을 수 있는 백신확인");
						int inputNum4 = Integer.parseInt(br.readLine());
						
						if(inputNum4 == 1) {
							System.out.println("나이를 입력하세요");
							int targetAge = Integer.parseInt(br.readLine());
							vc.getVaccineByTargetAge(targetAge);
						}else if(inputNum4 == 2) {
							System.out.println("지역을 입력하세요");
							String loc = br.readLine();
							hc.getHospitalLocation(loc);
						}else if(inputNum4 == 3) {
							//- 그 병원 중에 위의 백신이 있는 곳 출력
							//=> 선택 <백신 종류, 병원, 접종날짜 선택> => 예약자 정보 추가 + 해당 병원 백신 수량 조절
						}else {
							//잘못된 입력
						}
							
					}else if(inputNum3 == 2) {
						//o
						System.out.println("1.백신 날짜 확인");
						System.out.println("2.백신 날짜 수정");
						System.out.println("3.주소 수정");
						System.out.println("4.예약 취소");
						System.out.println("숫자를 입력해주세요");
						int inputNum4 = Integer.parseInt(br.readLine());
						
						if(inputNum4 == 1) {
							System.out.println("1.백신 날짜 확인");
							uc.nextVaccineDate(idnum);
						}else if(inputNum4 == 2) {
							System.out.println("2.백신 날짜 수정");
							//updateUserDate(int idNum, int dateNum, String date)
//							uc.updateUserDate(idnum,,date);
						}else if(inputNum4 == 3) {
							System.out.println("3.주소 수정");
							String loc = br.readLine();
							uc.updateUserAddress(idnum, loc);
						}else if(inputNum4 == 4){
							System.out.println("4.예약 취소");
							uc.deleteUser(idnum);
						}else {
							//잘못된 입력
						}
						
					}else {
						//잘못된 입력
					}
				}else if(inputNum == 2) {
					System.out.println("===================검색 분야를 선택하세요.===================");
					System.out.println("1) 병원 \n 2) 백신");	
					int inputNum2 = Integer.parseInt(br.readLine());
					
					if(inputNum2 == 1) {
						// 병원
						//해당병원만 조회 / 전체 조회
						System.out.println("1.병원 이름으로 조회");
						System.out.println("2.전체 병원  조회");
						System.out.println("3.병원 삭제");
						System.out.println("4.병원 지역 수정");
						System.out.println("5.병원 백신 수량 수정");
						System.out.println("6.해당 병원 예약자 전체 검색");
						int inputNum3 = Integer.parseInt(br.readLine());
						
						if(inputNum3 == 1) {
							System.out.println("1.병원 이름으로 조회");
							System.out.println("병원 이름을 입력하세요");
							String hospitalName = br.readLine();
							hc.getHospital(hospitalName);
						}else if(inputNum3 == 2) {
							System.out.println("2.전체 병원  조회");
							hc.getAllHospital();
						}else if(inputNum3 == 3) {
							System.out.println("3.병원 삭제");
							System.out.println("병원 이름을 입력하세요");
							String hospitalName = br.readLine();
							hc.deleteHospital(hospitalName);
						}else if(inputNum3 == 4) {
							System.out.println("4.병원 지역 수정");
							System.out.println("병원 이름을 입력하세요");
							String hospitalName = br.readLine();
							System.out.println("병원 지역을 입력하세요");
							String loc = br.readLine();
							hc.updateHospitalLocation(hospitalName, loc);
						}else if(inputNum3 == 5) {
							System.out.println("5.병원 백신 수량 수정");
							System.out.println("1.전체 백신 수정");
							System.out.println("2.특정 백신 수정");
							int inputNum4 = Integer.parseInt(br.readLine());
							
							if(inputNum4 == 1) {
								System.out.println("1.전체 백신 수정");
								
								System.out.println("병원 이름을 입력하세요");
								String hospitalName = br.readLine();
								System.out.println("화이자의 개수를 입력하세요");
								int pfizer = Integer.parseInt(br.readLine());
								System.out.println("모더나의 개수를 입력하세요");
								int moderna = Integer.parseInt(br.readLine());
								System.out.println("AZ의 개수를 입력하세요");
								int az = Integer.parseInt(br.readLine());
								
								hc.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
								
							}else if(inputNum4 == 2) {
								System.out.println("2.특정 백신 수정");
								
								System.out.println("병원 이름을 입력하세요");
								String hospitalName = br.readLine();
								System.out.println("백신의 이름을 입력하세요");
								String vaccineName = br.readLine();
								System.out.println("백신의 개수를 입력하세요");
								int num = Integer.parseInt(br.readLine());
								
								hc.updateHospitalVaccine(hospitalName, vaccineName, num);
							}else {
								//잘못된 입력
							}
						}else if(inputNum3 == 6) {
							System.out.println("6.해당 병원 예약자 전체 검색");
							System.out.println("병원 이름을 입력하세요");
							String hospitalName = br.readLine();
							
							uc.getAllUsersByHospital(hospitalName);
						}else {
							//잘못된 입력
						}
						
						
					}else if(inputNum2 == 2) {
						//백신
						System.out.println("1.백신 전체 검색");
						System.out.println("2.백신 수정");
						System.out.println("3.백신 접종 연령 수정");
						System.out.println("숫자를 입력하세요");
						int inputNum3 = Integer.parseInt(br.readLine());
						
						if(inputNum3 == 1) {
							System.out.println("1.백신 전체 검색");
							vc.getAllVaccine();
							
						}else if (inputNum3 == 2) {
							System.out.println("2.백신 수정");
							System.out.println("백신 이름을 입력하세요");
							String vaccineName = br.readLine();
							System.out.println("백신 플랫폼 입력하세요");
							String platform = br.readLine();
							System.out.println("백신 온도를 입력하세요");
							String temper = br.readLine();
							
							vc.updateVaccine(vaccineName, platform, temper);
						}else if (inputNum3 == 3) {
							System.out.println("3.백신 접종 연령 수정");
							System.out.println("백신 이름을 입력하세요");
							String vaccineName = br.readLine();
							System.out.println("백신 연령을 입력하세요");
							int targetAge = Integer.parseInt(br.readLine());
							
							vc.updateVaccineTargetAge(vaccineName, targetAge);
							
						}else {
							//잘못된 입력
						}
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
