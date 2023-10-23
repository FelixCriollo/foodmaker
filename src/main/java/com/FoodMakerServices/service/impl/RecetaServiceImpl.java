package com.FoodMakerServices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dao.receta.AvailableRQ;
import com.FoodMakerServices.entity.dao.receta.AvailableRS;
import com.FoodMakerServices.repository.RecetaRepository;
import com.FoodMakerServices.service.RecetaService;

@Service
public class RecetaServiceImpl implements RecetaService {
	@Autowired
	RecetaRepository repo;
	
	public List<Receta> getAll() {
		return (List<Receta>) repo.findAll();
	}

	@Override
	public List<AvailableRS> getAvailabilities(AvailableRQ aval, Usuario user) {
		// TODO Auto-generated method stub
		System.out.println(user.getCorreo());
		// obtener ingredients del cliente de un json
		
		// obtener recetas
		
		// filtrar
		
		// servir
		
		return null;
	}
}
