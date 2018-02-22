package model.dto;

import java.util.ArrayList;

public class Pasos {

	private Productos producto;
	private String descripcion;
	private ArrayList<MateriasPrimasCantidad> materiasPrimas;

	public Pasos() {};

	public Pasos(Productos producto, ArrayList<MateriasPrimasCantidad> materiasPrimas, String descripcion) {
		super();
		this.producto = producto;
		this.materiasPrimas = materiasPrimas;
		this.descripcion = descripcion;
	}

	public Productos getProducto() {
		return producto;
	}
	public void setProducto(Productos producto) {
		this.producto = producto;
	}
	public ArrayList<MateriasPrimasCantidad> getMateriasPrimas() {
		return materiasPrimas;
	}
	public void setMateriasPrimas(ArrayList<MateriasPrimasCantidad> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
