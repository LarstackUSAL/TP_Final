package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AsignacionView;
import view.ConsultaTareasView;
import view.IngresoView;
import view.MenuView;
import view.RegistroView;

public class MenuController implements ActionListener {

	private MenuView menuView;

	public void setView(MenuView menuView) {

		this.menuView = menuView;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Ingreso OT")) {
			
			IngresoController ingresoController = new IngresoController();
			IngresoView ingresoView = new IngresoView(ingresoController);
			
		} else if(e.getActionCommand().equals("Asignacion Tareas OT")) {
			
			AsignacionController asignacionController = new AsignacionController();
			AsignacionView asignacionView = new AsignacionView(asignacionController);
			
		} else if(e.getActionCommand().equals("Registro Tareas OT")) {
			
			RegistroController registroController = new RegistroController();
			RegistroView registroView = new RegistroView(registroController);
			
		} else if(e.getActionCommand().equals("Consulta Tareas OT")) {
			
			ConsultaTareasController consultaTareasController = new ConsultaTareasController();
			ConsultaTareasView consultaTareasView = new ConsultaTareasView(consultaTareasController);
			
		}

	}

}