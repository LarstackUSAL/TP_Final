package model.dto;

import java.util.Calendar;

public class PasosAsignados {

	private Pasos paso;
	private Usuarios usuario;
	private boolean esFinalizado;
	private Calendar fechaFinalizacion;
	
	public Pasos getPaso() {
		return paso;
	}
	public void setPaso(Pasos paso) {
		this.paso = paso;
	}
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	public boolean isEsFinalizado() {
		return esFinalizado;
	}
	public void setEsFinalizado(boolean esFinalizado) {
		this.esFinalizado = esFinalizado;
	}
	public Calendar getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(Calendar fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	
	
}
