package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
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
			loop1 :
			while (esc == null) {
				if( inputNum == 100) {
					System.out.println("\n ■■■■■■■■■■ 사용자 유형을 선택하세요 ■■■■■■■■■■");
					System.out.println("\t 1) 접종 예약자");				
					System.out.println("\t 2) 관리자");				
					System.out.println("\t 0) 시스템 끄기");				
					System.out.println("\n ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					
					inputNum = Integer.parseInt(br.readLine());
					
				// 접종 예약자
				}else if(inputNum == 1) {
					System.out.println("\n << 접종 예약자를 선택하셨습니다. >>");
					
					System.out.print("예약 조회를 위해 이름을 입력해주세요 : ");
					String userName = br.readLine();
					
					System.out.print("예약 조회를 위해 주민등록 번호를 입력해주세요 : ");
					int idnum = Integer.parseInt(br.readLine());
					
					boolean ub = uc.getUserBoolean(idnum);
					
					if(ub == true) {
						int reservation = 100;
						
						while(true) {
							if(reservation == 100) {
								System.out.println("\n===================하고싶은 작업을 선택해주세요===================");
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
								reservation = 100;
							}else if(reservation == 5) {
								System.out.print("주소를 입력해주세요 : ");
								String add = br.readLine();
								uc.updateUserAddress(idnum, add);
								reservation = 100;
							}else if(reservation == 6) {
								String hos = uc.getUser2(idnum).getHospital().getHospitalName();
								String vac = uc.getUser2(idnum).getVaccine().getVaccineName();
								hc.updateHospitalVaccine(hos, vac, hc.getHospitalVaccineTotal(hos, vac)+1);
								uc.deleteUser(idnum);
								inputNum = 100;
								continue loop1;
							}else if(reservation == 0) {
								inputNum = 100;
								continue loop1;
							}else {
								System.out.println("잘못 입력하셨습니다");
								reservation = 100;
							}
						}
					}else {
						boolean va = false;
						System.out.print("나이를 입력해주세요 : ");
						int age = Integer.parseInt(br.readLine());
						System.out.print("주소를 입력해주세요 : ");
						String add = br.readLine();
						
						List<Vaccine> vac = vc.getVaccineAge(age);
						System.out.println("\n맞을 수 있는 백신의 종류입니다");
						vac.forEach(v -> System.out.println(v));
						HashSet<Hospital> hos = hc.getReservationHospital(vac, add);
						if(hos.size() == 0) {
							System.out.println("\n예약할 수 있는 병원이 없습니다");
							inputNum = 100;
							continue;
						}
						
						System.out.println("\n현재 예약 가능한 병원입니다");
						hos.forEach(v -> System.out.println(v));
						
						System.out.print("\n병원을 선택해주세요 : ");
						String sHos = br.readLine();
						
						for(Hospital h : hos) {
							if(!h.getHospitalName().equals(sHos)) {
								System.out.println("\n해당 병원은 선택이 불가한 병원입니다");
								inputNum = 100;
								continue loop1;
							}
						}
						
						System.out.print("백신을 선택해주세요 : ");
						String sVac = br.readLine();
						
						for(Vaccine v : vac) {
							if(v.getVaccineName().equals(sVac) && hc.getHospitalVaccineTotal(sHos, sVac) > 0){
								va = true;
								break;
							}
						}
						
						if(va == true) {
							System.out.print("1차 접종날짜를 선택해주세요 : ");
							String sDate = br.readLine();
							
							Users addUser = new Users(idnum, userName, age, add, sDate, uc.nextVaccineDate(sDate, sVac), vc.getVaccineName(sVac), hc.getHospitalName(sHos));
						
							uc.addUser(addUser);
							hc.updateHospitalVaccine(sHos, sVac, hc.getHospitalVaccineTotal(sHos, sVac)-1);
						}else{
							System.out.println("\n해당 백신은 예약자가 맞을 수 없는 백신입니다");
						}
						
						inputNum = 100;
					}
					
				// 관리자
				}else if(inputNum == 2) {
					int search = 100;
					System.out.println("\n << 관리자를 선택하셨습니다. >>");
					
					while(true) {
						if(search == 100) {
							System.out.println("\n===================검색 분야를 선택해주세요===================");
							System.out.println("1) 병원 \n2) 백신 \n3) 예약자 명단");	
							search = Integer.parseInt(br.readLine());
						
						}else if(search == 1) {
							System.out.println("\n===================하고싶은 작업을 선택해주세요===================");
							System.out.println("1.병원 이름으로 조회");
							System.out.println("2.전체 병원  조회");
							System.out.println("3.병원 추가");
							System.out.println("4.병원 삭제");
							System.out.println("5.병원 지역 수정");
							System.out.println("6.병원 백신 수량 수정");
							int searchH = Integer.parseInt(br.readLine());
							
							if(searchH == 1) {
								System.out.print("병원 이름을 입력해주세요 : ");
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
								System.out.print("병원 이름을 입력해주세요 : ");
								String hospitalName = br.readLine();
								
								if(uc.getAllUsersByHospitalBoolean(hospitalName) != true) {
									hc.deleteHospital(hospitalName);
								}else {
									System.out.println("\n현재 예약이 있는 병원이기 때문에 삭제할 수 없습니다");
								}
							}else if(searchH == 5) {
								System.out.print("병원 이름을 입력해주세요 : ");
								String hospitalName = br.readLine();
								System.out.print("지역을 입력해주세요 : ");
								String loc = br.readLine();
								hc.updateHospitalLocation(hospitalName, loc);
							}else if(searchH == 6) {
								System.out.println("1.전체 백신 수정");
								System.out.println("2.특정 백신 수정");
								int upVac = Integer.parseInt(br.readLine());
								
								if(upVac == 1) {
									System.out.print("병원 이름을 입력해주세요 : ");
									String hospitalName = br.readLine();
									System.out.print("화이자의 개수를 입력해주세요 : ");
									int pfizer = Integer.parseInt(br.readLine());
									System.out.print("모더나의 개수를 입력해주세요 : ");
									int moderna = Integer.parseInt(br.readLine());
									System.out.print("AZ의 개수를 입력해주세요 : ");
									int az = Integer.parseInt(br.readLine());
									
									hc.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);
									
								}else if(upVac == 2) {
									System.out.print("병원 이름을 입력해주세요 : ");
									String hospitalName = br.readLine();
									System.out.print("백신의 이름을 입력해주세요 : ");
									String vaccineName = br.readLine();
									System.out.print("백신의 개수를 입력해주세요 : ");
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
							System.out.println("\n===================하고싶은 작업을 선택해주세요===================");
							System.out.println("1.백신 전체 검색");
							System.out.println("2.백신 수정");
							System.out.println("3.백신 접종 연령 수정");
							System.out.println("숫자를 입력하세요");
							int searchV = Integer.parseInt(br.readLine());
							
							if(searchV == 1) {
								vc.getAllVaccine();
							}else if (searchV == 2) {
								System.out.print("백신 이름을 입력해주세요 : ");
								String vaccineName = br.readLine();
								System.out.print("백신 플랫폼 입력해주세요 : ");
								String platform = br.readLine();
								System.out.print("백신 온도를 입력해주세요 : ");
								String temper = br.readLine();
								
								vc.updateVaccine(vaccineName, platform, temper);
							}else if (searchV == 3) {
								System.out.print("백신 이름을 입력해주세요 : ");
								String vaccineName = br.readLine();
								System.out.print("백신 연령을 입력해주세요 : ");
								int targetAge = Integer.parseInt(br.readLine());
								
								vc.updateVaccineTargetAge(vaccineName, targetAge);
							}else if(searchV == 0){
								break;
							}else {
								System.out.println("잘못 입력하셨습니다");
							}
						}else if(search == 3){
							System.out.println("\n===================하고싶은 작업을 선택해주세요===================");
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
	
}
