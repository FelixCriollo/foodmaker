package com.FoodMakerServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dto.LoginDto;
import com.FoodMakerServices.service.UsuarioService;

import lombok.AllArgsConstructor;

import org.springframework.web.server.ResponseStatusException;

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
	public Usuario login(@RequestBody LoginDto login) {
		System.out.println("chau mundo");
		return null;
	}
	
}
