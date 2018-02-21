package views;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controllers.AsignacionController;
import model.dto.Operarios;
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

	public AsignacionView(AsignacionController asignacionController) {

		asignacionController.setAsignacionView(this);
		this.asignacionController = asignacionController;
		
		ventana = new JFrame("Asignar recursos");		
		ventana.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);
		//descomentar
//		OrdenesTrabajos[] OTVector = this.asignacionController.getOTPendientes();
		
		//testing
		String[] OTVector = {"orden1","orden2"};
		
		numerosOtLabel = new JLabel("Numeros OT");
		numerosOTCombo = new JComboBox(OTVector);
		seleccionarBtn = new JButton("Seleccionar");
		seleccionarBtn.setActionCommand("Seleccionar");
		seleccionarBtn.addActionListener(this.asignacionController);
		
		ventana.add(numerosOtLabel);
		ventana.add(numerosOTCombo);
		ventana.add(seleccionarBtn);

		ventana.setVisible(true);
	}

	public void addElementos(ArrayList<Pasos> pasosList, ArrayList<Operarios> operariosList) {

		//testing
		pasosList = new ArrayList<>();
		pasosList.add(new Pasos(null,null,0,"paso 1"));
		pasosList.add(new Pasos(null,null,0,"paso 2"));
		pasosList.add(new Pasos(null,null,0,"paso 3"));
		//testing
		operariosList = new ArrayList<>();
		operariosList.add(new Operarios("nombre1", "apellido1", null));
		operariosList.add(new Operarios("nombre2", "apellido2", null));
		operariosList.add(new Operarios("nombre3", "apellido3", null));
		operariosList.add(new Operarios("nombre4", "apellido4", null));
		/////////

		tareasLabelList = new ArrayList<>();
		operariosComboList = new ArrayList<>();
		for (int i = 0; i < pasosList.size(); i++) {
			
			JLabel tareaLabel = new JLabel(pasosList.get(i).getDescripcion());
			tareasLabelList.add(tareaLabel);
			ventana.add(tareaLabel);

			ventana.add(new JComboBox(operariosList.toArray()));

		}
		
		asignarBtn = new JButton("Asignar");
		asignarBtn.setActionCommand("Asignar");
		asignarBtn.addActionListener(this.asignacionController);
		ventana.add(asignarBtn);
		
		ventana.setVisible(true);
	}

}