package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controllers.ConsultaTareasController;
import model.dto.PasosAsignados;
import utils.Constants;
import utils.Utilities;

public class ConsultaTareasView {

	private ConsultaTareasController consultaTareasController;
	private JTable grilla;
	private JFrame ventana;
	private JScrollPane scroll;
	private DefaultTableModel tableModel;
	private JLabel labelOrdenTrabajo;
	private JComboBox otCombo;
	private JButton btnConsultar;

	public ConsultaTareasView(ConsultaTareasController consultaTareasController) {
		
		consultaTareasController.setConsultaTareasView(this);
		this.consultaTareasController = consultaTareasController;
		
		this.ventana = new JFrame("Consulta tareas");
		ventana.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);

		Object[] otVector = this.consultaTareasController.getOrdenesTrabajo();
		labelOrdenTrabajo = new JLabel("OT: ");
		otCombo = new JComboBox(otVector);
		
		btnConsultar =  new JButton("Consultar");
		this.btnConsultar.setActionCommand("Consultar");
		this.btnConsultar.addActionListener(this.consultaTareasController);
		
		this.grilla = new JTable();
		this.tableModel = (DefaultTableModel) this.grilla.getModel();

		this.tableModel.setColumnIdentifiers(new String[] {
				
				"Producto",
				"Tarea",
				"Operario",
				"Fecha inicio",
				"Fecha finalización"
		});
		
		this.scroll = new JScrollPane(grilla);
		this.grilla.setPreferredScrollableViewportSize(ventana.getSize());

		this.grilla.setVisible(true);
		this.grilla.setEnabled(false);
		
		ventana.add(labelOrdenTrabajo);
		ventana.add(otCombo);
		ventana.add(btnConsultar);
		ventana.add(scroll);

		ventana.setVisible(true);
	}
	
	public void addRegistros(ArrayList<PasosAsignados> pasosAsignadosList) {

		this.tableModel.getDataVector().removeAllElements();

		for (int i = 0; i < pasosAsignadosList.size(); i++) {
			
			PasosAsignados paso = pasosAsignadosList.get(i);
			
			String producto = paso.getProducto().getDescripcion();
			String tarea = paso.getDescripcion();
			String operario = paso.getOperario().getNombre() + " " + paso.getOperario().getApellido();
			String fechaInicio = paso.getFechaInicio() != null ? Utilities.calendarToString(paso.getFechaInicio(), "dd/MM/yyyy") : "No informada";
			String fechaFinalizacion = paso.getFechaFinalizacion() != null ? Utilities.calendarToString(paso.getFechaFinalizacion(), "dd/MM/yyyy") : "No informada";

			this.tableModel.addRow(new String[] {producto,tarea,operario,fechaInicio,fechaFinalizacion});

		}
		
		if(pasosAsignadosList.isEmpty()) {
			
			this.mostrarMensajeDialog("No existen tareas asignadas para la orden de trabajo seleccionada", "Tareas no encontradas");
		}
		
		this.setWidthColumnas(this.ventana.getWidth(), 40, 90, 50, 35, 35);
		this.grilla.setVisible(true);
	}

	public void setWidthColumnas(int tablePreferredWidth, double... percentages) {
	    
		double total = 0;
	    for (int i = 0; i < this.grilla.getColumnModel().getColumnCount(); i++) {
	        total += percentages[i];
	    }
	 
	    for (int i = 0; i < this.grilla.getColumnModel().getColumnCount(); i++) {
	        TableColumn column = this.grilla.getColumnModel().getColumn(i);
	        column.setPreferredWidth((int)(tablePreferredWidth * (percentages[i] / total)));
	    }
	}

	public void mostrarMensajeDialog(String mensajeBody, String titulo) {

		JOptionPane.showMessageDialog(null, mensajeBody, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public JComboBox getOtCombo() {
		return otCombo;
	}

	public void setOtCombo(JComboBox otCombo) {
		this.otCombo = otCombo;
	}

	public JTable getGrilla() {
		return grilla;
	}

	public void setGrilla(JTable grilla) {
		this.grilla = grilla;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JLabel getLabelOrdenTrabajo() {
		return labelOrdenTrabajo;
	}

	public void setLabelOrdenTrabajo(JLabel labelOrdenTrabajo) {
		this.labelOrdenTrabajo = labelOrdenTrabajo;
	}
}