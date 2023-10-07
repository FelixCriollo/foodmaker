package com.FoodMakerServices.repository;

import org.springframework.data.repository.CrudRepository;

import com.FoodMakerServices.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

}
