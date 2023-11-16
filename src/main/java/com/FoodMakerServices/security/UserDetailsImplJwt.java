package com.FoodMakerServices.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.FoodMakerServices.entity.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImplJwt implements UserDetails {

	private final Usuario user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getContrasenia();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getCorreo();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getName()
	{
		return user.getNombre();
	}

}
