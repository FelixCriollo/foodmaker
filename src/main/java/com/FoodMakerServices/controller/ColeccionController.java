package com.FoodMakerServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FoodMakerServices.entity.Coleccion;
import com.FoodMakerServices.service.ColeccionService;
import com.FoodMakerServices.service.DetalleColeccionService;

@RestController
public class ColeccionController {
	
	@Autowired
	ColeccionService coleccionService;
	
	@Autowired
	DetalleColeccionService detalleColeccionService;
	
	@GetMapping("/coleccion")
	public List<Coleccion> allCategorias(){
		return coleccionService.getAll();
	}
}
