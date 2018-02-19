package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import model.dao.OrdenesTrabajosDao;
import model.dto.OrdenesTrabajos;
import utils.Utilities;
import views.IngresoView;

public class IngresoController implements ActionListener {

	private IngresoView ingresoView;

	public void setIngresoView(IngresoView ingresoView) {
		
		this.ingresoView = ingresoView;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Ingresar")) {
			
			boolean persistenciaOk = false;
			
			if(ingresoView.validar()){
				
				persistenciaOk = this.guardarOT();
			}

			if(persistenciaOk) {

				this.ingresoView.mostrarMensajeDialog("Datos guardados con exito!", "Exito");
			}else{
				
				this.ingresoView.mostrarMensajeDialog("Se ha verificado un error de persistencia.", "ERROR");
			}
		}
		
	}

	private boolean guardarOT() {

		OrdenesTrabajosDao otDao = new OrdenesTrabajosDao();

		String numero = otDao.generarNumeroOrden();
		String codigoProducto = this.ingresoView.getCodigoProductoTextField().getText().trim();
		int cantidadRequerida = Integer.parseInt(this.ingresoView.getCantidadTextField().getText().trim());
		Calendar fechaEstimadaFinalizacion = Utilities.stringToCalendar(
						this.ingresoView.getFechaEstimadaFinalizacionTextField().getText().trim(), "dd/MM/yyyy");
		String descripcion = this.ingresoView.getDescripcionTextArea().getText();
		boolean esUrgente = this.ingresoView.getEsUrgenteCheckBox().isSelected();
		
		OrdenesTrabajos ot = new OrdenesTrabajos();
		ot.setCantidadRequerida(cantidadRequerida);
		ot.setCodigoProducto(codigoProducto);
		ot.setDescripcion(descripcion);
		ot.setEsUrgente(esUrgente);
		ot.setFechaEstimadaFinalizacion(fechaEstimadaFinalizacion);
		ot.setNumero(numero);

		return otDao.persistirNuevaOT(ot);
	}
}