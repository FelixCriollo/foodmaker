package com.FoodMakerServices.service;

import java.util.List;

import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dao.receta.AvailableRQ;
import com.FoodMakerServices.entity.dao.receta.AvailableRS;

public interface RecetaService {
	public Receta addReceta(Receta receta);
	public Receta updateReceta(Receta receta);
	public boolean deleteReceta(Receta receta);
	public List<Receta> getAll();
	public List<Receta> filtrarPorTiempo(int tiempopreparacion);
	public List<Receta> filtrarPorCategoria(int idcategoria);
	public List<Receta> filtrarPorTiempoyCategoria(int tiempopreparacion,int idcategoria);
}
