package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.MenuController;
import utils.Constants;

public class MenuView extends JFrame {

	private MenuController menuController;
	private String descripcion;
	
	public MenuView(MenuController menuController) {

		super("TP - FINAL ");

		menuController.setView(this);
		this.menuController = menuController;
				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		this.setLayout(Constants.ESTILO_LAYOUT);
		this.setLocationRelativeTo(null);
		
		descripcion = "Operaciones";
		JMenu operacionesMenu = new JMenu(descripcion);
		operacionesMenu.setMnemonic(descripcion.charAt(0));

		descripcion = "Ingreso OT";
		JMenuItem ingresoOtItem = new JMenuItem(descripcion);
		ingresoOtItem.setMnemonic(descripcion.charAt(0));
		ingresoOtItem.setActionCommand(descripcion);
		ingresoOtItem.addActionListener(this.menuController);
		operacionesMenu.add(ingresoOtItem);
		
		descripcion = "Asignacion Tareas OT";
		JMenuItem asignacionOtItem = new JMenuItem(descripcion);
		asignacionOtItem.setMnemonic(descripcion.charAt(0));
		asignacionOtItem.setActionCommand(descripcion);
		asignacionOtItem.addActionListener(this.menuController);
		operacionesMenu.add(asignacionOtItem);
		
		descripcion = "Registro Tareas OT";
		JMenuItem registrarTareaItem = new JMenuItem(descripcion);
		registrarTareaItem.setMnemonic(descripcion.charAt(0));
		registrarTareaItem.setActionCommand(descripcion);
		registrarTareaItem.addActionListener(this.menuController);
		operacionesMenu.add(registrarTareaItem);
		
		descripcion = "Consulta Tareas OT";
		JMenuItem consultaTareasItem = new JMenuItem(descripcion);
		consultaTareasItem.setMnemonic(descripcion.charAt(0));
		consultaTareasItem.setActionCommand(descripcion);
		consultaTareasItem.addActionListener(this.menuController);
		operacionesMenu.add(consultaTareasItem);
		
		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);
		barra.add(operacionesMenu);
		
		this.setVisible(true);
		
	}

}