package model.dto;

import java.util.ArrayList;

public class Pasos {

	private int id;
	private Productos producto;
	private String descripcion;
	private ArrayList<MateriasPrimasCantidad> materiasPrimas;

	public Pasos() {};

	public Pasos(int id, Productos producto, ArrayList<MateriasPrimasCantidad> materiasPrimas, String descripcion) {
		super();
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
