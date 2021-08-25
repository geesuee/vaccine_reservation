package view;

import java.io.IOException;

import controller.Controller;

public class StartView {
	
	
	public static void main(String[] args) throws IOException {
		Controller controller = Controller.getInstance();
		
		controller.vaccineStart();
	}

}
