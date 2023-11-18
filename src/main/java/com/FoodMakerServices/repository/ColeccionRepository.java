package com.FoodMakerServices.repository;

import com.FoodMakerServices.entity.Coleccion;
import com.FoodMakerServices.service.ColeccionService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColeccionRepository extends CrudRepository<Coleccion, Integer> {
    @Query("SELECT collection FROM Coleccion AS collection WHERE collection.idUsuario = :idUsuario")
    public List<Coleccion> encontrarTodosPorIdUsuario(@Param("idUsuario") int idUsuario);
}
