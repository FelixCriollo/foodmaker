package com.FoodMakerServices.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table (name = "detallecoleccion")
@Data
public class DetalleColeccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddetallecoleccion;
    private int idreceta;
    private int idcoleccion;
    private int idusuario;
    
}
