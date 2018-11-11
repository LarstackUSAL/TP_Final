package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import model.dao.OrdenesTrabajosDao;
import model.dao.PasosDao;
import model.dto.Operarios;
import model.dto.OrdenesTrabajos;
import model.dto.PasosAsignados;
import utils.Utilities;
import view.RegistroView;

public class RegistroController implements ActionListener {

	private RegistroView registroView;

	public void setRegistroView(RegistroView registroView) {
		
		this.registroView = registroView;
	}

	public RegistroView getRegistroView() {
		return registroView;
	}

	public ArrayList<HashMap<String, String>> getPasosAsignadosOperario() {
		
		PasosDao pasosDao = new PasosDao();
		OrdenesTrabajosDao ordenesTrabajosDao = new OrdenesTrabajosDao();

		ArrayList<PasosAsignados> pasosList = pasosDao.getPasosAsignadosByOperario();
		
		ArrayList<HashMap<String,String>> pasosAsignadosList = new ArrayList<>();
		
		for (int i = 0; i < pasosList.size(); i++) {
			
			PasosAsignados paso = pasosList.get(i);

			OrdenesTrabajos ordenTrabajo = ordenesTrabajosDao.getOrdenTrabajoByPaso(paso);
			
			String ordenTrabajoCodigo = ordenTrabajo.getNumero();
			String productoDescripcion = paso.getProducto().getDescripcion();
			String tareaDescripcion = paso.getDescripcion();
			String idPaso = String.valueOf(paso.getId());

			Calendar calInicio = paso.getFechaInicio();
			String fechaInicio = calInicio != null ? Utilities.calendarToString(calInicio, "dd/MM/yyyy") : "";

			Calendar calFinalizacion = paso.getFechaFinalizacion();
			String fechaFinalizacion = calFinalizacion != null ? Utilities.calendarToString(calFinalizacion, "dd/MM/yyyy") : "";
			
			String esFinalizado = String.valueOf(paso.isEsFinalizado());

			HashMap<String, String> pasoAsignado = new HashMap<>();
			pasoAsignado.put("idPaso", idPaso);
			pasoAsignado.put("ordenTrabajoCodigo", ordenTrabajoCodigo);
			pasoAsignado.put("productoDescripcion", productoDescripcion);
			pasoAsignado.put("tareaDescripcion", tareaDescripcion);
			pasoAsignado.put("fechaInicio", fechaInicio);
			pasoAsignado.put("fechaFinalizacion", fechaFinalizacion);
			pasoAsignado.put("esFinalizado", esFinalizado);

			pasosAsignadosList.add(pasoAsignado);
		}
		
		return pasosAsignadosList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		PasosDao pasosDao = new PasosDao();

		if(e.getActionCommand().substring(0, e.getActionCommand().indexOf("-")).equals("iniciar")) {
			
			int idPaso = Integer.parseInt(e.getActionCommand().substring(e.getActionCommand().indexOf("-") + 1, e.getActionCommand().length()));
			
			boolean persistenciaOk = pasosDao.iniciarPaso(idPaso);
			
			if(persistenciaOk) {

				this.registroView.mostrarMensajeDialog("Datos guardados con exito!", "Exito");
			
			}else {
				
				this.registroView.mostrarMensajeDialog("Se ha verificado un error de persistencia.", "ERROR");
			}
			
		}else if(e.getActionCommand().substring(0, e.getActionCommand().indexOf("-")).equals("finalizar")) {
			
			int idPaso = Integer.parseInt(e.getActionCommand().substring(e.getActionCommand().indexOf("-") + 1, e.getActionCommand().length()));
			
			boolean persistenciaOk = pasosDao.finalizarPaso(idPaso);
			
			if(persistenciaOk) {

				this.registroView.mostrarMensajeDialog("Datos guardados con exito!", "Exito");
			
			}else {
				
				this.registroView.mostrarMensajeDialog("Se ha verificado un error de persistencia.", "ERROR");
			}
		}
	}
}