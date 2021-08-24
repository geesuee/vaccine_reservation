package vaccine.view;
import vaccine.controller.Controller;
import vaccine.model.entity.Users;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import util.PublicCommon;

public class StartView {

	public static void main(String[] args) {
		
		Controller.nextVaccineDate(940804);
	}
	
}
