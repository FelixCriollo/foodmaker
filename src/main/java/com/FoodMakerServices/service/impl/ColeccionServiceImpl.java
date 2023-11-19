package com.FoodMakerServices.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.FoodMakerServices.entity.Coleccion;
import com.FoodMakerServices.entity.DetalleColeccion;
import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.entity.dto.coleccion.ActColeccionRQ;
import com.FoodMakerServices.entity.dto.coleccion.AgregarRecetaRQ;
import com.FoodMakerServices.entity.dto.coleccion.ColeccionCargadaDto;
import com.FoodMakerServices.repository.ColeccionRepository;
import com.FoodMakerServices.repository.DetalleColeccionRepository;
import com.FoodMakerServices.repository.RecetaRepository;
import com.FoodMakerServices.security.UserDetailsImplJwt;
import com.FoodMakerServices.service.ColeccionService;
import com.FoodMakerServices.service.RecetaService;

import jakarta.transaction.Transactional;

@Service
public class ColeccionServiceImpl implements ColeccionService {

	@Autowired
	ColeccionRepository repo;
	@Autowired
	RecetaService recetarepo;
	@Autowired
	DetalleColeccionRepository detarepo;
	
	@Override
	public List<ColeccionCargadaDto> getAll() {
		var algo = (List<Coleccion>) repo.findAll();
		List<ColeccionCargadaDto> rsp = new ArrayList<ColeccionCargadaDto>();
		algo.forEach(a -> {
			ColeccionCargadaDto c = new ColeccionCargadaDto();
			c.setId(a.getIdcoleccion());
			c.setNomnre(a.getNombre());
			c.setRecetas(recetarepo.getByColeccion(a.getIdcoleccion()));
			rsp.add(c);
		});
		return rsp;
	}

	@Override
	public List<ColeccionCargadaDto> getByUsuario(int idusuario) {
		
		var algo = repo.findByIdusuario(idusuario).orElse(new ArrayList<>());
		List<ColeccionCargadaDto> rsp = new ArrayList<ColeccionCargadaDto>();
		algo.forEach(a -> {
			ColeccionCargadaDto c = new ColeccionCargadaDto();
			c.setId(a.getIdcoleccion());
			c.setNomnre(a.getNombre());
			c.setRecetas(recetarepo.getByColeccion(a.getIdcoleccion()));
			rsp.add(c);
		});
		return rsp;
	}

	@Override
	public Coleccion add(String nombre) {
		Coleccion coleccion = new Coleccion();
		coleccion.setNombre(nombre);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getPrincipal() instanceof UserDetailsImplJwt) {
			UserDetailsImplJwt userDetails = (UserDetailsImplJwt) authentication.getPrincipal();
		    coleccion.setIdusuario(1);
		}
		coleccion.setIdusuario(1);
		repo.save(coleccion);
		
		return coleccion;
	}

	@Transactional
	@Override
	public void delete(int id) {
		
		
		detarepo.findByIdcoleccion(id).forEach(f -> {
			detarepo.delete(f);
		});
		repo.deleteById(String.valueOf(id));
		
	}

	@Override
	public Coleccion update(ActColeccionRQ act) {
		Coleccion collec = repo.findById(act.getId()).get();
		collec.setNombre(act.nombre);
		return repo.save(collec);
	}

	@Override
	public void addReceta(AgregarRecetaRQ addreceta) {
		DetalleColeccion d = new DetalleColeccion();
		d.setIdcoleccion(addreceta.idcoleccion);
		d.setIdreceta(addreceta.idreceta);
		detarepo.save(d);
	}

}
