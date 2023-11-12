package com.FoodMakerServices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "receta")
@Data
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
}
