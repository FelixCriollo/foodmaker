package com.FoodMakerServices.repository;

import com.FoodMakerServices.entity.Coleccion;
import com.FoodMakerServices.entity.ColeccionDetalle;
import com.FoodMakerServices.service.ColeccionService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColeccionDetalleRepository extends CrudRepository<ColeccionDetalle, Integer> {
    @Query("SELECT collectionDetalle FROM ColeccionDetalle AS collectionDetalle WHERE collectionDetalle.idColeccion = :idColeccion")
    public List<ColeccionService> encontrarTodosPorIdUsuario(@Param("idColeccion") int idColeccion);
}
