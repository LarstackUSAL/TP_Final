package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import model.dao.OperariosDao;
import model.dao.OrdenesTrabajosDao;
import model.dao.PasosDao;
import model.dao.ProductosDao;
import model.dto.Operarios;
import model.dto.OrdenesTrabajos;
import model.dto.Pasos;
import utils.Utilities;
import view.AsignacionView;

public class AsignacionController implements ActionListener {

	private AsignacionView asignacionView;

	public AsignacionView getAsignacionView() {
		return asignacionView;
	}

	public void setAsignacionView(AsignacionView asignacionView) {
		this.asignacionView = asignacionView;
	}

	public Object[] getOTPendientes() {

		OrdenesTrabajosDao otDao = new OrdenesTrabajosDao();
		
		return otDao.getOrdenesTrabajos(true).toArray();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Seleccionar")) {

			PasosDao pasosDao = new PasosDao();
			OperariosDao operariosDao = new OperariosDao();

			OrdenesTrabajos ot = (OrdenesTrabajos) this.asignacionView.getNumerosOTCombo().getSelectedItem();
			ArrayList<Pasos> pasosList = pasosDao.getPasosByNumeroOT(ot.getNumero());
			ArrayList<Operarios> operariosList = operariosDao.loadOperarios();

			this.asignacionView.getSeleccionarBtn().setVisible(false);
			this.asignacionView.addElementos(pasosList, operariosList);
			
		}else if(e.getActionCommand().equals("Asignar")) {
			
			boolean persistenciaOk = false;				
			persistenciaOk = this.guardarAsignacion();

			if(persistenciaOk) {

				this.asignacionView.mostrarMensajeDialog("Datos guardados con exito!", "Exito");
			
			}else {
				
				this.asignacionView.mostrarMensajeDialog("Se ha verificado un error de persistencia.", "ERROR");
			}
		}
		
	}
	
	private boolean guardarAsignacion() {

		OrdenesTrabajosDao otDao = new OrdenesTrabajosDao();
		ProductosDao productosDao = new ProductosDao();
		PasosDao pasosDao = new PasosDao();

		OrdenesTrabajos ot = (OrdenesTrabajos) this.asignacionView.getNumerosOTCombo().getSelectedItem();
		ArrayList<Pasos> pasosList = this.asignacionView.getPasosList();
		
		boolean persistenciaOk = false;

		for (int i = 0; i < pasosList.size(); i++) {

			Pasos paso = pasosList.get(i);
			Operarios operario = (Operarios) this.asignacionView.getOperariosComboList().get(i).getSelectedItem();
			
			persistenciaOk = pasosDao.asignarTarea(ot, paso, operario);
			
			if(!persistenciaOk) break;
		}
		
		return persistenciaOk;
	}
}