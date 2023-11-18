package com.FoodMakerServices.entity.dto.receta;

import java.util.List;

import com.FoodMakerServices.entity.Ingrediente;
import com.FoodMakerServices.entity.Receta;

import lombok.Data;

@Data
public class RecetaCompleta {
	Receta receta;
	List<Ingrediente> ingredientes;
	int cantidadCoincidencias;
}
