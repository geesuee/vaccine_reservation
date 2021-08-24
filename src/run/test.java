package run;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import model.entity.Users;
import util.PublicCommon;

public class test {
	@Test
	void ma() throws ParseException {
		EntityManager em = PublicCommon.getEntityManager();
		Users user = em.find(Users.class, 971202);
		System.out.println(user);
		
		System.out.println("==================");
//		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
		
//		String date1 = user.getDate1();
		LocalDate todaysDate = LocalDate.now();
		LocalDate date1 = LocalDate.parse(user.getDate1(), DateTimeFormatter.BASIC_ISO_DATE);
		
		
//		Calendar cal = Calendar.getInstance();
//		Date dt = dtFormat.parse(date1);
//		Date dt1 = dtFormat.parse(todaysDate);
//		cal.setTime(dt);
		
		System.out.println(date1);
		System.out.println(todaysDate);
		
		
		em.close();
		em = null;
	}
}
