package model.dto;

public class MateriasPrimasCantidad {

	private MateriasPrimas materiaPrima;
	private int cantidad;
	
	public MateriasPrimasCantidad(MateriasPrimas materiaPrima, int cantidad) {
		super();
		this.materiaPrima = materiaPrima;
		this.cantidad = cantidad;
	}

	public MateriasPrimasCantidad() {}
	
	public MateriasPrimas getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriasPrimas materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
