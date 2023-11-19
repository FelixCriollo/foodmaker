package com.FoodMakerServices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Categoria;
import com.FoodMakerServices.repository.CategoriaRepository;
import com.FoodMakerServices.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaRepository repo;
	
	@Override
	public List<Categoria> getAll() {
		return (List<Categoria>) repo.findAll();
	}

}
