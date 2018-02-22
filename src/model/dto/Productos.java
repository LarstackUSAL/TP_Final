package model.dto;

public class Productos {

	String codigoProducto;
	String descripcion;
	
	public Productos() {}

	public Productos(String codigoProducto, String descripcion) {
		super();
		this.codigoProducto = codigoProducto;
		this.descripcion = descripcion;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
