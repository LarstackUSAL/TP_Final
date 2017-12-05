package utils;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//import ar.edu.usal.tp9.controller.IngresoController;

public class GuiUtilities {

	public static void agregarComponentesVentana(JFrame ventana, Component[] componentesArray) {
		
		agregarComponentesVentana(ventana, componentesArray, null);
		
	}
	

	public static void agregarComponentesVentana(JPanel pnlPaqueteBuscado, Component[] componentesPaqueteArray) {
		
		agregarComponentesVentana(pnlPaqueteBuscado, componentesPaqueteArray, null);
		
	}
	
	public static void agregarComponentesVentana(Object ventana,
			Component[] componentesTitulosArray, JTextArea[] componentesArray) {
		
		for (int i = 0; i < componentesTitulosArray.length; i++) {
			
			if(ventana instanceof JPanel)
				((JPanel)ventana).add(componentesTitulosArray[i]);
			else
				((JFrame)ventana).add(componentesTitulosArray[i]);
			
			if(componentesArray != null){
				if(ventana instanceof JPanel)
					((JPanel)ventana).add(componentesArray[i]);
				else
					((JFrame)ventana).add(componentesArray[i]);
			}
			
		}
		
	}

	public static void aplicarFormatoComponentes(JFrame ventana, JTextArea[] componentesArray) {

		for (int i = 0; i < componentesArray.length; i++)
			aplicarFormatoTextArea(ventana, componentesArray[i]);
		
	}

	public static void aplicarFormatoTextArea(JFrame ventana, JTextArea textArea){
		
		textArea.setEditable(false);	
		textArea.setLineWrap(true);	
		textArea.setWrapStyleWord(true); 
		textArea.setCaretPosition(SwingConstants.CENTER);
		textArea.setBackground(ventana.getBackground());
		textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textArea.setFont(new Font(textArea.getFont().getName(), Font.ITALIC, 
				textArea.getFont().getSize()));
		
	}

	public static void aplicarFormatoTextField(JFrame ventana, JTextField textField){
		
		textField.setCaretPosition(SwingConstants.CENTER);
		textField.setHorizontalAlignment(JTextField.CENTER);
		
	}
	

	public static void aplicarFormatoLista(JFrame ventana, JList lista, int opcionSeleccion) {

		lista.setVisibleRowCount(Constants.ITEMS_MOSTRAR);
		lista.setSelectionMode(opcionSeleccion);
		
	}

	public static void aplicarFormatoPanel(JFrame ventana, JPanel panel, JList lista1, JList lista2) {
		
		panel.setBorder(lista1.getBorder());
		panel.setBackground(lista1.getBackground());
		panel.setForeground(lista1.getForeground());
		panel.add(lista2);
		
		
	}
	
	public static void aplicarFormatoRadioButton(JFrame ventana, JRadioButton radioButton, String nombreAccion, 
			ActionListener listener) {

		radioButton.setMnemonic(nombreAccion.charAt(0));
		radioButton.setActionCommand(nombreAccion);
		radioButton.addActionListener(listener);
		
	}
	
	public static void aplicarFormatoVentana(JFrame ventana){
		
		ventana.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);
		
	}
	
	public static JMenuItem setearDatosJMenuItem(String nombreItem, ActionListener listener, JMenu menu) {
		
		JMenuItem menuItem = new JMenuItem(nombreItem);
		menuItem.setMnemonic(nombreItem.charAt(0));
		menuItem.setActionCommand(nombreItem);
		menuItem.addActionListener(listener);
		menu.add(menuItem);
		
		return menuItem;
		
	}

	public static void setearComandoBoton(JButton boton, String nombreAccion, ActionListener listener) {

		boton.setActionCommand(nombreAccion);
		boton.addActionListener(listener);
		
	}
	

}
