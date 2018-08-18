package main;

import controllers.LoginController;
import controllers.MenuController;
import view.LoginView;
import view.MenuView;

public class Main {

	public static void main(String[] args) {

		LoginController loginController = new LoginController();
		LoginView loginView = new LoginView(loginController); 	
				
	}

}