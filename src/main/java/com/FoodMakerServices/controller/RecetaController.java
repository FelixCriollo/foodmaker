package com.FoodMakerServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.service.RecetaService;

@RestController
public class RecetaController {
	
	@Autowired
	RecetaService recetaService;
	
	@GetMapping("/recetas")
	public List<Receta> allRecetas(){
		return recetaService.getAll();
	}
	
}
