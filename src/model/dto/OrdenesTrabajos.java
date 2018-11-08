package model.dto;

import java.util.Calendar;

public class OrdenesTrabajos {
	
	private int id;
	private String numero;
	private Calendar fechaAlta;
	private Productos producto;
	private int cantidadRequerida;
	private Calendar fechaEstimadaFinalizacion;
	private Calendar fechaFinalizacion;
	private String descripcion;
	private boolean esUrgente;
	
	private String usuarioCreacion;
	
	public OrdenesTrabajos(int id, String numero, Calendar fechaAlta, Productos producto, int cantidadRequerida,
			Calendar fechaEstimadaFinalizacion, Calendar fechaFinalizacion, String descripcion, boolean esUrgente) {
		super();
		this.setId(id);
		this.numero = numero;
		this.fechaAlta = fechaAlta;
		this.producto = producto;
		this.cantidadRequerida = cantidadRequerida;
		this.fechaEstimadaFinalizacion = fechaEstimadaFinalizacion;
		this.fechaFinalizacion = fechaFinalizacion;
		this.descripcion = descripcion;
		this.esUrgente = esUrgente;
	}
	
	public OrdenesTrabajos() {
		super();
	}

	@Override
	public String toString() {

		return this.numero + " - " + this.producto.getDescripcion();
	}

	public Calendar getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Calendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	
	public int getCantidadRequerida() {
		return cantidadRequerida;
	}
	
	public void setCantidadRequerida(int cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}
	
	public Calendar getFechaEstimadaFinalizacion() {
		return fechaEstimadaFinalizacion;
	}
	
	public void setFechaEstimadaFinalizacion(Calendar fechaEstimadaFinalizacion) {
		this.fechaEstimadaFinalizacion = fechaEstimadaFinalizacion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean isEsUrgente() {
		return esUrgente;
	}
	
	public void setEsUrgente(boolean esUrgente) {
		this.esUrgente = esUrgente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Calendar getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Calendar fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
}