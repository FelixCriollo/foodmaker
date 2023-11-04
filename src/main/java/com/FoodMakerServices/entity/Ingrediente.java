package com.FoodMakerServices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table (name = "ingrediente")
@Data
public class Ingrediente {
	@Id
	private int idingrediente;
	private String nombre;
	private String descripcion;
	private int cantidad;

}
