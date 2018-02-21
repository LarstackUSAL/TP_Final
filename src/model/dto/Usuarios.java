package model.dto;

public class Usuarios {

	private String usuario;
	private RolEnum rol;

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public RolEnum getRol() {
		return rol;
	}
	public void setRol(RolEnum rol) {
		this.rol = rol;
	}
}
