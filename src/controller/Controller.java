package controller;

import model.dao.UsersDAO;
import model.entity.Users;
import view.EndView;

public class Controller {
	
	/**
	 * 영훈님 부분
	 */
	public static void nextVaccineDate(int idNum) {
		if((Integer)idNum != null) {
			EndView.showNextVaccineDate(UsersDAO.getUserNextVaccineDate(idNum));
		}else {
			EndView.ErroMessage("주민등록번호 앞자리는 null일 수 없습니다.");
		}
	}
	
	/**
	 * User Controller
	 * - getUser
	 * - createUser
	 * - deleteUser
	 * - updateUser
	 */
	public static void getUser(String name, int idNum) {
		if(name != null && (Integer)idNum != null) {
			EndView.showUser(UsersDAO.getUser(name, idNum));
		}else {
			EndView.ErroMessage("이름과 주민등록번호 앞자리는 null일 수 없습니다.");
		}
	}
	
	public static void createUser(Users user) {
		if(user != null) {
			if(UsersDAO.createUser(user)) {
				EndView.showResult(user.getUserName() + " 님의 백신 접종 예약이 완료되었습니다.");
			}else {
				EndView.ErroMessage("접종 예약을 실패하였습니다.");
			}
		}else {
			EndView.ErroMessage("접종 예약자 정보는 null일 수 없습니다.");
		}
	}
	
	public static void deleteUser(int idNum) {
		if((Integer)idNum != null) {
			if(UsersDAO.deleteUser(idNum)) {
				EndView.showResult("백신 접종 예약 취소가 완료되었습니다.");
			}else {
				EndView.ErroMessage("접종 예약 취소를 실패하였습니다.");
			}
		}else {
			EndView.ErroMessage("주민등록번호 앞자리는 null일 수 없습니다.");
		}
	}
	
	public static void updateUserDate(int idNum, int dateNum, String date) {
		if((Integer)idNum != null && (Integer)dateNum != null && date != null) {
			if(UsersDAO.updateUserDate(idNum, dateNum, date)) {
				EndView.showResult(dateNum + "차 백신 접종 일자가 " + date + "로 수정되었습니다.");
			}else {
				EndView.ErroMessage("접종 예약 수정을 실패하였습니다.");
			}
		}else {
			EndView.ErroMessage("주민등록번호 앞자리, 수정할 접종 예약 차시, 수정할 접종 일자는 null일 수 없습니다.");
		}
	}
	
}
