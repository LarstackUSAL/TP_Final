package model.dto;

public class Pasos {

	private String codigoProducto;
	private MateriasPrimas materiaPrima;
	private int cantidad;
	
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public MateriasPrimas getMateriaPrima() {
		return materiaPrima;
	}
	public void setMateriaPrima(MateriasPrimas materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	
	
}
