package com.FoodMakerServices.service;

import java.util.List;

import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dto.receta.AgregarRecetaDto;
import com.FoodMakerServices.entity.dto.receta.AvailableRQ;
import com.FoodMakerServices.entity.dto.receta.AvailableRS;
import com.FoodMakerServices.entity.dto.receta.RecetaCompleta;

public interface RecetaService {
	public Receta addReceta(RecetaCompleta receta);
	public Receta updateReceta(Receta receta);
	public boolean deleteReceta(Receta receta);
	public List<Receta> getAll();
	public List<Receta> filtrarPorTiempo(int tiempopreparacion);
	public List<Receta> filtrarPorCategoria(int idcategoria);
	public List<Receta> getByColeccion(int idcoleccion);
	public Receta BuscarReceta(int idReceta);
	public List<Receta> filtrarPorTiempoyCategoria(int tiempopreparacion,int idcategoria);
	
}
