package com.FoodMakerServices.entity.dto.receta;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AgregarRecetaDto {
	private String nombre;
	private String descripcion;
	private String descripcioncorta;
	private String instrucciones;
	private int tiempopreparacion;
	private int idcategoria;
	private String imagen;
}
