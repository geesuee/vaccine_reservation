package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;

import model.dao.HospitalDAO;
import model.dao.UsersDAO;
import model.dao.VaccineDAO;
import model.entity.Hospital;
import model.entity.Users;
import model.entity.Vaccine;
import view.EndView;

public class Controller {

	private static Controller instance = new Controller();

	private Controller() {
	};

	public static Controller getInstance() {
		return instance;
	}

	public void vaccineStart() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String esc = null;
		int inputNum = 100;

		try {
			loop1: while (esc == null) {
				if (inputNum == 100) {
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println("\n>> 사용자 유형을 선택하세요");
					System.out.println("\t 1) 접종 예약자");
					System.out.println("\t 2) 관리자");
					System.out.println("\t 0) 시스템 끄기");
					System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

					inputNum = Integer.parseInt(br.readLine());

					// 접종 예약자
				} else if (inputNum == 1) {
					System.out.println("\n << 접종 예약자를 선택하셨습니다. >>");

					System.out.print("\n예약 조회를 위해 이름을 입력해주세요 : ");
					String userName = br.readLine();

					System.out.print("예약 조회를 위해 주민등록 번호를 입력해주세요 : ");
					int idNum = Integer.parseInt(br.readLine());

					Users user = null;

					try {
						user = UsersDAO.getUser(userName, idNum);
					} catch (Exception e) {
//						e.printStackTrace();
					}

					if (user != null) { // 접종 예약 정보 존재
						loop1a: while (true) {
							System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
							System.out.println("\n>> 실행할 기능 번호를 선택하세요");
							System.out.println("\t 1) 백신 확인");
							System.out.println("\t 2) 병원 확인");
							System.out.println("\t 3) 접종 날짜 확인");
							System.out.println("\t 4) 접종 날짜 수정");
							System.out.println("\t 5) 주소 수정");
							System.out.println("\t 6) 예약 취소");
							System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

							int function = Integer.parseInt(br.readLine());

							switch (function) {
							case 0:
								inputNum = 100;
								continue loop1;
							case 1:
								System.out.println("\n========== 1) 백신 확인 ==========");
								getVaccine(user.getVaccine().getVaccineName());
								continue loop1a;
							case 2:
								System.out.println("\n========== 2) 병원 확인 ==========");
								getHospital(user.getHospital().getHospitalName());
								continue loop1a;
							case 3:
								System.out.println("\n========== 3) 접종 날짜 확인 ==========");
								getVaccineDate(idNum);
								continue loop1a;
							case 4:
								System.out.println("\n========== 4) 접종 날짜 수정 ==========");
								getVaccineDate(idNum);

								System.out.println("\n※ 접종 날짜 수정은 <<일정 연기>>만 가능합니다.");
								System.out.println("※ 당일 예약 연기는 불가합니다.");

								System.out.print("\n연기할 백신 접종 차시를 입력하세요(1 or 2) : ");
								int dateNum = Integer.parseInt(br.readLine());

								System.out.print("연기 일자를 다음 양식과 같이 입력하세요(20210901) : ");
								String date = br.readLine();

								updateUserDate(idNum, dateNum, date);
								continue loop1a;
							case 5:
								System.out.println("\n========== 5) 주소 수정 ==========");
								System.out.print("변경된 주소를 입력해주세요 : ");
								String address = br.readLine();

								updateUserAddress(idNum, address);
								continue loop1a;
							case 6:
								System.out.println("\n========== 6) 예약 취소 ==========");
								deleteUser(idNum);

								String hospitalName = user.getHospital().getHospitalName();
								String vaccineName = user.getVaccine().getVaccineName();
								updateReservationVaccine(hospitalName, vaccineName, 1);

								inputNum = 100;
								continue loop1;
							default:
								System.out.println("\n << 잘못된 값을 입력하셨습니다.>>");
								continue loop1a;
							}
						}

					} else { // 접종 예약 정보 미존재
						System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
						System.out.println("\n접종 예약 정보가 존재하지 않습니다.");
						System.out.println("접종 예약을 시작합니다.");
						System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

						System.out.print("나이를 입력해주세요 : ");
						int age = Integer.parseInt(br.readLine());

						System.out.print("주소를 입력해주세요 : ");
						String address = br.readLine();

						System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
						System.out.println("\n접종 가능한 백신 리스트입니다.");
						List<Vaccine> vaccineList = VaccineDAO.getVaccineByTargetAge(age);
						vaccineList.forEach(System.out::println);

						HashSet<Hospital> hospitalSet = getReservationHospital(vaccineList, address);
						if (hospitalSet.size() == 0) {
							System.out.println("\n해당 백신을 보유한 인근 병원이 없습니다.");
							inputNum = 100;
							continue loop1;
						}
						System.out.println("\n예약 가능한 인근 병원 리스트입니다.");
						hospitalSet.forEach(System.out::println);
						System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

						System.out.print("\n병원을 선택해주세요 : ");
						String sHospital = br.readLine();
						Hospital selectHospital = HospitalDAO.getHospitalByName(sHospital);

						
						
						for (Hospital h : hospitalSet) {
							if (!h.getHospitalName().equals(sHospital)) {
								System.out.println("\n해당 병원은 선택이 불가합니다.");
								inputNum = 100;
								continue loop1;
							}
						}
						
						System.out.print("백신을 선택해주세요 : ");
						String sVaccine = br.readLine();
						Vaccine selectVaccine = VaccineDAO.getVaccine(sVaccine);

						boolean check = false;
						
						for(Vaccine v : vaccineList) {
							if(v.getVaccineName().equals(sVaccine) && HospitalDAO.getHospitalVaccine(sHospital, sVaccine) > 0) {
								check = true;
								break;
							}
						}
						
						if(check) {
							System.out.println("※ 당일 예약은 불가합니다. 오늘 이후의 날짜를 입력해주세요");
							System.out.print("1차 접종날짜를 선택해주세요 : ");
							String sDate1 = br.readLine();
							String sDate2 = UsersDAO.calNextVaccineDate(sDate1, selectVaccine.getPeriod());
							Users addUser = null;

							try {
								addUser = new Users(idNum, userName, age, address, sDate1, sDate2, selectVaccine,
										selectHospital);
							} catch (Exception e) {
//								e.printStackTrace();
							}

							addUser(addUser);

							updateReservationVaccine(sHospital, sVaccine, -1);
						}else {
							EndView.errorMessage("\n해당 백신은 예약이 불가합니다.");
						}
						
						inputNum = 100;
						
					}

					// 관리자
				} else if (inputNum == 2) {
					System.out.println("\n << 관리자를 선택하셨습니다. >>");
					int search = 100;

					while (true) {
						if (search == 100) {
							System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
							System.out.println("\n>> 검색할 정보를 선택하세요");
							System.out.println("\t 1) 병원 관련 정보 검색");
							System.out.println("\t 2) 백신 관련 정보 검색");
							System.out.println("\t 3) 접종 예약자 명단 검색");
							System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

							search = Integer.parseInt(br.readLine());

						} else if (search == 1) {
							System.out.println("\n========== 1) 병원 관련 정보 검색 ==========");
							System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
							System.out.println("\n>> 실행할 기능 번호를 선택하세요");
							System.out.println("\t 1) 병원 정보 전체 조회");
							System.out.println("\t 2) 병원 정보 단일 조회");
							System.out.println("\t 3) 병원 추가");
							System.out.println("\t 4) 병원 삭제");
							System.out.println("\t 5) 병원 위치 수정");
							System.out.println("\t 6) 병원 백신 수량 수정");
							System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

							int searchH = Integer.parseInt(br.readLine());

							if (searchH == 0) {
								break;

							} else if (searchH == 1) {
								System.out.println("\n========== 1-1) 병원 정보 전체 조회 ==========");
								getAllHospital();

							} else if (searchH == 2) {
								System.out.println("\n========== 1-2) 병원 정보 단일 조회 ==========");
								System.out.print("병원 이름을 입력해주세요 : ");
								String hospitalName = br.readLine();
								getHospital(hospitalName);

							} else if (searchH == 3) {
								System.out.println("\n========== 1-3) 병원 추가 ==========");
								System.out.print("병원 이름을 입력해주세요 : ");
								String hospitalName = br.readLine();
								System.out.print("병원 위치를 입력해주세요 : ");
								String location = br.readLine();
								System.out.print("보유하고 있는 화이자의 개수를 입력해주세요 : ");
								int pfizer = Integer.parseInt(br.readLine());
								System.out.print("보유하고 있는 모더나의 개수를 입력해주세요 : ");
								int moderna = Integer.parseInt(br.readLine());
								System.out.print("보유하고 있는 AZ의 개수를 입력해주세요 : ");
								int az = Integer.parseInt(br.readLine());
								
								Hospital hospital = null;

								try {
									hospital = new Hospital(hospitalName, location, pfizer, moderna, az);
								} catch (Exception e) {
//									e.printStackTrace();
								}
								
								addHospital(hospital);

							} else if (searchH == 4) {
								System.out.println("\n========== 1-4) 병원 삭제 ==========");
								System.out.print("삭제할 병원 이름을 입력해주세요 : ");
								String hospitalName = br.readLine();

								if (UsersDAO.getAllUsersByHospital(hospitalName).size() > 0) {
									EndView.errorMessage("\n예약이 존재하는 병원은 삭제할 수 없습니다.");
								} else {
									deleteHospital(hospitalName);
								}

							} else if (searchH == 5) {
								System.out.println("\n========== 1-5) 병원 위치 수정 ==========");
								System.out.print("위치를 수정할 병원 이름을 입력해주세요 : ");
								String hospitalName = br.readLine();
								System.out.print("변경된 위치를 입력해주세요 : ");
								String loc = br.readLine();

								updateHospitalLocation(hospitalName, loc);

							} else if (searchH == 6) {
								System.out.println("\n========== 1-6) 병원 백신 수량 수정 ==========");
								System.out.println("\n>> 실행할 기능 번호를 선택하세요");
								System.out.println("\t 1)전체 백신 수량 수정");
								System.out.println("\t 2)특정 백신 수량 수정");
								System.out.println("\n==============================================");

								int updateNum = Integer.parseInt(br.readLine());

								if (updateNum == 1) {
									System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
									System.out.println(">> 전체 백신 수량 수정을 시작합니다.");
									System.out.print("\n병원 이름을 입력해주세요 : ");
									String hospitalName = br.readLine();
									System.out.print("보유 화이자 개수를 입력해주세요 : ");
									int pfizer = Integer.parseInt(br.readLine());
									System.out.print("보유 모더나 개수를 입력해주세요 : ");
									int moderna = Integer.parseInt(br.readLine());
									System.out.print("보유 AZ 개수를 입력해주세요 : ");
									int az = Integer.parseInt(br.readLine());

									updateHospitalAllVaccine(hospitalName, pfizer, moderna, az);

									System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

								} else if (updateNum == 2) {
									System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
									System.out.println(">> 특정 백신 수량 수정을 시작합니다.");
									System.out.print("병원 이름을 입력해주세요 : ");
									String hospitalName = br.readLine();
									System.out.print("수량을 수정할 백신의 이름을 입력해주세요 : ");
									String vaccineName = br.readLine();
									System.out.print("해당 백신의 개수를 입력해주세요 : ");
									int num = Integer.parseInt(br.readLine());

									updateHospitalVaccine(hospitalName, vaccineName, num);

									System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

								} else {
									EndView.errorMessage("잘못된 값을 입력하였습니다.");
								}
							} else {
								EndView.errorMessage("잘못된 값을 입력하였습니다.");
							}

						} else if (search == 2) {
							System.out.println("\n========== 2) 백신 관련 정보 검색 ==========");
							System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
							System.out.println("\n>> 실행할 기능 번호를 선택하세요");
							System.out.println("\t 1) 백신 정보 전체 조회");
							System.out.println("\t 2) 백신 정보 단일 조회");
							System.out.println("\t 3) 백신 플랫폼, 보관 온도 수정");
							System.out.println("\t 4) 백신 접종 연령 수정");
							System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

							int searchV = Integer.parseInt(br.readLine());
							if (searchV == 0) {
								break;

							} else if (searchV == 1) {
								System.out.println("\n========== 2-1) 백신 정보 전체 조회 ==========");
								getAllVaccine();

							} else if (searchV == 2) {
								System.out.println("\n========== 2-2) 백신 정보 단일 조회 ==========");
								System.out.print("백신 이름을 입력해주세요 : ");
								String vaccineName = br.readLine();
								getVaccine(vaccineName);

							} else if (searchV == 3) {
								System.out.println("\n========== 2-3) 백신 플랫폼, 보관 온도 수정 ==========");
								System.out.print("수정할 백신 이름을 입력해주세요 : ");
								String vaccineName = br.readLine();
								System.out.print("백신 플랫폼 입력해주세요 : ");
								String platform = br.readLine();
								System.out.print("백신 온도를 입력해주세요 : ");
								String temper = br.readLine();

								updateVaccine(vaccineName, platform, temper);

							} else if (searchV == 4) {
								System.out.println("\n========== 2-4) 백신 접종 연령 수정 ==========");
								System.out.print("수정할 백신 이름을 입력해주세요 : ");
								String vaccineName = br.readLine();
								System.out.print("백신 접종 연령을 입력해주세요 : ");
								int targetAge = Integer.parseInt(br.readLine());

								updateVaccineTargetAge(vaccineName, targetAge);
							} else {
								EndView.errorMessage("잘못된 값을 입력하였습니다.");
							}

						} else if (search == 3) {
							System.out.println("\n========== 3) 접종 예약자 명단 검색 ==========");
							System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
							System.out.println("\n>> 실행할 기능 번호를 선택하세요");
							System.out.println("\t 1) 접종 예약자 명단 전체 조회");
							System.out.println("\t 2) 특정 병원 접종 예약자 명단 조회");
							System.out.println("\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

							int searchU = Integer.parseInt(br.readLine());

							if (searchU == 0) {
								break;

							} else if (searchU == 1) {
								System.out.println("\n========== 3-1) 접종 예약자 명단 전체 조회 ==========");
								getAllUsers();

							} else if (searchU == 2) {
								System.out.println("\n========== 3-2) 특정 병원 접종 예약자 명단 조회 ==========");
								System.out.print("예약자 명단을 조회할 병원 이름을 입력해주세요 : ");
								String hospitalName = br.readLine();
								getAllUsersByHospital(hospitalName);

							} else {
								EndView.errorMessage("잘못된 값을 입력하였습니다.");
							}

						} else if (search == 0) {
							inputNum = 100;
							break;
						} else {
							EndView.errorMessage("잘못된 값을 입력하였습니다.");
						}
					}

				} else if (inputNum == 0) {
					System.out.println("예약 서비스를 종료하였습니다.");
					break;
				} else {
					EndView.errorMessage("잘못된 값을 입력하였습니다.");
					inputNum = 100;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
	}

//	--------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Hospital Controller
	 * 
	 * - getAllHospital() 
	 * - getHospital() 
	 * - getReservationHospital()
	 * - addHospital()
	 * - updateHospitalLocation() 
	 * - updateHospitalAllVaccine()
	 * - updateHospitalVaccine() 
	 * - updateReservationVaccine()
	 * - deleteHospital()
	 */

	// 병원 정보 다중(전체) 조회
	public static void getAllHospital() {
		List<Hospital> hospitalList = HospitalDAO.getAllHospital();

		if (hospitalList.size() > 0) {
			EndView.showAll(hospitalList);
		} else {
			EndView.errorMessage("병원 정보가 존재하지 않습니다.");
		}
	}

	// 병원 정보 단일 조회
	public static void getHospital(String hospitalName) {
		if (hospitalName != null && !hospitalName.equals("")) {
			Hospital hospital = HospitalDAO.getHospitalByName(hospitalName);

			if (hospital != null) {
				EndView.showOne(hospital);
			} else {
				EndView.errorMessage("해당 이름의 병원 정보가 존재하지 않습니다.");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 예약 가능한 병원 조회 및 반환
	public static HashSet<Hospital> getReservationHospital(List<Vaccine> vaccineList, String location) {
		HashSet<Hospital> hospitalSet = new HashSet<>();

		List<Hospital> hospital1 = HospitalDAO.getHospitalByLocation(location);
		List<Hospital> hospital2 = HospitalDAO.getHospitalByVaccine(vaccineList);

		for (Hospital h : hospital1) {
			for (int i = 0; i < hospital2.size(); i++) {
				if (h.getHospitalName().equals(hospital2.get(i).getHospitalName())) {
					hospitalSet.add(h);
				}
			}
		}
		return hospitalSet;
	}

	// 병원 추가
	public static void addHospital(Hospital hospital) {
		if (hospital != null) {

			if (HospitalDAO.addHospital(hospital)) {
				EndView.successMessage("병원 : " + hospital.getHospitalName() + "] 저장");
			} else {
				EndView.failMessage("병원 : " + hospital.getHospitalName() + "] 저장");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 병원 위치 수정
	public static void updateHospitalLocation(String hospitalName, String location) {
		if (hospitalName != null && !hospitalName.equals("") && location != null && !location.equals("")) {

			if (HospitalDAO.updateHospitalLocation(hospitalName, location)) {
				EndView.successMessage("병원 : " + hospitalName + "] 지역 정보 수정");
			} else {
				EndView.failMessage("병원 : " + hospitalName + "] 지역 정보 수정");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 병원 보유 백신 개수 다중(전체) 수정
	public static void updateHospitalAllVaccine(String hospitalName, int pfizer, int moderna, int az) {
		if (hospitalName != null && !hospitalName.equals("")) {

			if (HospitalDAO.updateHospitalAllVaccine(hospitalName, pfizer, moderna, az)) {
				EndView.successMessage("병원 : " + hospitalName + "] 백신 정보 수정");
			} else {
				EndView.failMessage("병원 : " + hospitalName + "] 백신 정보 수정");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 병원 보유 백신 개수 단일 수정
	public static void updateHospitalVaccine(String hospitalName, String vaccineName, int num) {
		if (hospitalName != null && !hospitalName.equals("") && vaccineName != null && !vaccineName.equals("")) {

			if (HospitalDAO.updateHospitalVaccine(hospitalName, vaccineName, num)) {
				EndView.successMessage("병원 : " + hospitalName + "] 백신 [" + vaccineName + "] 정보 수정");
			} else {
				EndView.failMessage("병원 : " + hospitalName + "] 백신 [" + vaccineName + "] 정보 수정");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 접종 예약 증감에 따라 백신 개수 수정
	public static void updateReservationVaccine(String hospitalName, String vaccineName, int count) {
		if (hospitalName != null && !hospitalName.equals("") && vaccineName != null && !vaccineName.equals("")) {
			int vaccineCount = HospitalDAO.getHospitalVaccine(hospitalName, vaccineName);

			if (vaccineCount > -1) {
				updateHospitalVaccine(hospitalName, vaccineName, vaccineCount + count);
			} else {
				EndView.errorMessage(hospitalName + "이 보유한 " + vaccineName + " 백신 정보가 존재하지 않습니다.");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 병원 삭제
	public static void deleteHospital(String hospitalName) {
		if (hospitalName != null && !hospitalName.equals("")) {

			if (HospitalDAO.deleteHospital(hospitalName)) {
				EndView.successMessage("병원 : " + hospitalName + "] 삭제");
			} else {
				EndView.failMessage("병원 : " + hospitalName + "] 삭제");
			}
		} else {
			EndView.nullMessage();
		}
	}

	/**
	 * Vaccine Controller
	 * 
	 * - getAllVaccine() 
	 * - getVaccine()
	 * - updateVaccine() 
	 * - updateVaccineTargetAge()
	 */

	// 백신 정보 다중(전체) 조회
	public static void getAllVaccine() {
		List<Vaccine> vaccineList = VaccineDAO.getAllVaccine();

		if (vaccineList.size() > 0) {
			EndView.showAll(vaccineList);
		} else {
			EndView.errorMessage("백신 정보가 존재하지 않습니다.");
		}
	}

	// 백신 정보 단일 조회
	public static void getVaccine(String vaccinName) {
		if (vaccinName != null && !vaccinName.equals("")) {
			Vaccine vaccine = VaccineDAO.getVaccine(vaccinName);
			if (vaccine != null) {
				EndView.showOne(vaccine);
			} else {
				EndView.errorMessage("해당 이름의 백신 정보가 존재하지 않습니다.");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 백신 플랫폼, 보관 온도 수정
	public static void updateVaccine(String vaccineName, String platform, String temper) {
		if (vaccineName != null && !vaccineName.equals("")) {

			if (VaccineDAO.updateVaccine(vaccineName, platform, temper)) {
				EndView.successMessage("백신 : " + vaccineName + "] 정보 수정");
			} else {
				EndView.failMessage("백신 : " + vaccineName + "] 정보 수정");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 백신 접종 연령 수정
	public static void updateVaccineTargetAge(String vaccineName, int age) {
		if (vaccineName != null && !vaccineName.equals("") && (Integer) age != null) {

			if (VaccineDAO.updateVaccineTargetAge(vaccineName, age)) {
				EndView.successMessage("백신 : " + vaccineName + "] 접종 연령 수정");
			} else {
				EndView.failMessage("백신 : " + vaccineName + "] 접종 연령 수정");
			}
		} else {
			EndView.nullMessage();
		}
	}

	/**
	 * Users Controller
	 * 
	 * - getAllUsers() 
	 * - getAllUsersByHospital() 
	 * - getVaccineDate()
	 * - addUser()
	 * - updateUserDate() 
	 * - updateUserAddress()
	 * - deleteUser()
	 */

	// 접종 예약자 정보 다중(전체) 조회
	public static void getAllUsers() {
		List<Users> userList = UsersDAO.getAllUsers();

		if (userList.size() > 0) {
			EndView.showAll(userList);
		} else {
			EndView.errorMessage("접종 예약자 정보가 존재하지 않습니다.");
		}
	}

	// 접종 예약자 정보 단일 조회
	public static void getAllUsersByHospital(String hospitalName) {
		if (hospitalName != null && !hospitalName.equals("")) {
			List<Users> userList = UsersDAO.getAllUsersByHospital(hospitalName);

			if (userList.size() > 0) {
				EndView.showAll(userList);
			} else {
				EndView.errorMessage(hospitalName + "의 접종 예약자 정보가 존재하지 않습니다.");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 접종 예약자 별 접종 예약일 조회
	public static void getVaccineDate(int idNum) {
		if ((Integer) idNum != null) {
			Users user = UsersDAO.getUserNextVaccineDate(idNum);
			if (user != null) {
				EndView.showNextVaccineDate(user);
			} else {
				EndView.errorMessage("해당 주민등록번호와 일치하는 접종 예약자 정보가 존재하지 않습니다.");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 접종 예약자 추가
	public static void addUser(Users user) {
		if (user != null) {
			if (UsersDAO.addUser(user)) {
				EndView.successMessage("예약자 : " + user.getUserName() + "] 접종 예약");
			} else {
				EndView.failMessage("예약자 : " + user.getUserName() + "] 접종 예약");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 접종 날짜 수정
	public static void updateUserDate(int idNum, int dateNum, String date) {
		if ((Integer) idNum != null && (Integer) dateNum != null && date != null && !date.equals("")) {
			if (UsersDAO.updateUserDate(idNum, dateNum, date)) {
				EndView.successMessage("예약자 주민등록 번호 : " + idNum + "] 접종일 수정");
			} else {
				EndView.failMessage("예약자 주민등록 번호 : " + idNum + "] 접종일 수정");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 접종 예약자 주소 수정
	public static void updateUserAddress(int idNum, String address) {
		if ((Integer) idNum != null && address != null && !address.equals("")) {
			if (UsersDAO.updateUserAddress(idNum, address)) {
				EndView.successMessage("예약자 주민등록 번호 : " + idNum + "] 주소 수정");
			} else {
				EndView.failMessage("예약자 주민등록 번호 : " + idNum + "] 주소 수정");
			}
		} else {
			EndView.nullMessage();
		}
	}

	// 접종 예약자 삭제
	public static void deleteUser(int idNum) {
		if ((Integer) idNum != null) {
			if (UsersDAO.deleteUser(idNum)) {
				EndView.successMessage("예약자 주민등록 번호 : " + idNum + "] 접종 예약 취소");
			} else {
				EndView.failMessage("예약자 주민등록 번호 : " + idNum + "] 접종 예약 취소");
			}
		} else {
			EndView.nullMessage();
		}
	}

}
