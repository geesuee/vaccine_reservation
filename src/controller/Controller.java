package vaccine.controller;

import vaccine.model.dao.UsersDAO;
import vaccine.model.dao.VaccineDAO;
import vaccine.view.EndView;

public class Controller {

	public static void nextVaccineDate(int idNum) {
		if((Integer)idNum != null) {
			
			EndView.showNextVaccineDate(UsersDAO.getUserNextVaccineDate(idNum));
			
		}else {
//			EndView.errorView();
		}
	}
}
