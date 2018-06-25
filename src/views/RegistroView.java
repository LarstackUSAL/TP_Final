package views;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controllers.RegistroController;
import utils.Constants;

public class RegistroView {

	private RegistroController registroController;
	
	private JFrame ventana;

	public RegistroView(RegistroController registroController) {

		this.registroController = registroController;
		this.registroController.setRegistroView(this);
		
		this.ventana = new JFrame("Registrar Tareas");
		ventana.setSize(Constants.VENTANA_ANCHO, Constants.VENTANA_ALTO);
		ventana.setLayout(Constants.ESTILO_LAYOUT);
		ventana.setLocationRelativeTo(null);

		ArrayList<HashMap<String,String>> pasosList = this.registroController.getPasosAsignadosOperario(null);
		
		for (Iterator iterator = pasosList.iterator(); iterator.hasNext();) {
			
			HashMap<String, String> pasoAsignado = (HashMap<String, String>) iterator.next();
			
			JLabel ordenTrabajoLabel = new JLabel("Orden de Trabajo: ");
			ordenTrabajoLabel.setFont(ordenTrabajoLabel.getFont().deriveFont(Font.BOLD));
			JLabel ordenTrabajoCodigo = new JLabel(pasoAsignado.get("ordenTrabajoCodigo"));
			ordenTrabajoCodigo.setFont(ordenTrabajoLabel.getFont().deriveFont(Font.PLAIN));

			JLabel productoLabel = new JLabel("Producto: ");
			productoLabel.setFont(productoLabel.getFont().deriveFont(Font.BOLD));
			JLabel productoDescripcion = new JLabel(pasoAsignado.get("productoDescripcion"));
			productoDescripcion.setFont(productoDescripcion.getFont().deriveFont(Font.PLAIN));
			
			JLabel tareaLabel = new JLabel("Tarea: ");
			tareaLabel.setFont(tareaLabel.getFont().deriveFont(Font.BOLD));
			JLabel tareaDescripcion = new JLabel(pasoAsignado.get("tareaDescripcion"));
			tareaDescripcion.setFont(tareaDescripcion.getFont().deriveFont(Font.PLAIN));
			
			JLabel fechaInicioLabel = new JLabel("Fecha inicio: ");
			fechaInicioLabel.setFont(fechaInicioLabel.getFont().deriveFont(Font.BOLD));
			JLabel fechaInicio = new JLabel(pasoAsignado.get("fechaInicio"));
			fechaInicio.setFont(fechaInicio.getFont().deriveFont(Font.PLAIN));
			
			JLabel fechaFinalizacionLabel = new JLabel("Fecha finalizacion: ");
			fechaFinalizacionLabel.setFont(fechaFinalizacionLabel.getFont().deriveFont(Font.BOLD));
			JLabel fechaFinalizacion = new JLabel(pasoAsignado.get("fechaFinalizacion"));
			fechaFinalizacion.setFont(fechaFinalizacion.getFont().deriveFont(Font.PLAIN));
			
			JButton iniciarBtn = new JButton("Iniciar tarea");
			iniciarBtn.setActionCommand("iniciar-" + pasoAsignado.get("idPaso"));
			iniciarBtn.addActionListener(this.registroController);

			JButton finalizarBtn = new JButton("Finalizar tarea");
			finalizarBtn.setActionCommand("finalizar-" + pasoAsignado.get("idPaso"));
			finalizarBtn.addActionListener(this.registroController);

			this.ventana.add(ordenTrabajoLabel);
			this.ventana.add(ordenTrabajoCodigo);
			this.ventana.add(productoLabel);
			this.ventana.add(productoDescripcion);
			this.ventana.add(tareaLabel);
			this.ventana.add(tareaDescripcion);
			this.ventana.add(fechaInicioLabel);
			this.ventana.add(fechaInicio);
			this.ventana.add(fechaFinalizacionLabel);
			this.ventana.add(fechaFinalizacion);
			
			if(pasoAsignado.get("fechaInicio").trim().isEmpty())
				this.ventana.add(iniciarBtn);
			else if(pasoAsignado.get("fechaFinalizacion").trim().isEmpty()) this.ventana.add(finalizarBtn);
		}
		
		this.ventana.setVisible(true);
	}

	public void mostrarMensajeDialog(String mensajeBody, String titulo) {

		JOptionPane.showMessageDialog(null, mensajeBody, titulo, JOptionPane.INFORMATION_MESSAGE);
		this.cerrar();
	}
	
	public void cerrar() {
		ventana.dispose();	
	}
}