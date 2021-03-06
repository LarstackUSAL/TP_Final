package model.dto;

public class Operarios extends Usuarios {

	private int id;
	private String nombre;
	private String apellido;
	private String legajo;

	public Operarios() {}
	public Operarios(int id, String nombre, String apellido, String legajo) {
		super();
		this.setId(id);
		this.nombre = nombre;
		this.apellido = apellido;
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	
	@Override
	public String toString() {

		return nombre + " " + apellido;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
