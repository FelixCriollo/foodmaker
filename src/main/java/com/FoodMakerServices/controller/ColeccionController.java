package com.FoodMakerServices.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dto.coleccion.ActColeccionRQ;
import com.FoodMakerServices.entity.dto.coleccion.AgregarRecetaRQ;
import com.FoodMakerServices.entity.dto.coleccion.ColeccionCargadaDto;
import com.FoodMakerServices.entity.dto.coleccion.CrearColeccionRQ;
import com.FoodMakerServices.security.UserDetailsImplJwt;
import com.FoodMakerServices.service.ColeccionService;
import com.FoodMakerServices.service.DetalleColeccionService;
import com.FoodMakerServices.service.UsuarioService;

@RestController
public class ColeccionController {
	
	@Autowired
	ColeccionService coleccionService;
	
	@Autowired
	DetalleColeccionService detalleColeccionService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/coleccions")
	public List<ColeccionCargadaDto> allCategorias(){
		return coleccionService.getAll();
	}
	
	@GetMapping("/getcoleccion")
	public List<ColeccionCargadaDto> allCollectionsByUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getPrincipal().toString().substring(5).substring(0, authentication.getPrincipal().toString().substring(5).indexOf(",")).trim();
		Usuario user = usuarioService.getByCorreo(email);
		var colecc = coleccionService.getByUsuario(user.getIdusuario());
		System.out.println(colecc);
		return colecc;
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
