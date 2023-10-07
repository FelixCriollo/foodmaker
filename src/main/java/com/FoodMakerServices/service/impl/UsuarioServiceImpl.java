package com.FoodMakerServices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.repository.UsuarioRepository;
import com.FoodMakerServices.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository repo;
	
	public Usuario addUsuario(Usuario usuario) {
		return repo.save(usuario);
	}


}
