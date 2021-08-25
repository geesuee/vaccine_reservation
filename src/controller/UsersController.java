package controller;

import java.text.ParseException;
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
	public void getVaccineDate(int idNum) {
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
	
	//1차접종날짜 받아서 2차 접종날짜 할당
	public String nextVaccineDate(String date, String vaccineName) {
		String date2 = null;
		try {
			date2 = UsersDAO.nextVaccineDate2(date, vaccineName);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date2;
	}
	
	
	public void getAllUsers() {
		List<Users> userList = UsersDAO.getAllUsers();
		
		if(userList.size() > 0) {
			EndView.showAll(userList);
		}else {
			EndView.errorMessage("접종 예약자 정보가 없습니다.");
		}
	}
	
	public void getAllUsersByHospital(String hospitalName) {
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
	
	
	public void getUser(String name, int idNum) {
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
	
	public Users getUser2(int idNum) {
		return UsersDAO.getUserById(idNum);
	}

	public boolean getUserBoolean(int idNum) {
		boolean result = false;
		Users user = UsersDAO.getUserById(idNum);
		
		if(user != null) {
			result = true;
		}
		
		return result;
	}
	
	public void addUser(Users user) {
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


	public void updateUserDate(int idNum, int dateNum, String date) {
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

	
	public void updateUserAddress(int idNum, String address) {
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

	
	public void deleteUser(int idNum) {
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
