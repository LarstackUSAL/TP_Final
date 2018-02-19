package views;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import controllers.AsignacionController;
import model.dto.OrdenesTrabajos;

public class AsignacionView {

	private AsignacionController asignacionController;
	
	private JLabel numerosOtLabel;
	private JComboBox numerosOTCombo;
	
	public AsignacionView(AsignacionController asignacionController) {

		asignacionController.setAsignacionView(this);
		this.asignacionController = asignacionController;
		
		OrdenesTrabajos[] OTVector = this.asignacionController.getOTPendientes();
		
		numerosOtLabel = new JLabel("Numeros OT");
		
		
		numerosOTCombo = new 
	}

}