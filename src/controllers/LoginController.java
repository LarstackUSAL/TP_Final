package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.dao.LoginDao;
import utils.Constants;
import view.LoginView;
import view.MenuView;

public class LoginController implements ActionListener {

	private LoginView loginView;

	public void setLoginView(LoginView loginView) {

		this.loginView = loginView;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Aceptar")) {

			String usuario = loginView.getUsuarioTextField().getText();
			String psw = loginView.getPswTextField().getText();

			LoginDao loginDao = new LoginDao();
			String rol = loginDao.getRolUsuario(usuario,psw);

			if(!rol.isEmpty()) {

				MenuController menuController = new MenuController(); 
				MenuView menuView = new MenuView(menuController, rol);
				
				this.loginView.getLoginFrame().dispose();

			} else {
				
				loginView.mostrarMensajeDialog("El usuario y/o contraseña no son válidos.", "Error Login");
				loginView.getUsuarioTextField().setText("");
				loginView.getPswTextField().setText("");
			}
		}

	}


}
