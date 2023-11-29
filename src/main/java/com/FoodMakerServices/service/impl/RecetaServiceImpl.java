package com.FoodMakerServices.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.entity.dto.receta.RecetaCompleta;
import com.FoodMakerServices.repository.RecetaRepository;
import com.FoodMakerServices.service.DetalleColeccionService;
import com.FoodMakerServices.service.RecetaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecetaServiceImpl implements RecetaService {
	RecetaRepository repo;
	DetalleColeccionService dcs;

	public Receta addReceta(RecetaCompleta recetaCompleta) {
		byte[] datosImagen = null;

		if(recetaCompleta.getReceta().getImagen() != null) {
			datosImagen = Base64.getDecoder().decode(recetaCompleta.getReceta().getImagen());
		}

		Receta receta = recetaCompleta.getReceta();
		receta.setImagen(datosImagen);

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

	@Override
	public Receta BuscarReceta(int idReceta) {
		Receta receta = repo.getById(idReceta);

		if(receta == null) {
			throw new RuntimeException("La receta " + receta.getNombre() + " no existe");
		}

		return receta;
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

	@Override
	public List<Receta> getByColeccion(int idcoleccion) {
		List<Receta> recetas = new ArrayList<Receta>();
		dcs.getbyIdcoleccion(idcoleccion).forEach(r -> {
			recetas.add(repo.findById(r.getIdreceta()).orElse(null));
		});
		return recetas;
	}
}