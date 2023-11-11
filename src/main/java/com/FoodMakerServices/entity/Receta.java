package com.FoodMakerServices.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "receta")
@Data
public class Receta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idreceta;
	private String nombre;
	private String descripcion;
	private String descripcioncorta;
	private String instrucciones;
	private int tiempopreparacion;
	private int idimagen;
	private int idcategoria;
	@ManyToOne
	@JoinColumn(name="idcategoria",insertable = false,updatable = false)
	private Categoria objCategoria;

}
