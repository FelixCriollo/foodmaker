package com.FoodMakerServices.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.repository.RecetaRepository;
import com.FoodMakerServices.service.RecetaService;
@Service
public class RecetaServiceImpl implements RecetaService {
	@Autowired
	RecetaRepository repo;
	
	public Receta addReceta(Receta receta) {
		return repo.save(receta);
	}
	
	@Override
	public List<Receta> getAll() {
		return (List<Receta>) repo.findAll();
	}

	@Override
	public List<Receta> filtrarPorTiempo(int tiempopreparacion) {
		return repo.findByTiempopreparacionLessThanEqual(tiempopreparacion);
	}

	@Override
	public List<Receta> filtrarPorCategoria(int idcategoria) {
		return repo.findByIdcategoria(idcategoria);
	}

	@Override
	public List<Receta> filtrarPorTiempoyCategoria(int tiempopreparacion, int idcategoria) {
		return repo.findByTiempopreparacionLessThanEqualAndIdcategoria(tiempopreparacion,idcategoria);
	}

	public Receta updateReceta(Receta receta) {		
		Optional<Receta> recetaExist = repo.findById(receta.getIdreceta());
		
		if(!recetaExist.isPresent()) {			
			throw new RuntimeException("La receta " + receta.getNombre() + " no existe");
		}
		
		return repo.save(receta);
	}
	
	public boolean deleteReceta(Receta receta) {
		try {
			repo.delete(receta);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
