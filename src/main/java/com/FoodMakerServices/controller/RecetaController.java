package com.FoodMakerServices.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.FoodMakerServices.entity.DetalleReceta;
import com.FoodMakerServices.entity.Ingrediente;
import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dao.receta.AvailableRQ;
import com.FoodMakerServices.entity.dao.receta.AvailableRS;
import com.FoodMakerServices.entity.dao.receta.RecetaCompleta;
import com.FoodMakerServices.service.DetalleRecetaService;
import com.FoodMakerServices.service.IngredienteService;
import com.FoodMakerServices.service.RecetaService;
import com.FoodMakerServices.service.UsuarioService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RecetaController {
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RecetaService recetaService;
	@Autowired
	IngredienteService ingredienteService;
	@Autowired
	DetalleRecetaService detalleRecetaService;
	
	@GetMapping("/recetas")
	public List<Receta> allRecetas(){
		return recetaService.getAll();
	}
	
	@PostMapping("/añadirReceta")
	@ResponseBody
	public Receta addReceta(@RequestBody Receta receta) {
		return recetaService.addReceta(receta);
	}
	
	@PostMapping("/actualizarReceta")
	@ResponseBody
	public Receta updateReceta(@RequestBody Receta receta) {
		return recetaService.updateReceta(receta);
	}
	
	@PostMapping("/eliminarReceta")
	@ResponseBody
	public String deleteReceta(@RequestBody Receta receta) {
		boolean isDelete = recetaService.deleteReceta(receta);
		if(isDelete) {
			return "Se ha eliminado con éxtio la receta " + receta.getNombre();
		}else {
			return "No se ha podido eliminar la receta " + receta.getNombre();		
		}
	}
	
	@PostMapping("/receta/disponibilidad")
	public List<AvailableRS> allAval(@RequestBody AvailableRQ aval){
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		auth.getName();
		Usuario user = usuarioService.getByNombre(userDetail.getUsername());
		
		return null;
	}
	
	public List<AvailableRS> GetRecetasDisponibles(int minutos){
		List<AvailableRS> recetasDisponibles = new ArrayList();
		
		try {
			
			JsonNode ingredienteRefrigerador = GetIngredientesRefrigerador();		
			List<Ingrediente> ingredientes = ingredienteService.getAll();
			List<DetalleReceta> referencias = detalleRecetaService.getAll();
			
			// Recetas por minutos de preparación
			List<Receta> recetas = recetaService.getAll().stream()
				    .filter(receta -> receta.getTiempopreparacion() < minutos)
				    .collect(Collectors.toList());
			
			// Filtraremos las recetas por ingredientes
		    for (Receta receta : recetas) {
		        RecetaCompleta recetaCompleta = new RecetaCompleta();
		        recetaCompleta.setReceta(receta);
		        
		        List<DetalleReceta> refByReceta = referencias.stream()
		        		.filter(d -> d.getIdreceta() == receta.getIdreceta())
		        		.collect(Collectors.toList());
		        
		        List<Ingrediente> ingredientesByRef = new ArrayList();
		        
		        for (DetalleReceta dr : refByReceta) {
		        	Ingrediente ingrediente = ingredientes.get(dr.getIdingrediente());
		        	ingredientesByRef.add(ingrediente);
		        }
		        
		        recetaCompleta.setIngredientes(ingredientesByRef);
		    }
			
			return recetasDisponibles;
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	// Helpers
	private static JsonNode leerJsonDesdeArchivo(String rutaArchivo) {
        try {
            // Crea un objeto ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Lee el archivo JSON y devuelve un JsonNode
            return objectMapper.readTree(new File(rutaArchivo));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	private static String obtenerRutaAbsoluta(String rutaRelativa) {
        // Obtiene el directorio actual como ruta base
        Path directorioActual = Paths.get(System.getProperty("user.dir"));

        // Resuelve la ruta relativa contra el directorio actual para obtener la ruta absoluta
        Path rutaAbsoluta = directorioActual.resolve(rutaRelativa);

        return rutaAbsoluta.toString();
    }
	
	public JsonNode GetIngredientesRefrigerador() {
		String rutaRelativa = "/foodmaker/src/main/json/refrigerador.json";
		String rutaAbsoluta = obtenerRutaAbsoluta(rutaRelativa);
        JsonNode jsonNode = leerJsonDesdeArchivo(rutaAbsoluta);
        
        return jsonNode;
	}
}
