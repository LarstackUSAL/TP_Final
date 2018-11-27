package view;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.LoginController;
import utils.Constants;

public class LoginView {

	private LoginController loginController;
	
	private JFrame loginFrame = new JFrame("Login");
	private JLabel usuarioLabel;
	private JTextField usuarioTextField;
	private JLabel pswLabel;
	private JPasswordField pswTextField;
	private JButton loginButton;
	
	public LoginView(LoginController loginController) {
		
		loginController.setLoginView(this);
		this.loginController = loginController;
		
		usuarioLabel = new JLabel("Usuario:");
		usuarioTextField = new JTextField(Constants.TEXTO_ANCHO);
		usuarioTextField.setCaretPosition(SwingConstants.CENTER);
		usuarioTextField.setHorizontalAlignment(JTextField.CENTER);
		
		pswLabel = new JLabel("Contraseña:");
		pswTextField = new JPasswordField(Constants.TEXTO_ANCHO);
		pswTextField.setCaretPosition(SwingConstants.CENTER);
		pswTextField.setHorizontalAlignment(JTextField.CENTER);
		
		loginButton = new JButton("Aceptar");
		loginButton.setActionCommand("Aceptar");
		loginButton.addActionListener(this.loginController);

		Component[] componentes = {usuarioLabel, usuarioTextField, pswLabel, pswTextField, loginButton};

		for (int i = 0; i < componentes.length; i++) {

			this.loginFrame.add(componentes[i]);
		}

		loginFrame.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		loginFrame.setLayout(Constants.ESTILO_LAYOUT);
		loginFrame.setLocationRelativeTo(null);

		this.loginFrame.setVisible(true);
	}

	public void mostrarMensajeDialog(String mensajeBody, String titulo) {

		JOptionPane.showMessageDialog(null, mensajeBody, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	
	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public JFrame getLoginFrame() {
		return loginFrame;
	}

	public void setLoginFrame(JFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public JLabel getUsuarioLabel() {
		return usuarioLabel;
	}

	public void setUsuarioLabel(JLabel usuarioLabel) {
		this.usuarioLabel = usuarioLabel;
	}

	public JTextField getUsuarioTextField() {
		return usuarioTextField;
	}

	public void setUsuarioTextField(JTextField usuarioTextField) {
		this.usuarioTextField = usuarioTextField;
	}

	public JLabel getPswLabel() {
		return pswLabel;
	}

	public void setPswLabel(JLabel pswLabel) {
		this.pswLabel = pswLabel;
	}

	public JTextField getPswTextField() {
		return pswTextField;
	}

	public void setPswTextField(JPasswordField pswTextField) {
		this.pswTextField = pswTextField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}
}
