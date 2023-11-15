package com.FoodMakerServices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Ingrediente;
import com.FoodMakerServices.repository.IngredienteRepository;
import com.FoodMakerServices.service.IngredienteService;
@Service
public class IngredienteServiceImpl implements IngredienteService {
	@Autowired
	IngredienteRepository repo;
	
	public List<Ingrediente> getAll(){
		return (List<Ingrediente>) repo.findAll();
	}
}
