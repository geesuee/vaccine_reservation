package controller;

import model.dao.UsersDAO;
import model.dao.VaccineDAO;
import model.entity.Users;
import model.entity.Vaccine;
import view.EndView;

public class Controller {
	
	/**
	 * ���ƴ� �κ�
	 */
	public static void nextVaccineDate(int idNum) {
		if((Integer)idNum != null) {
			EndView.showNextVaccineDate(UsersDAO.getUserNextVaccineDate(idNum));
		}else {
			EndView.errorMessage("�ֹε�Ϲ�ȣ ���ڸ��� null�� �� �����ϴ�.");
		}
	}
	
	/**
	 * User Controller
	 * - getUser
	 * - createUser
	 * - deleteUser
	 * - updateUserDate
	 * - updateUserAddress
	 */
	public static void getUser(String name, int idNum) {
		if(name != null && !name.equals("") && (Integer)idNum != null) {
			Users user = UsersDAO.getUser(name, idNum);
			if(user != null) {
				EndView.showUser(user);
			}else {
				EndView.errorMessage("�Է��Ͻ� ������ ��ġ�ϴ� ���� ������ ������ �����ϴ�.");
			}
		}else {
			EndView.errorMessage("�̸��� �ֹε�Ϲ�ȣ ���ڸ��� null�� �� �����ϴ�.");
		}
	}
	
	public static void createUser(Users user) {
		if(user != null) {
			if(UsersDAO.createUser(user)) {
				EndView.showResult(user.getUserName() + " ���� ��� ���� ������ �Ϸ�Ǿ����ϴ�.");
			}else {
				EndView.errorMessage("���� ������ �����Ͽ����ϴ�.");
			}
		}else {
			EndView.errorMessage("���� ������ ������ null�� �� �����ϴ�.");
		}
	}
	
	public static void deleteUser(int idNum) {
		if((Integer)idNum != null) {
			if(UsersDAO.deleteUser(idNum)) {
				EndView.showResult("��� ���� ���� ��Ұ� �Ϸ�Ǿ����ϴ�.");
			}else {
				EndView.errorMessage("���� ���� ��Ҹ� �����Ͽ����ϴ�.");
			}
		}else {
			EndView.errorMessage("�ֹε�Ϲ�ȣ ���ڸ��� null�� �� �����ϴ�.");
		}
	}
	
	public static void updateUserDate(int idNum, int dateNum, String date) {
		if((Integer)idNum != null && (Integer)dateNum != null && date != null && !date.equals("")) {
			if(UsersDAO.updateUserDate(idNum, dateNum, date)) {
				EndView.showResult(dateNum + "�� ��� ���� ���ڰ� " + date + "�� �����Ǿ����ϴ�.");
			}else {
				EndView.errorMessage("���� ���� ������ �����Ͽ����ϴ�.");
			}
		}else {
			EndView.errorMessage("�ֹε�Ϲ�ȣ ���ڸ�, ������ ���� ���� ����, ������ ���� ���ڴ� null�� �� �����ϴ�.");
		}
	}
	
	public static void updateUserAddress(int idNum, String address) {
		if((Integer)idNum != null && address != null && !address.equals("")) {
			if(UsersDAO.updateUserAddress(idNum, address)) {
				EndView.showResult("�ּ� ������ �Ϸ�Ǿ����ϴ�.");
			}else {
				EndView.errorMessage("�ּ� ������ �����Ͽ����ϴ�.");
			}
		}else {
			EndView.errorMessage("�ֹε�Ϲ�ȣ ���ڸ�, ������ �ּ� ������ null�� �� �����ϴ�.");
		}
	}
	
	/* controller + VaccineDAO
	 * -nextVaccineDate   (userDAO)
	 * -getAllVaccine
	 * -addVaccine
	 * -findByVaccineName
	 * -findByVaccineTagetAge
	 * -updateVaccine
	 * -updateVaccineAge
	 * -deleteVaccine
	 */

		public static void getAllVaccine() {
			try {
				EndView.showVaccinList(VaccineDAO.getAllVaccine());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public static void addVaccine() {
			// 데이터를 받아오는 곳.

//			Scanner sc = new Scanner(System.in);
//			
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
			Vaccine vaccine = new Vaccine("얀센",50,0,"바이러스 벡터","-25도 ~ 15도",8);
			EndView.addVaccineView(VaccineDAO.addVaccine(vaccine));
					
		}

		public static void findByVaccineName(String vaccinName) {
			if(vaccinName != null && !vaccinName.equals("")) {
				EndView.getVaccine(VaccineDAO.findByVaccineName(vaccinName));
			}else {
				EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
			}
		}

		public static void findByVaccineTagetAge(int targetAge) {
			if((Integer)targetAge !=null) {
				try {
					EndView.showVaccinList(VaccineDAO.findByVaccineTagetAge(targetAge));
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
			}
			
		}

		public static void updateVaccine(String vaccineName) {
			if(vaccineName != null && !vaccineName.equals("")) {
				//수정 데이터, 객체 입력 
				Vaccine updateVaccine = new Vaccine(vaccineName,30,0,"바이러스 벡터","-25도 ~ 15도",8);
				VaccineDAO.updateVaccine(updateVaccine);
			}else {
				EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
			}
			
		}
		public static void updateVaccineAge(String vaccineName, int age) {
			if(vaccineName != null && !vaccineName.equals("") && (Integer)age !=null) {
				boolean result = VaccineDAO.updateVaccineAge(vaccineName, age);
				
				if(result == true) {
					System.out.println("변경 성공");
				}else {
					System.out.println("변경 실패");
				}
			}else {
				EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
			}
			
		}

		public static void deleteVaccine(String vaccineName) {
			if(vaccineName != null && !vaccineName.equals("")) {
				VaccineDAO.deleteVaccine(vaccineName);
			}else {
				EndView.errorMessage("아무것도 입력하지 않았거나 null값 입니다.");
			}
		}
}
