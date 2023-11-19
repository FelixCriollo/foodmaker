package com.FoodMakerServices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Coleccion;
import com.FoodMakerServices.repository.ColeccionRepository;
import com.FoodMakerServices.service.ColeccionService;

@Service
public class ColeccionServiceImpl implements ColeccionService {

	@Autowired
	ColeccionRepository repo;
	
	@Override
	public List<Coleccion> getAll() {
		return (List<Coleccion>) repo.findAll();
	}

}
