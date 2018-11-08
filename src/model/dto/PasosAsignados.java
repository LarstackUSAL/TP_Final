package model.dto;

import java.util.Calendar;

public class PasosAsignados extends Pasos {

	private Operarios operario;
	private boolean esFinalizado;
	private Calendar fechaInicio;
	private Calendar fechaFinalizacion;
	private Calendar usuarioAsignacion;
	
	public Operarios getOperario() {
		return operario;
	}
	public void setOperario(Operarios operario) {
		this.operario = operario;
	}
	public boolean isEsFinalizado() {
		return esFinalizado;
	}
	public void setEsFinalizado(boolean esFinalizado) {
		this.esFinalizado = esFinalizado;
	}
	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Calendar getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(Calendar fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	public Calendar getUsuarioAsignacion() {
		return usuarioAsignacion;
	}
	public void setUsuarioAsignacion(Calendar usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}
	
	
}
