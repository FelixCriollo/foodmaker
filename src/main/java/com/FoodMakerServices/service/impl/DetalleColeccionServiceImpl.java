package com.FoodMakerServices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.DetalleColeccion;
import com.FoodMakerServices.repository.DetalleColeccionRepository;
import com.FoodMakerServices.service.DetalleColeccionService;

@Service
public class DetalleColeccionServiceImpl implements DetalleColeccionService {

	@Autowired
	DetalleColeccionRepository repo;
	
	@Override
	public List<DetalleColeccion> getAll() {
		return (List<DetalleColeccion>) repo.findAll();
	}

	@Override
	public List<DetalleColeccion> getbyIdcoleccion(int coleccion) {
		return repo.findByIdcoleccion(coleccion);
	}

	@Override
	public void deleteByIdcoleccion(int coleccion) {
		getbyIdcoleccion(coleccion).forEach(f -> {
			repo.delete(f);
		});
		
	}

}
