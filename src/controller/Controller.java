package controller;

import model.dao.UsersDAO;
import model.entity.Users;
import view.EndView;

public class Controller {
	
	/**
	 * ���ƴ� �κ�
	 */
	public static void nextVaccineDate(int idNum) {
		if((Integer)idNum != null) {
			EndView.showNextVaccineDate(UsersDAO.getUserNextVaccineDate(idNum));
		}else {
			EndView.ErroMessage("�ֹε�Ϲ�ȣ ���ڸ��� null�� �� �����ϴ�.");
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
			EndView.ErroMessage("�̸��� �ֹε�Ϲ�ȣ ���ڸ��� null�� �� �����ϴ�.");
		}
	}
	
	public static void createUser(Users user) {
		if(user != null) {
			if(UsersDAO.createUser(user)) {
				EndView.showResult(user.getUserName() + " ���� ��� ���� ������ �Ϸ�Ǿ����ϴ�.");
			}else {
				EndView.ErroMessage("���� ������ �����Ͽ����ϴ�.");
			}
		}else {
			EndView.ErroMessage("���� ������ ������ null�� �� �����ϴ�.");
		}
	}
	
	public static void deleteUser(int idNum) {
		if((Integer)idNum != null) {
			if(UsersDAO.deleteUser(idNum)) {
				EndView.showResult("��� ���� ���� ��Ұ� �Ϸ�Ǿ����ϴ�.");
			}else {
				EndView.ErroMessage("���� ���� ��Ҹ� �����Ͽ����ϴ�.");
			}
		}else {
			EndView.ErroMessage("�ֹε�Ϲ�ȣ ���ڸ��� null�� �� �����ϴ�.");
		}
	}
	
	public static void updateUserDate(int idNum, int dateNum, String date) {
		if((Integer)idNum != null && (Integer)dateNum != null && date != null) {
			if(UsersDAO.updateUserDate(idNum, dateNum, date)) {
				EndView.showResult(dateNum + "�� ��� ���� ���ڰ� " + date + "�� �����Ǿ����ϴ�.");
			}else {
				EndView.ErroMessage("���� ���� ������ �����Ͽ����ϴ�.");
			}
		}else {
			EndView.ErroMessage("�ֹε�Ϲ�ȣ ���ڸ�, ������ ���� ���� ����, ������ ���� ���ڴ� null�� �� �����ϴ�.");
		}
	}
	
}
