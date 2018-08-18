package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.dao.OperariosDao;
import model.dao.OrdenesTrabajosDao;
import model.dao.PasosDao;
import model.dto.OrdenesTrabajos;
import model.dto.PasosAsignados;
import view.ConsultaTareasView;

public class ConsultaTareasController implements ActionListener{

	private ConsultaTareasView consultaTareasView;

	public ConsultaTareasController() {
		super();
	}

	public ConsultaTareasView getConsultaTareasView() {
		return consultaTareasView;
	}

	public void setConsultaTareasView(ConsultaTareasView consultaTareasView) {
		this.consultaTareasView = consultaTareasView;
	}

	public Object[] getOrdenesTrabajo() {
		
		OrdenesTrabajosDao otDao = new OrdenesTrabajosDao();

		return otDao.getOrdenesTrabajos(false).toArray();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Consultar")) {

			PasosDao pasosDao = new PasosDao();

			OrdenesTrabajos ot = (OrdenesTrabajos) this.consultaTareasView.getOtCombo().getSelectedItem();
			ArrayList<PasosAsignados> pasosList = pasosDao.getPasosAsignadosByNumeroOT(ot.getNumero());

			this.consultaTareasView.addRegistros(pasosList);
			
		}		
	}
	
	
}