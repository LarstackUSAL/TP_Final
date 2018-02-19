package controllers;

import model.dao.OrdenesTrabajosDao;
import model.dto.OrdenesTrabajos;
import views.AsignacionView;

public class AsignacionController {

	private AsignacionView asignacionView;

	public AsignacionView getAsignacionView() {
		return asignacionView;
	}

	public void setAsignacionView(AsignacionView asignacionView) {
		this.asignacionView = asignacionView;
	}

	public OrdenesTrabajos[] getOTPendientes() {

		OrdenesTrabajosDao otDao = new OrdenesTrabajosDao();
		
		return otDao.getOTPendientes();
	}
}