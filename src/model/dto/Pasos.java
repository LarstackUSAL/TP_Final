package model.dto;

public class Pasos {

	private String codigoProducto;
	private MateriasPrimas materiaPrima;
	private int cantidadMateriaPrima;
	
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
	
	
}
