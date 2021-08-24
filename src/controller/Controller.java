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
}
