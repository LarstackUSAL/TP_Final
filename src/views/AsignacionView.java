package views;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controllers.AsignacionController;
import model.dao.ProductosDao;
import model.dto.Operarios;
import model.dto.OrdenesTrabajos;
import model.dto.Pasos;
import utils.Constants;

public class AsignacionView {

	private AsignacionController asignacionController;
	
	private JFrame ventana;
	private JLabel numerosOtLabel;
	private JComboBox numerosOTCombo;
	private JButton seleccionarBtn;
	private JButton asignarBtn;
	ArrayList<JLabel> tareasLabelList = new ArrayList<>();
	ArrayList<JComboBox> operariosComboList = new ArrayList<>();
	private ArrayList<Pasos> pasosList = new ArrayList<>();

	public AsignacionView(AsignacionController asignacionController) {

		asignacionController.setAsignacionView(this);
		this.asignacionController = asignacionController;
		
		ventana = new JFrame("Asignar recursos");		
		ventana.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);

		seleccionarBtn = new JButton("Seleccionar");
		seleccionarBtn.setActionCommand("Seleccionar");
		seleccionarBtn.addActionListener(this.asignacionController);

		Object[] OTVector = this.asignacionController.getOTPendientes();		
		
		if(OTVector.length == 0) {
			
			OTVector = new Object[] {"No hay OT sin asignar"};
			this.seleccionarBtn.setVisible(false);
		}
		
		numerosOtLabel = new JLabel("Numeros OT");
		numerosOTCombo = new JComboBox(OTVector);
		
		ventana.add(numerosOtLabel);
		ventana.add(numerosOTCombo);
		ventana.add(seleccionarBtn);

		ventana.setVisible(true);
	}

	public void addElementos(ArrayList<Pasos> pasosList, ArrayList<Operarios> operariosList) {

		this.setPasosList(pasosList);

		tareasLabelList = new ArrayList<>();
		operariosComboList = new ArrayList<>();
		for (int i = 0; i < pasosList.size(); i++) {
			
			JLabel tareaLabel = new JLabel(pasosList.get(i).getDescripcion());
			tareasLabelList.add(tareaLabel);
			ventana.add(tareaLabel);
			
			JComboBox combo = new JComboBox(operariosList.toArray());
			operariosComboList.add(combo);
			
			ventana.add(combo);

		}
		
		asignarBtn = new JButton("Asignar");
		asignarBtn.setActionCommand("Asignar");
		asignarBtn.addActionListener(this.asignacionController);
		ventana.add(asignarBtn);
		
		ventana.setVisible(true);
	}

	public void mostrarMensajeDialog(String mensajeBody, String titulo) {

		JOptionPane.showMessageDialog(null, mensajeBody, titulo, JOptionPane.INFORMATION_MESSAGE);
		this.cerrar();
	}
	
	public void cerrar() {
		ventana.dispose();	
	}
	
	public AsignacionController getAsignacionController() {
		return asignacionController;
	}

	public void setAsignacionController(AsignacionController asignacionController) {
		this.asignacionController = asignacionController;
	}

	public JFrame getVentana() {
		return ventana;
	}

	public void setVentana(JFrame ventana) {
		this.ventana = ventana;
	}

	public JLabel getNumerosOtLabel() {
		return numerosOtLabel;
	}

	public void setNumerosOtLabel(JLabel numerosOtLabel) {
		this.numerosOtLabel = numerosOtLabel;
	}

	public JComboBox getNumerosOTCombo() {
		return numerosOTCombo;
	}

	public void setNumerosOTCombo(JComboBox numerosOTCombo) {
		this.numerosOTCombo = numerosOTCombo;
	}

	public JButton getSeleccionarBtn() {
		return seleccionarBtn;
	}

	public void setSeleccionarBtn(JButton seleccionarBtn) {
		this.seleccionarBtn = seleccionarBtn;
	}

	public JButton getAsignarBtn() {
		return asignarBtn;
	}

	public void setAsignarBtn(JButton asignarBtn) {
		this.asignarBtn = asignarBtn;
	}

	public ArrayList<JLabel> getTareasLabelList() {
		return tareasLabelList;
	}

	public void setTareasLabelList(ArrayList<JLabel> tareasLabelList) {
		this.tareasLabelList = tareasLabelList;
	}

	public ArrayList<JComboBox> getOperariosComboList() {
		return operariosComboList;
	}

	public void setOperariosComboList(ArrayList<JComboBox> operariosComboList) {
		this.operariosComboList = operariosComboList;
	}

	public ArrayList<Pasos> getPasosList() {
		return pasosList;
	}

	public void setPasosList(ArrayList<Pasos> pasosList) {
		this.pasosList = pasosList;
	}

}