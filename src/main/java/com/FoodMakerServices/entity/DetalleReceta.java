package com.FoodMakerServices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table (name = "detallereceta")
@Data
public class DetalleReceta {
	private int idreceta;
	private int idingrediente;
}
