package com.FoodMakerServices.repository;

import org.springframework.data.repository.CrudRepository;

import com.FoodMakerServices.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	Usuario findByNombre(String usuario);
	Usuario findByNombreAndContrasenia(String nombre, String contrasenia);
	Usuario findByCorreo(String correo);
}
