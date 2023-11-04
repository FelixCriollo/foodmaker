package com.FoodMakerServices.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dao.LoginDao;
import com.FoodMakerServices.repository.UsuarioRepository;
import com.FoodMakerServices.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	public Usuario addUsuario(Usuario usuario) {
		
		usuario.setContrasenia(encoder.encode(usuario.getContrasenia()));
		
		return repo.save(usuario);
	}

	@Override
	public List<Usuario> getAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) repo.findAll();
	}

	@Override
	public Usuario login(LoginDao login) {
		Usuario user = null;
		
		for (Usuario u : repo.findAll()) {
			if (u.getCorreo().equals(login.getCorreo()) && encoder.matches(login.getContrasenia(), u.getContrasenia())) {
				user = u;
			}
		}		
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public Usuario getByCorreo(String correo) {
		Usuario user = null;
		
		user = repo.findByCorreo(correo);
		
		return user;
	}

	@Override
	public Usuario getByNombre(String nombre) {
		Usuario user = null;
		
		user = repo.findByNombre(nombre);
		
		return user;
	}


}
