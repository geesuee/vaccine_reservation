package view;

import controller.Controller;
import model.dao.UsersDAO;
import model.entity.Hospital;
import model.entity.Users;
import model.entity.Vaccine;

public class StartView {
	
	
	public static void main(String[] args) {
//		System.out.println("=== user �˻� ===");
//		Controller.getUser("������", 971202);
//		
//		System.out.println("\n=== ���ο� user insert ===");
//		Vaccine vaccine = new Vaccine("ȭ����", 12, 21, "mRNA", "-90�� ~ -60��", 6);
//		Hospital hospital = new Hospital("�ƻ꺴��", "���ı�", 0, 4, 5);
//		Users user = new Users(970326, "�̹��", 20, "���ı�", "20210801", UsersDAO.getNextVaccineDate("20210801", vaccine.getPeriod()), vaccine, hospital);
		/**
		 * ***���ο� ������ insert �Ǹ�,
		 * �ش� ������ �ش� ��� �� -1;
		 */
//		Controller.createUser(user);
		
//		System.out.println("\n=== ���� ���� ��� delete ===");
//		Controller.deleteUser(970324);
		/**
		 * ***������ ��ҵǸ�,
		 * �ش� ������ �ش� ��� �� +1;
		 */
		
//		System.out.println("\n=== ���� ���� ���� update ===");
//		System.out.println("** ���� ���� ������ <<���� ����>>�� �����մϴ�.");
		/**
		 * ������ ��, User�� ã�� ������ getUser�� �ϰ� user ��ü�� �ѱ���?
		 * �̷��� �Ǹ� controller���� "���� user �����Դϴ�."�� ���� ������� ���� ó�� ����
		 * 
		 * 1. user ������ �ִ��� ������
		 * 2. ������ ���� ���ø� 1, 2 �� �ϳ��� �Է� �ߴ���
		 * 3. 1���� ������ ������ ���
		 * 		- 1�� ���� ���� ��¥�� ���� ���� ���� ���� �´���
		 * 		- ���� ���� ���ڰ� ���� ���İ� �´���
		 * 		- *** 1�� ���� ��¥ �����Ǹ� �ڵ����� 2�� ��¥�� �����ǵ��� ����??
		 * 4. 2���� ������ ������ ���
		 * 		- 2�� ���� ���� ��¥�� ���� ���� ���� ���� �´���
		 * 		- ���� ���� ���ڰ� ���� ���İ� �´���
		 * 		- *** 1�� �����Ϸκ��� �󸶳� ���� Ȯ���ؼ� �ȵǰ��ϴ� ���� ���� �ʿ�!! (���� ��¥ ���Ĵ� �ȵǰ� �Է�-3��)
		 */
//		System.out.println("\n1) 1�� ���� ���� ����, ���� ��¥���� ���� ��¥ �Է�");
//		Controller.updateUserDate(971202, 1, "20210702");
//		
//		System.out.println("\n2) 1�� ���� ���� ����, ���� ��¥���� ���� ��¥ �Է�");
//		Controller.updateUserDate(971202, 1, "20210823");
//		
//		System.out.println("\n3) 2�� ���� ���� ����, ���� ��¥���� ���� ��¥ �Է�");
//		Controller.updateUserDate(971202, 2, "20210915");
//		
//		System.out.println("\n3) 2�� ���� ���� ����, 1�� ���� ��¥���� ���� ��¥ �Է�");
//		Controller.updateUserDate(971202, 2, "20210722");
//		
//		System.out.println("\n4) 2�� ���� ���� ����, ���� ��¥���� ���� ��¥ �Է�");
//		Controller.updateUserDate(971202, 2, "20210823");
//		
//		System.out.println("\n=== ���� ���� ���� ���� ���̽� ===");
//		Controller.updateUserDate(971202, 2, "20210918");
//		Controller.nextVaccineDate(971202);
		
		System.out.println("\n=== ����� �ּ� ���� update ===");
		Controller.updateUserAddress(971202, "������");
	}

}
