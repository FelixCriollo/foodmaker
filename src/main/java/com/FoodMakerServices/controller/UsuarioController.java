package com.FoodMakerServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dao.LoginDao;
import com.FoodMakerServices.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/usuarios")
	public List<Usuario> allUsuarios() {
		return usuarioService.getAll();
	}
	
	@PostMapping("/registrar")
	@ResponseBody
	public Usuario addUsuario(@RequestBody Usuario usuario) {
		return usuarioService.addUsuario(usuario);
	}
	
	@PostMapping(value = "/login")
	@ResponseBody
	public Usuario login(@RequestBody LoginDao login) {		
		return usuarioService.login(login);
	}
	
}
