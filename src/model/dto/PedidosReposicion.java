package model.dto;

import java.util.Calendar;

public class PedidosReposicion {

	private int numeroPedido;
	private Calendar fecha;
	private MateriasPrimas materiaPrima;
	private int cantidad;
	
	public int getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
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
