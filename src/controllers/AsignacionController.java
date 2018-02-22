package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.dao.OrdenesTrabajosDao;
import model.dao.PasosDao;
import model.dto.Operarios;
import model.dto.OrdenesTrabajos;
import model.dto.Pasos;
import views.AsignacionView;

public class AsignacionController implements ActionListener {

	private AsignacionView asignacionView;

	public AsignacionView getAsignacionView() {
		return asignacionView;
	}

	public void setAsignacionView(AsignacionView asignacionView) {
		this.asignacionView = asignacionView;
	}

	public OrdenesTrabajos[] getOTPendientes() {

		OrdenesTrabajosDao otDao = new OrdenesTrabajosDao();
		
		return (OrdenesTrabajos[]) otDao.getOrdenesTrabajos(true).toArray();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Seleccionar")) {

			PasosDao pasosDao = new PasosDao();

			OrdenesTrabajos ot = (OrdenesTrabajos) this.asignacionView.getNumerosOTCombo().getSelectedItem();
			ArrayList<Pasos> pasosList = pasosDao.getPasosByNumeroOT(ot.getNumero());
			ArrayList<Operarios> operariosList = pasosDao.getOperarios();

			this.asignacionView.addElementos(pasosList, operariosList);
		}
		
	}
}