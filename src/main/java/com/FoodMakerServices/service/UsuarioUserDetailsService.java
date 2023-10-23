package com.FoodMakerServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.component.UsuarioUserDetailsComponent;
import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.repository.UsuarioRepository;

@Service
public class UsuarioUserDetailsService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository repoUser;

	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Usuario usuario = repoUser.findByCorreo(correo);
		
		if (usuario == null)
			throw new UsernameNotFoundException("Cuenta " + correo + " no existe");
		
		return new UsuarioUserDetailsComponent(usuario);
	}
}
