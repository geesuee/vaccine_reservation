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
	 * - findByVaccineName -> getVaccineByName
	 * - findByVaccineTagetAge -> getVaccineByTargetAge
	 * 
	 * - addVaccine
	 * 
	 * - updateVaccine
	 * - updateVaccineAge -> updateVaccineTargetAge
	 * 
	 * - deleteVaccine
	 */
	
	public static void getAllVaccine() {
		List<Vaccine> vaccineList = VaccineDAO.getAllVaccine();
		
		if(vaccineList.size() > 0) {
			EndView.showVaccinList(vaccineList);
		}else {
			EndView.errorMessage("백신 정보가 없습니다.");
		}
	}
	
	
	public static void getVaccineByName(String vaccinName) {
		if(vaccinName != null && !vaccinName.equals("")) {
			Vaccine vaccine = VaccineDAO.getVaccineByName(vaccinName);
			
			if(vaccine != null) {
				EndView.getVaccine(vaccine);
			}else {
				EndView.errorMessage("해당 이름의 백신 정보가 없습니다.");
			}
		}else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
		}
	}

	
	public static void getVaccineByTargetAge(int targetAge) {
		if((Integer)targetAge !=null) {
			List<Vaccine> vaccineList = VaccineDAO.getVaccineByTargetAge(targetAge);
			
			if(vaccineList.size() > 0) {
				EndView.showVaccinList(vaccineList);
			}else {
				EndView.errorMessage("해당 연령 대상 백신 정보가 없습니다.");
			}
		}else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
		}
	}

	
	public static void addVaccine(Vaccine vaccine) {
		if(vaccine != null) {
			Vaccine newVaccine = VaccineDAO.addVaccine(vaccine);
			
			if(newVaccine != null) {
				EndView.addVaccineView(newVaccine);
			}else {
				EndView.errorMessage("새로운 백신 정보 추가를 실패했습니다.");
			}
		}else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
		}
	}
	
	
	
//	public static void addVaccine(Vaccine vaccine) {
//		// 데이터를 받아오는 곳.
////			Scanner sc = new Scanner(System.in);
////			System.out.println("백신 명을 입력하세요");
////			String vaccineName = sc.nextLine();
////			System.out.println("백신의 접종 연령을 입력하세요");
////			int targetAge = sc.nextInt();
////			System.out.println("백신의 접종간격을 입력하세요");
////			int period = sc.nextInt();
////			System.out.println("플랫폼을 입력하세요");
////			String platform = sc.nextLine();
////			System.out.println("백신의 보관 온도를 입력하세요");
////			String temperature = sc.nextLine();
////			System.out.println("보관 기간을 숫자로 입력하세요");
////			int storage = sc.nextInt();
////			Vaccine vaccine = new Vaccine(vaccineName, targetAge, period, platform, temperature, storage);
//		
////		Vaccine vaccine = new Vaccine("얀센",50,0,"바이러스 벡터","-25도 ~ 15도",8);
//		
//		if(vaccine != null) {
//			Vaccine newVaccine = null;
//			
//			try {
//				newVaccine = VaccineDAO.addVaccine(vaccine);
//				if(newVaccine != null) {
//					EndView.addVaccineView(newVaccine);
//				}else {
//					EndView.errorMessage("새로운 백신 정보 추가를 실패했습니다.");
//				}
//			}catch(Exception e) {
//				EndView.errorMessage("<< 백신 정보 추가 실패 >>");
//				e.printStackTrace();
//			}
//			
//		}else {
//			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
//		}
//	}
	
// -------------------------------------------------------------------------------
	
	// 이거는 제가 잘 모르겠어서 뒀습니다ㅜ
	public static void updateVaccine(String vaccineName) {
		if(vaccineName != null && !vaccineName.equals("")) {
			//수정 데이터, 객체 입력 
			Vaccine updateVaccine = new Vaccine(vaccineName,30,0,"바이러스 벡터","-25도 ~ 15도",8);
			VaccineDAO.updateVaccine(updateVaccine);
		}else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
		}
		
	}
	
	
// ---------------------------------------------------------------------------------

	public static void updateVaccineTargetAge(String vaccineName, int age) {
		if(vaccineName != null && !vaccineName.equals("") && (Integer)age !=null) {
			
			if(VaccineDAO.updateVaccineTargetAge(vaccineName, age)) {
				EndView.showResult(vaccineName + "의 접종 연령을 " + age + "세로 수정 완료하였습니다.");
			}else {
				EndView.errorMessage("접종 연령 수정을 실패했습니다.");
			}
		}else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
		}
	}


//	-----------------------------------------------------------------------------------------
	
	//여기 DAO가 조금 수정되면 좋을 것 같아서 우선 그대로 뒀습니다!
	public static void deleteVaccine(String vaccineName) {
		if(vaccineName != null && !vaccineName.equals("")) {
			
			VaccineDAO.deleteVaccine(vaccineName);
		}else {
			EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
		}
	}
	
}
