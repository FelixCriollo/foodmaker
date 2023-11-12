package com.FoodMakerServices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.FoodMakerServices.entity.Ingrediente;
import com.FoodMakerServices.repository.IngredienteRepository;
import com.FoodMakerServices.service.IngredienteService;

public class IngredienteServiceImpl implements IngredienteService {
	@Autowired
	IngredienteRepository repo;
	
	public List<Ingrediente> getAll(){
		return (List<Ingrediente>) repo.findAll();
	}
}
