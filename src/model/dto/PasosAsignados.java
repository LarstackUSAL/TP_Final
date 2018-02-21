package model.dto;

import java.util.Calendar;

public class PasosAsignados extends Pasos {

	private Operarios operario;
	private boolean esFinalizado;
	private Calendar fechaFinalizacion;
	
	public Usuarios getOperario() {
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
	public Calendar getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(Calendar fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	
	
}
