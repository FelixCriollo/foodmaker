package com.FoodMakerServices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.DetalleReceta;
import com.FoodMakerServices.repository.DetalleRecetaRepository;
import com.FoodMakerServices.service.DetalleRecetaService;
@Service
public class DetalleRecetaServiceImpl implements DetalleRecetaService {
	@Autowired
	DetalleRecetaRepository repo;
	
	public List<DetalleReceta> getAll() {
		return (List<DetalleReceta>) repo.findAll();
	}
}
