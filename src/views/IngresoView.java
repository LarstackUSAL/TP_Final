package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.IngresoController;
import model.dao.ProductosDao;
import utils.Constants;

public class IngresoView {

	private IngresoController ingresoController;
	private JFrame ventanaFrame = new JFrame("Ingreso OT");
	private JLabel codigoProductoLabel = new JLabel("Código producto:");
	private JTextField codigoProductoTextField;
	private JLabel cantidadLabel = new JLabel("Cantidad:");
	private JTextField cantidadTextField;
	private JLabel fechaEstimadaFinalizacionLabel = new JLabel("Fecha estimada finalización:");
	private JTextField fechaEstimadaFinalizacionTextField;
	private JLabel descripcionLabel = new JLabel("Descripción:");
	private JTextArea descripcionTextArea;
	private JCheckBox esUrgenteCheckBox;
	private JButton ingresarButton;

	public IngresoView(IngresoController ingresoController) {

		ingresoController.setIngresoView(this);
		this.ingresoController = ingresoController;

		ventanaFrame.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventanaFrame.setLayout(Constants.ESTILO_LAYOUT);
		ventanaFrame.setLocationRelativeTo(null);

		codigoProductoLabel = new JLabel("Código producto:");
		codigoProductoTextField = new JTextField(Constants.TEXTO_ANCHO);
		codigoProductoTextField.setCaretPosition(SwingConstants.CENTER);
		codigoProductoTextField.setHorizontalAlignment(JTextField.CENTER);

		cantidadLabel = new JLabel("Cantidad:");
		cantidadTextField = new JTextField(Constants.TEXTO_ANCHO);
		cantidadTextField.setCaretPosition(SwingConstants.CENTER);
		cantidadTextField.setHorizontalAlignment(JTextField.CENTER);

		descripcionLabel = new JLabel("Descripción:");
		descripcionTextArea = new JTextArea();
		descripcionTextArea.setEditable(true);	
		descripcionTextArea.setLineWrap(true);	
		descripcionTextArea.setWrapStyleWord(true); 
		descripcionTextArea.setCaretPosition(SwingConstants.CENTER);
//		descripcionTextArea.setBackground(ventanaFrame.getBackground());
		descripcionTextArea.setBackground(new Color(255,255,255));
		descripcionTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		descripcionTextArea.setFont(new Font(descripcionTextArea.getFont().getName(), Font.ITALIC, 
		descripcionTextArea.getFont().getSize()));
		descripcionTextArea.setVisible(true);
		
		fechaEstimadaFinalizacionTextField = new JTextField(Constants.TEXTO_ANCHO);
		fechaEstimadaFinalizacionTextField.setCaretPosition(SwingConstants.CENTER);
		fechaEstimadaFinalizacionTextField.setHorizontalAlignment(JTextField.CENTER);
		fechaEstimadaFinalizacionTextField.setText("dd/mm/yyyy");
		fechaEstimadaFinalizacionTextField.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				fechaEstimadaFinalizacionTextField.setText("");
				super.mousePressed(e);
			}
		});

		esUrgenteCheckBox = new JCheckBox("Es urgente");

		ingresarButton = new JButton("Ingresar");
		ingresarButton.setActionCommand("Ingresar");
		ingresarButton.addActionListener(this.ingresoController);
		
		Component[] componentes = {codigoProductoLabel,codigoProductoTextField,cantidadLabel,cantidadTextField,
				fechaEstimadaFinalizacionLabel,fechaEstimadaFinalizacionTextField,
				descripcionLabel,descripcionTextArea,esUrgenteCheckBox,ingresarButton};

		for (int i = 0; i < componentes.length; i++) {

			ventanaFrame.add(componentes[i]);
		}

		ventanaFrame.setVisible(true);

	}
	
	public IngresoController getIngresoController() {
		return ingresoController;
	}

	public void setIngresoController(IngresoController ingresoController) {
		this.ingresoController = ingresoController;
	}

	public JTextField getCodigoProductoTextField() {
		return codigoProductoTextField;
	}

	public JTextField getCantidadTextField() {
		return cantidadTextField;
	}

	public JTextField getFechaEstimadaFinalizacionTextField() {
		return fechaEstimadaFinalizacionTextField;
	}

	public JTextArea getDescripcionTextArea() {
		return descripcionTextArea;
	}

	public JCheckBox getEsUrgenteCheckBox() {
		return esUrgenteCheckBox;
	}

	public boolean validar() {

		ProductosDao productosDao = new ProductosDao();
		ArrayList<String> errores = new ArrayList<>();
		boolean datosValidos = true;
		
		if(this.codigoProductoTextField.getText().trim().isEmpty()){

			errores.add("código del producto");
		}else {
			
			if(productosDao.loadProducto(this.codigoProductoTextField.getText().trim()) == null)
				errores.add("código del producto");
		}

		if(this.cantidadTextField.getText().trim().isEmpty()){
			
			errores.add("cantidad");
		}else {
			
			try {
			
				Integer.parseInt(this.cantidadTextField.getText().trim());
			
			}catch(NumberFormatException e) {
				
				errores.add("cantidad");
			}
			
		}
		if(this.fechaEstimadaFinalizacionTextField.getText().trim().isEmpty()) {
			
			errores.add("fecha estimada finalización");
		}else{

			try{
				int dia = Integer.valueOf(this.fechaEstimadaFinalizacionTextField.getText().trim().substring(0,2));
				int mes = Integer.valueOf(this.fechaEstimadaFinalizacionTextField.getText().trim().substring(3,5));
				int anio = Integer.valueOf(this.fechaEstimadaFinalizacionTextField.getText().trim().substring(6,10));

				if(!((dia > 0 && dia <=31) && (mes > 0 && mes <= 12) && (anio >= 2017 && anio <= 9999))){

					errores.add("fecha finalización");
				}
			}catch(NumberFormatException e){

				errores.add("fecha finalización");
			}
		}

		if(!errores.isEmpty()){

			String error = "Los datos de:\n";

			for (int i = 0; i < errores.size(); i++) {

				error = error + "\n- " + errores.get(i);
			}

			error += "\n\nson invalidos.";

			datosValidos = false;

			JOptionPane.showMessageDialog(null, error, "Datos obligatorios", 
					JOptionPane.INFORMATION_MESSAGE);
		}

		return datosValidos;
	}

	public void mostrarMensajeDialog(String mensajeBody, String titulo) {

		JOptionPane.showMessageDialog(null, mensajeBody, titulo, JOptionPane.INFORMATION_MESSAGE);
		this.cerrar();
	}

	public void cerrar() {
		ventanaFrame.dispose();	
	}
}