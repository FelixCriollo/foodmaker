package com.FoodMakerServices.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "ingrediente")
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idingrediente;
	private String nombre;
	private String descripcion;
	private int cantidad;
	
	
	public int getIdingrediente() {
		return idingrediente;
	}
	public void setIdingrediente(int idingrediente) {
		this.idingrediente = idingrediente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
