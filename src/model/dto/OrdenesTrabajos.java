package model.dto;

import java.util.Calendar;

public class OrdenesTrabajos {
	
	private String numero;
	private Calendar fechaAlta;
	private String codigoProducto;
	private int cantidadRequerida;
	private Calendar fechaEstimadaFinalizacion;
	private Calendar fechaFinalizacion;
	private String descripcion;
	private boolean esUrgente;
	
	public OrdenesTrabajos(String numero, Calendar fechaAlta, String codigoProducto, int cantidadRequerida,
			Calendar fechaEstimadaFinalizacion, Calendar fechaFinalizacion, String descripcion, boolean esUrgente) {
		super();
		this.numero = numero;
		this.fechaAlta = fechaAlta;
		this.codigoProducto = codigoProducto;
		this.cantidadRequerida = cantidadRequerida;
		this.fechaEstimadaFinalizacion = fechaEstimadaFinalizacion;
		this.setFechaFinalizacion(fechaFinalizacion);
		this.descripcion = descripcion;
		this.esUrgente = esUrgente;
	}
	
	public OrdenesTrabajos() {
		super();
	}

	public Calendar getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Calendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public String getCodigoProducto() {
		return codigoProducto;
	}
	
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
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
	
}