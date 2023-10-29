package com.FoodMakerServices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "receta")
public class Receta {
	@Id
	private int idreceta;
	private String nombre;
	private String descripcion;
	private String decripcioncorta;
	private String instrucciones;
	private int tiempopreparacion;
	private int idimagen;
	private int idcategoria;
	
	public int getIdreceta() {
		return idreceta;
	}
	public void setIdreceta(int idreceta) {
		this.idreceta = idreceta;
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
	public String getDecripcioncorta() {
		return decripcioncorta;
	}
	public void setDecripcioncorta(String decripcioncorta) {
		this.decripcioncorta = decripcioncorta;
	}
	public String getInstrucciones() {
		return instrucciones;
	}
	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}
	public int getTiempopreparacion() {
		return tiempopreparacion;
	}
	public void setTiempopreparacion(int tiempopreparacion) {
		this.tiempopreparacion = tiempopreparacion;
	}
	public int getIdimagen() {
		return idimagen;
	}
	public void setIdimagen(int idimagen) {
		this.idimagen = idimagen;
	}
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
}
