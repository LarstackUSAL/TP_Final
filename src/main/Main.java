package main;

import controllers.MenuController;
import views.MenuView;

public class Main {

	public static void main(String[] args) {

		MenuController menuController = new MenuController(); 
		MenuView menuView = new MenuView(menuController);	
				
	}

}