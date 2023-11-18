package com.FoodMakerServices.service;

import com.FoodMakerServices.entity.Coleccion;

import java.util.List;

public interface ColeccionService {
    public List<Coleccion> encontrarTodosPorIdUsuario(int usuarioId);
}
