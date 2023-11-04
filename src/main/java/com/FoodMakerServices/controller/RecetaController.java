package com.FoodMakerServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dao.receta.AvailableRQ;
import com.FoodMakerServices.entity.dao.receta.AvailableRS;
import com.FoodMakerServices.service.RecetaService;
import com.FoodMakerServices.service.UsuarioService;

@RestController
public class RecetaController {
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RecetaService recetaService;
	
	@GetMapping("/recetas")
	public List<Receta> allRecetas(){
		return recetaService.getAll();
	}
	
	@PostMapping("/añadirReceta")
	@ResponseBody
	public Receta addReceta(@RequestBody Receta receta) {
		return recetaService.addReceta(receta);
	}
	
	@PostMapping("/actualizarReceta")
	@ResponseBody
	public Receta updateReceta(@RequestBody Receta receta) {
		return recetaService.updateReceta(receta);
	}
	
	@PostMapping("/eliminarReceta")
	@ResponseBody
	public String deleteReceta(@RequestBody Receta receta) {
		boolean isDelete = recetaService.deleteReceta(receta);
		if(isDelete) {
			return "Se ha eliminado con éxtio la receta " + receta.getNombre();
		}else {
			return "No se ha podido eliminar la receta " + receta.getNombre();		
		}
	}
	
	@PostMapping("/receta/disponibilidad")
	public List<AvailableRS> allAval(@RequestBody AvailableRQ aval){
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		auth.getName();
		Usuario user = usuarioService.getByNombre(userDetail.getUsername());
		
		return recetaService.getAvailabilities(aval, user);
	}
}
