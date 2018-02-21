package model.dto;

public class Pasos {

	private String codigoProducto;
	private MateriasPrimas materiaPrima;
	private int cantidadMateriaPrima;
	private String descripcion;
	
	public Pasos() {};
	public Pasos(String codigoProducto, MateriasPrimas materiaPrima, int cantidadMateriaPrima, String descripcion) {
		super();
		this.codigoProducto = codigoProducto;
		this.materiaPrima = materiaPrima;
		this.cantidadMateriaPrima = cantidadMateriaPrima;
		this.descripcion = descripcion;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public int getCantidadMateriaPrima() {
		return cantidadMateriaPrima;
	}
	public void setCantidadMateriaPrima(int cantidadMateriaPrima) {
		this.cantidadMateriaPrima = cantidadMateriaPrima;
	}
	public MateriasPrimas getMateriaPrima() {
		return materiaPrima;
	}
	public void setMateriaPrima(MateriasPrimas materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
