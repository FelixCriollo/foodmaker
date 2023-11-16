
package com.FoodMakerServices.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.service.impl.UsuarioServiceImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor																																																																																								
public class UserDetailsServiceImplJwt implements UserDetailsService {
	
	private UsuarioServiceImpl UsuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = UsuarioService.getByCorreo(username);
		return new UserDetailsImplJwt(user);
	}
}
