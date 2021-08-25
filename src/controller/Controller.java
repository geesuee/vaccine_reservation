package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.entity.Hospital;
import model.entity.Users;
import model.entity.Vaccine;

public class Controller {
	
	private static HospitalController hc = HospitalController.getInstance();
	private static UsersController uc = UsersController.getInstance();
	private static VaccineController vc = VaccineController.getInstance();
	
	public static void vaccineStart() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String esc = null;
		int inputNum = 100;

		try {
			while (esc == null) {
				if( inputNum == 100) {
					System.out.println("┌───────────────사용자 유형을 선택하세요.─────┐");
					System.out.println("│1) 접종 예약자                                               │");				
					System.out.println("│2) 관리자                                                     │");				
					System.out.println("│0) 시스템 끄기                                               │");				
					System.out.println("└──────────────────────────────────────┘");
					
					inputNum = Integer.parseInt(br.readLine());
				}else if(inputNum == 1) {
					System.out.println("1) 접종 예약자를 선택하셨습니다.");
					
					System.out.print("예약 조회를 위해 이름을 입력해주세요 : ");
					String userName = br.readLine();
					
					System.out.print("예약 조회를 위해 주민등록 번호를 입력해주세요 : ");
					int idnum = Integer.parseInt(br.readLine());
					
					boolean ub = uc.getUserBoolean(idnum);
					
					if(ub == true) {
						int reservation = 100;
						
						while(true) {
							if(reservation == 100) {
								System.out.println("1.백신 확인");
								System.out.println("2.병원 확인");
								System.out.println("3.접종 날짜 확인");
								System.out.println("4.접종 날짜 수정");
								System.out.println("5.주소 수정");
								System.out.println("6.예약 취소");
								System.out.println("숫자를 입력해주세요");
								reservation = Integer.parseInt(br.readLine());
							}else if(reservation == 1) {
								vc.getVaccineByName(uc.getUser2(idnum).getVaccine().getVaccineName());
								reservation = 100;
							}else if(reservation == 2) {
								hc.getHospital(uc.getUser2(idnum).getHospital().getHospitalName());
								reservation = 100;
							}else if(reservation == 3) {
								System.out.println("1차 접종 날짜 : " + uc.getUser2(idnum).getDate1());
								System.out.println("2차 접종 날짜 : " + uc.getUser2(idnum).getDate2());
								reservation = 100;
							}else if(reservation == 4) {
								System.out.print("접종 날짜를 입력해주세요 : ");
								String date = br.readLine();
								uc.updateUserDate(idnum, 1, date);
							}else if(reservation == 5) {
								System.out.print("주소를 입력해주세요 : ");
								String add = br.readLine();
								uc.updateUserAddress(idnum, add);
							}else if(reservation == 6) {
								uc.deleteUser(idnum);
							}else if(reservation == 0) {
								inputNum = 100;
								break;
							}else {
								System.out.println("잘못 입력하셨습니다");
							}
						}
					}else {
						System.out.print("나이를 입력해주세요 : ");
						int age = Integer.parseInt(br.readLine());
						System.out.print("주소를 입력해주세요 : ");
						String add = br.readLine();
						
						List<Vaccine> vac = vc.getVaccineAge(age);
						System.out.println("맞을 수 있는 백신의 종류입니다");
						vac.forEach(v -> System.out.println(v));
						
						ArrayList<Hospital> hos = hc.getReservationHospital(vac, add);
						
						hos.forEach(v -> System.out.println(v));
						System.out.print("병원을 선택해주세요 : ");
						String sHos = br.readLine();
						System.out.print("백신을 선택해주세요 : ");
						String sVac = br.readLine();
						System.out.print("1차 접종날짜를 선택해주세요 : ");
						String sDate = br.readLine();
						
						Users addUser = new Users(idnum, userName, age, add, sDate, uc.nextVaccineDate(sDate, sVac), vc.getVaccineName(sVac), hc.getHospitalName(sHos));
						
						uc.addUser(addUser);
						
						inputNum = 100;
					}
				}else if(inputNum == 2) {
					int search = 100;
					
					while(true) {
						if(search == 100) {
							System.out.println("===================검색 분야를 선택하세요.===================");
							System.out.println("1) 병원 \n2) 백신 \n3) 예약자 명단");	
							search = Integer.parseInt(br.readLine());
						
						}else if(search == 1) {
							// 병원
							//해당병원만 조회 / 전체 조회
							System.out.println("1.병원 이름으로 조회");
							System.out.println("2.전체 병원  조회");
							System.out.println("3.병원 추가");
							System.out.println("4.병원 삭제");
							System.out.println("5.병원 지역 수정");
							System.out.println("6.병원 백신 수량 수정");
							int searchH = Integer.parseInt(br.readLine());
							
							if(searchH == 1) {
								System.out.print("병원 이름을 입력하세요 : ");
								String hospitalName = br.readLine();
								hc.getHospital(hospitalName);
							}else if(searchH == 2) {
								hc.getAllHospital();
							}else if(searchH == 3) {
								System.out.print("병원 이름을 입력해주세요 : ");
								String hospitalName = br.readLine();
								System.out.print("지역을 입력해주세요 : ");
								String location = br.readLine();
								System.out.print("보유하고 있는 화이자의 개수를 입력해주세요 : ");
								int pfizer = Integer.parseInt(br.readLine());
								System.out.print("보유하고 있는 모더나의 개수를 입력해주세요 : ");
								int moderna = Integer.parseInt(br.readLine());
								System.out.print("보유하고 있는 AZ의 개수를 입력해주세요 : ");
								int az = Integer.parseInt(br.readLine());
								
								Hospital hospital = new Hospital(hospitalName, location, pfizer, moderna, az);
								
								hc.addHospital(hospital);
							}else if(searchH == 4) {
								System.out.print("병원 이름을 입력하세요 : ");
								String hospitalName = br.readLine();
								hc.deleteHospital(hospitalName);
							}else if(searchH == 5) {
								System.out.print("병원 이름을 입력하세요 : ");
								String hospitalName = br.readLine();
								System.out.print("병원 지역을 입력하세요 : ");
								String loc = br.readLine();
								hc.updateHospitalLocation(hospitalName, loc);
							}else if(searchH == 6) {
								System.out.println("1.전체 백신 수정");
								System.out.println("2.특정 백신 수정");
								int upVac = Integer.parseInt(br.readLine());
								
								if(upVac == 1) {
									System.out.print("병원 이름을 입력하세요 : ");
									String hospitalName = br.readLine();
									System.out.print("화이자의 개수를 입력하세요 : ");
									int pfizer = Integer.parseInt(br.readLine());
									System.out.print("모더나의 개수를 입력하세요 : ");
									int moderna = Integer.parseInt(br.readLine());
									System.out.print("AZ의 개수를 입력하세요 : ");
									int az = Integer.parseInt(br.readLine());
									
									hc.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
									
								}else if(upVac == 2) {
									System.out.print("병원 이름을 입력하세요 : ");
									String hospitalName = br.readLine();
									System.out.print("백신의 이름을 입력하세요 : ");
									String vaccineName = br.readLine();
									System.out.print("백신의 개수를 입력하세요 : ");
									int num = Integer.parseInt(br.readLine());
									
									hc.updateHospitalVaccine(hospitalName, vaccineName, num);
								}else {
									System.out.println("잘못 입력하셨습니다");
								}
							}else if(searchH == 0) {
								break;
							}else {
								System.out.println("잘못 입력하셨습니다");
							}
						}else if(search == 2) {
							System.out.println("1.백신 전체 검색");
							System.out.println("2.백신 수정");
							System.out.println("3.백신 접종 연령 수정");
							System.out.println("숫자를 입력하세요");
							int searchV = Integer.parseInt(br.readLine());
							
							if(searchV == 1) {
								vc.getAllVaccine();
							}else if (searchV == 2) {
								System.out.print("백신 이름을 입력하세요 : ");
								String vaccineName = br.readLine();
								System.out.print("백신 플랫폼 입력하세요 : ");
								String platform = br.readLine();
								System.out.print("백신 온도를 입력하세요 : ");
								String temper = br.readLine();
								
								vc.updateVaccine(vaccineName, platform, temper);
							}else if (searchV == 3) {
								System.out.print("백신 이름을 입력하세요 : ");
								String vaccineName = br.readLine();
								System.out.print("백신 연령을 입력하세요 : ");
								int targetAge = Integer.parseInt(br.readLine());
								
								vc.updateVaccineTargetAge(vaccineName, targetAge);
							}else if(searchV == 0){
								break;
							}else {
								System.out.println("잘못 입력하셨습니다");
							}
						}else if(search == 3){
							System.out.println("1.전체 예약자 명단 \n2.특정병원예약자 명단");
							int people = Integer.parseInt(br.readLine());
							
							if(people == 1) {
								uc.getAllUsers();
							}else if(people == 2) {
								System.out.print("병원 이름을 입력해주세요 : ");
								String hosName = br.readLine();
								
								uc.getAllUsersByHospital(hosName);
							}else if(people == 0) {
								break;
							}else {
								System.out.println("잘못 입력하셨습니다");
							}
						}else if(search == 0){
							inputNum = 100;
							break;
						}else {
							System.out.println("잘못 입력하셨습니다");
						}
					}
				}else if(inputNum == 0) {
					break;
				}else {
					System.out.println("잘못 입력하셨습니다");
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
