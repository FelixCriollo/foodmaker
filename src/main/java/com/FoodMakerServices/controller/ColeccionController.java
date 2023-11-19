package com.FoodMakerServices.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;


import com.FoodMakerServices.entity.Coleccion;
import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.entity.dto.coleccion.ActColeccionRQ;
import com.FoodMakerServices.entity.dto.coleccion.AgregarRecetaRQ;
import com.FoodMakerServices.entity.dto.coleccion.ColeccionCargadaDto;
import com.FoodMakerServices.entity.dto.coleccion.CrearColeccionRQ;
import com.FoodMakerServices.security.UserDetailsImplJwt;
import com.FoodMakerServices.service.ColeccionService;
import com.FoodMakerServices.service.DetalleColeccionService;

@RestController
public class ColeccionController {
	
	@Autowired
	ColeccionService coleccionService;
	
	@Autowired
	DetalleColeccionService detalleColeccionService;
	
	@GetMapping("/coleccions")
	public List<ColeccionCargadaDto> allCategorias(){
		return coleccionService.getAll();
	}
	
	@GetMapping("/getcoleccion")
	public List<ColeccionCargadaDto> allCollectionsByUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getPrincipal() instanceof UserDetailsImplJwt) {
			UserDetailsImplJwt userDetails = (UserDetailsImplJwt) authentication.getPrincipal();
		    
			return coleccionService.getByUsuario(userDetails.getIdUser());
		}
		
		return coleccionService.getByUsuario(1);
	}
	
	@PostMapping("/coleccion/crear")
	public Coleccion crearColeccion(@RequestBody CrearColeccionRQ crear) {
		return coleccionService.add(crear.getNombre());
	}
	
	@DeleteMapping("/coleccion/eliminar/{id}")
	public void eliminar(@PathVariable int id) {
		coleccionService.delete(id);
	}
	
	@PutMapping("/coleccion/actualizar")
	public Coleccion actualizar(@RequestBody ActColeccionRQ upd) {
		return coleccionService.update(upd);
	}
	
	@PostMapping("/coleccion/agregareceta")
	public void agregareceta(@RequestBody AgregarRecetaRQ add) {
		coleccionService.addReceta(add);
	} 
}
