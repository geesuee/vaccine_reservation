package vaccine.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import util.PublicCommon;
import vaccine.model.entity.Users;

public class UsersDAO {

	public static Users getUserNextVaccineDate(int idNum) {
		EntityManager em = PublicCommon.getEntityManager();
		Users user=em.find(Users.class, idNum);
		if(user == null) {
			System.out.println("정보가 없습니다.");
			em.close();
			em = null;
			
			return null;
		}else {
			try {
				user = nextVaccineDate(user);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			em.close();
			em = null;
			return user;
		}
	}

	private static Users nextVaccineDate(Users user) throws ParseException {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");

		String userName=user.getUserName();
		String VaccineName=user.getVaccineName().getVaccineName();
		String date1=user.getDate1();
		String date2=user.getDate2();
		
		if(date2 != null || !date2.equals("")) {
			return user;
		}else {
			Calendar cal = Calendar.getInstance(); 
			Date dt = dtFormat.parse(date1);

			cal.setTime(dt);
			int day = 0;
			if("az".equals(VaccineName) || "AZ".equals(VaccineName)) {
				day = 84;
			}else if("화이자".equals(VaccineName)) {
				day = 42;
			}else if("모더나".equals(VaccineName)) {
				day = 42;
			}else {
				System.out.println("잘못된 입력");
			}
			cal.add(Calendar.DATE,day );
			user.setDate2(dtFormat.format(cal.getTime())); 

			return user;
		}
		
	}
}
