package controller;

import java.util.List;

import model.dao.VaccineDAO;
import model.entity.Vaccine;
import view.EndView;

public class VaccineController {
	
	private static VaccineController instance = new VaccineController();
	
	private VaccineController() {};
	
	public static VaccineController getInstance() {
		return instance;
	}
	
	/** 
	 * Vaccine Controller
	 * - getAllVaccine
	 * - getVaccineByName
	 * - getVaccineByTargetAge
	 * - addVaccine
	 * - updateVaccine
	 * - updateVaccineTargetAge
	 * - deleteVaccine
	 */
	
	public static void getAllVaccine() {
		List<Vaccine> vaccineList = VaccineDAO.getAllVaccine();
		
		if(vaccineList.size() > 0) {
			EndView.showAll(vaccineList);
		}else {
			EndView.errorMessage("백신 정보가 없습니다.");
		}
	}
	
	
	public static void getVaccineByName(String vaccinName) {
		if(vaccinName != null && !vaccinName.equals("")) {
			Vaccine vaccine = VaccineDAO.getVaccineByName(vaccinName);
			if(vaccine != null) {
				EndView.showOne(vaccine);
			}else {
				EndView.errorMessage("해당 이름의 백신 정보가 없습니다.");
			}
		}else {
			EndView.nullMessage();
		}
	}

	
	public static void getVaccineByTargetAge(int targetAge) {
		if((Integer)targetAge !=null) {
			List<Vaccine> vaccineList = VaccineDAO.getVaccineByTargetAge(targetAge);
			
			if(vaccineList.size() > 0) {
				EndView.showAll(vaccineList);
			}else {
				EndView.errorMessage("해당 연령 대상 백신 정보가 없습니다.");
			}
		}else {
			EndView.nullMessage();
		}
	}

	
	//객체를 받아오는 방식으로 수정  // 데이터를 받아오는 곳, startView로 이동 예정.
	public static void addVaccine(Vaccine vaccine ) {
		
//			Scanner sc = new Scanner(System.in);
//			System.out.println("백신 명을 입력하세요");
//			String vaccineName = sc.nextLine();
//			System.out.println("백신의 접종 연령을 입력하세요");
//			int targetAge = sc.nextInt();
//			System.out.println("백신의 접종간격을 입력하세요");
//			int period = sc.nextInt();
//			System.out.println("플랫폼을 입력하세요");
//			String platform = sc.nextLine();
//			System.out.println("백신의 보관 온도를 입력하세요");
//			String temperature = sc.nextLine();
//			System.out.println("보관 기간을 숫자로 입력하세요");
//			int storage = sc.nextInt();
//			Vaccine vaccine = new Vaccine(vaccineName, targetAge, period, platform, temperature, storage);
//		Vaccine vaccine = new Vaccine("얀센",50,0,"바이러스 벡터","-25도 ~ 15도",8);
		
		if(vaccine != null) {
			try {
				if(VaccineDAO.addVaccine(vaccine)) {
					EndView.successMessage("백신 : " + vaccine.getVaccineName() + "] 저장");
				}else {
					EndView.failMessage("백신 : " + vaccine.getVaccineName() + "] 저장");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			// 보류
		}
	} 
	
// -------------------------------------------------------------------------------
	
	// 이거는 제가 잘 모르겠어서 뒀습니다ㅜ    //파라미터로 받기  백신네임 플랫폼 온도
	public static void updateVaccine(String vaccineName,String platform,String temper) {
		if(vaccineName != null && !vaccineName.equals("")) {
			//수정 데이터, 객체 입력 
//			Vaccine updateVaccine = new Vaccine(vaccineName,30,0,"바이러스 벡터","-25도 ~ 15도",8);
			if(VaccineDAO.updateVaccine(vaccineName,platform,temper)) {
				EndView.successMessage("백신 : " + vaccineName + "] 정보 수정");
			}else {
				EndView.failMessage("백신 : " + vaccineName + "] 정보 수정");
			}
		}else {
			EndView.nullMessage();
		}
		
	}
	
	
// ---------------------------------------------------------------------------------

	public static void updateVaccineTargetAge(String vaccineName, int age) {
		if(vaccineName != null && !vaccineName.equals("") && (Integer)age !=null) {
			
			if(VaccineDAO.updateVaccineTargetAge(vaccineName, age)) {
				EndView.successMessage("백신 : " + vaccineName + "] 접종 연령 수정");
			}else {
				EndView.failMessage("백신 : " + vaccineName + "] 접종 연령 수정");
			}
		}else {
			EndView.nullMessage();
		}
	}


//	-----------------------------------------------------------------------------------------
	
	//여기 DAO가 조금 수정되면 좋을 것 같아서 우선 그대로 뒀습니다!
	public static void deleteVaccine(String vaccineName) {
		if(vaccineName != null && !vaccineName.equals("")) {
			
			if(VaccineDAO.deleteVaccine(vaccineName)) {
				EndView.successMessage("백신 : " + vaccineName + "] 삭제");
			}else {
				EndView.failMessage("백신 : " + vaccineName + "] 삭제");
			}
		}else {
			EndView.nullMessage();
		}
	}
	
}
