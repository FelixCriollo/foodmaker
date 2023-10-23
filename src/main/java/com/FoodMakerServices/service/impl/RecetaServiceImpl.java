package com.FoodMakerServices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.repository.RecetaRepository;

@Service
public class RecetaServiceImpl {
	@Autowired
	RecetaRepository repo;
	
	public List<Receta> getAll() {
		return (List<Receta>) repo.findAll();
	}
}
