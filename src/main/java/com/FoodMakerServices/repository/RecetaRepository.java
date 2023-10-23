package com.FoodMakerServices.repository;

import org.springframework.data.repository.CrudRepository;

import com.FoodMakerServices.entity.Receta;

public interface RecetaRepository extends CrudRepository<Receta, String> {
	
}
