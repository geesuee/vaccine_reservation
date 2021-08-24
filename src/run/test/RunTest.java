package run.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import util.PublicCommon;
import vaccine.model.entity.Users;

public class RunTest {
	
//	@Test
	public void Test1() throws ParseException {
//		EntityManager em = PublicCommon.getEntityManager();
//		String date = "20210814";
//		
//		String date2 = nextVaccineDate(date,"AZ");
//		System.out.println(date2);
		
		EntityManager em = PublicCommon.getEntityManager();
		Users user = em.find(Users.class, 940804);
		System.out.println(user);
		em.close();
		em = null;
	}

	private String nextVaccineDate(String date, String VaccineName) throws ParseException {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");

		Calendar cal = Calendar.getInstance(); 
		Date dt = dtFormat.parse(date);

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
		return dtFormat.format(cal.getTime());

	}
}
