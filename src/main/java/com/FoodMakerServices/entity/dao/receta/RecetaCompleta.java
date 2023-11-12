package com.FoodMakerServices.entity.dao.receta;

import java.util.List;

import com.FoodMakerServices.entity.Ingrediente;
import com.FoodMakerServices.entity.Receta;

import lombok.Data;

@Data
public class RecetaCompleta {
	Receta receta;
	List<Ingrediente> ingredientes;
}
