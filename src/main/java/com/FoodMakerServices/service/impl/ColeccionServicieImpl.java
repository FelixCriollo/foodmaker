package com.FoodMakerServices.service.impl;

import com.FoodMakerServices.entity.Coleccion;
import com.FoodMakerServices.repository.ColeccionRepository;
import com.FoodMakerServices.service.ColeccionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColeccionServicieImpl implements ColeccionService {

    ColeccionRepository coleccionRepository;

    @Override
    public List<Coleccion> encontrarTodosPorIdUsuario(int usuarioId) {
        return coleccionRepository.encontrarTodosPorIdUsuario(usuarioId);
    }


}
