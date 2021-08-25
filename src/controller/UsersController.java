package controller;

import java.util.List;

import model.dao.UsersDAO;
import model.entity.Users;
import view.EndView;

public class UsersController {
	
	private static UsersController instance = new UsersController();
	
	private UsersController() {};
	
	public static UsersController getInstance() {
		return instance;
	}
	
	/**
	 * Users Controller
	 * - nextVaccineDate
	 * - getAllUsers
	 * - getAllUsersByHospital
	 * - getUser
	 * - addUser
	 * - updateUserDate
	 * - updateUserAddress
	 * - deleteUser
	 */
	
	//보류
	public static void nextVaccineDate(int idNum) {
		if((Integer)idNum != null) {
			Users user = UsersDAO.getUserNextVaccineDate(idNum);
			if(user != null) {
				EndView.showNextVaccineDate(user);
			}else {
				EndView.errorMessage("해당 주민등록번호와 일치하는 접종 예약자 정보가 없습니다.");
			}
		}else {
			EndView.nullMessage();
		}
	}
	
	
	public static void getAllUsers() {
		List<Users> userList = UsersDAO.getAllUsers();
		
		if(userList.size() > 0) {
			EndView.showAll(userList);
		}else {
			EndView.errorMessage("접종 예약자 정보가 없습니다.");
		}
	}
	
	public static void getAllUsersByHospital(String hospitalName) {
		if(hospitalName != null && !hospitalName.equals("")) {
			List<Users> userList = UsersDAO.getAllUsersByHospital(hospitalName);
			
			if(userList.size() > 0) {
				EndView.showAll(userList);
			}else {
				EndView.errorMessage(hospitalName + "의 접종 예약자 정보가 없습니다.");
			}
		}else {
			EndView.nullMessage();
		}
	}
	
	
	public static void getUser(String name, int idNum) {
		if(name != null && !name.equals("") && (Integer)idNum != null) {
			Users user = UsersDAO.getUser(name, idNum);
			if(user != null) {
				EndView.showOne(user);
			}else {
				EndView.errorMessage("해당 정보와 일치하는 접종 예약자 정보가 없습니다.");
			}
		}else {
			EndView.nullMessage();
		}
	}

	
	public static void addUser(Users user) {
		if(user != null) {
			if(UsersDAO.addUser(user)) {
				EndView.successMessage("예약자 : " + user.getUserName() + "] 접종 예약");
			}else {
				EndView.failMessage("예약자 : " + user.getUserName() + "] 접종 예약");
			}
		}else {
			EndView.errorMessage("유효하지 않은 접종 예약자 정보입니다."); // 보류
		}
	}


	public static void updateUserDate(int idNum, int dateNum, String date) {
		if((Integer)idNum != null && (Integer)dateNum != null && date != null && !date.equals("")) {
			if(UsersDAO.updateUserDate(idNum, dateNum, date)) {
				EndView.successMessage("예약자 주민등록 번호 : " + idNum + "] 접종일 수정");
			}else {
				EndView.failMessage("예약자 주민등록 번호 : " + idNum + "] 접종일 수정");
			}
		}else {
			EndView.nullMessage();
		}
	}

	
	public static void updateUserAddress(int idNum, String address) {
		if((Integer)idNum != null && address != null && !address.equals("")) {
			if(UsersDAO.updateUserAddress(idNum, address)) {
				EndView.successMessage("예약자 주민등록 번호 : " + idNum + "] 주소 수정");
			}else {
				EndView.failMessage("예약자 주민등록 번호 : " + idNum + "] 주소 수정");
			}
		}else {
			EndView.nullMessage();
		}
	}

	
	public static void deleteUser(int idNum) {
		if((Integer)idNum != null) {
			if(UsersDAO.deleteUser(idNum)) {
				EndView.successMessage("예약자 주민등록 번호 : " + idNum + "] 접종 예약 취소");
			}else {
				EndView.failMessage("예약자 주민등록 번호 : " + idNum + "] 접종 예약 취소");
			}
		}else {
			EndView.nullMessage();
		}
	}
}
