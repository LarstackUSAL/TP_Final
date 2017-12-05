package model.dto;

import java.util.Calendar;

public class OrdenTrabajo {
	
	private String numero;
	private Calendar fechaAlta;
	private String codigoProducto;
	private int cantidadRequerida;
	private Calendar fechaEstimadaFinalizacion;
	private String descripcion;
	private boolean esUrgente;
	
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
	
}