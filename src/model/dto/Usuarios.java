package model.dto;

public class Usuarios {

	private String legajo;
	private String nombre;
	private RolEnum rol;
	
	public String getLegajo() {
		return legajo;
	}
	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public RolEnum getRol() {
		return rol;
	}
	public void setRol(RolEnum rol) {
		this.rol = rol;
	}
}
