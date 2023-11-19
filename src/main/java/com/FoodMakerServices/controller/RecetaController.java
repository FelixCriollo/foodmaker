package com.FoodMakerServices.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.FoodMakerServices.entity.DetalleReceta;
import com.FoodMakerServices.entity.Ingrediente;
import com.FoodMakerServices.entity.Receta;
import com.FoodMakerServices.entity.dto.receta.AgregarRecetaDto;
import com.FoodMakerServices.entity.dto.receta.AvailableRQ;
import com.FoodMakerServices.entity.dto.receta.AvailableRS;
import com.FoodMakerServices.entity.dto.receta.RecetaCompleta;
import com.FoodMakerServices.service.DetalleRecetaService;
import com.FoodMakerServices.service.IngredienteService;
import com.FoodMakerServices.service.RecetaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RecetaController {
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
	
	@PostMapping("/anadirReceta")
	@ResponseBody
	public Receta addReceta(@RequestBody AgregarRecetaDto receta) {
		return recetaService.addReceta(receta);
	}
	
	@PostMapping("/actualizarReceta")
	@ResponseBody
	public Receta updateReceta(@RequestBody Receta receta) {
		return recetaService.updateReceta(receta);
	}
	
	@PostMapping("/eliminarReceta/{idReceta}")
	@ResponseBody
	public String deleteReceta(@PathVariable int idReceta) {
		Receta receta = recetaService.BuscarReceta(idReceta);
		boolean isDelete = recetaService.deleteReceta(receta);
		if(isDelete) {
			return "Se ha eliminado con éxtio la receta " + receta.getNombre();
		}else {
			return "No se ha podido eliminar la receta " + receta.getNombre();		
		}
	}
	
	@PostMapping("/receta/disponibilidad")
	public List<AvailableRS> allAval(@RequestBody AvailableRQ aval){
		return GetRecetasDisponibles(aval.getTiempodecocina());
	}
	
	public List<AvailableRS> GetRecetasDisponibles(int minutos){
		try {	
			List<Ingrediente> ingredientes = ingredienteService.getAll();
			List<DetalleReceta> referencias = detalleRecetaService.getAll();
			List<RecetaCompleta> recetasWithIngrediente = new ArrayList();
			
			// Recetas por minutos de preparación
			List<Receta> recetas = recetaService.filtrarPorTiempo(minutos);
			
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
		        recetasWithIngrediente.add(recetaCompleta);
		    }
		    
		    List<AvailableRS> recetasDisponibles = ConstruirRecetasDisponibles(recetasWithIngrediente);
		    
			return recetasDisponibles;
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	// Helpers
	public static JsonNode leerJsonDesdeArchivo(String rutaArchivo) {
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
	
	 public static String obtenerRutaAbsoluta(String rutaRelativa) {
        //Obtiene la ruta absoluta del directorio actual
        Path rutaBase = Paths.get(System.getProperty("user.dir"));
        
        // Combina la ruta base con la ruta relativa
        Path rutaAbsoluta = rutaBase.resolve(Paths.get(rutaRelativa));
        
        // Convierte la ruta a formato de cadena y la retorna
        return rutaAbsoluta.toString();
    }

	
	public static JsonNode GetIngredientesRefrigerador() {
		String rutaRelativa = "/refrigerador.json";
		String rutaAbsoluta = obtenerRutaAbsoluta(rutaRelativa);
        JsonNode jsonNode = leerJsonDesdeArchivo(rutaAbsoluta);
        
        return jsonNode;
	}
	
	public static List<AvailableRS> ConstruirRecetasDisponibles(List<RecetaCompleta> recetas){
		JsonNode ingredienteRefrigerador = GetIngredientesRefrigerador();
		
		List<RecetaCompleta> recetasOrdenadas = new ArrayList();
		
		for (RecetaCompleta receta : recetas) {
            int coincidencias = contarCoincidencias(receta.getIngredientes(), ingredienteRefrigerador);
            receta.setCantidadCoincidencias(coincidencias);
            recetasOrdenadas.add(receta);
        }
		
		Collections.sort(recetasOrdenadas, Comparator.comparingInt(RecetaCompleta::getCantidadCoincidencias).reversed());
		
		List<AvailableRS> disponibilidad = ConstruirAvailableList(recetasOrdenadas);
		
		return disponibilidad;
	}
	
	public static int contarCoincidencias(List<Ingrediente> ingredientesReceta, JsonNode ingredienteRefrigerador) {
	    int coincidencias = 0;

	    for (Ingrediente ingredienteReceta : ingredientesReceta) {
	        String nombreReceta = ingredienteReceta.getNombre();

	        for (JsonNode ingredienteJson : ingredienteRefrigerador) {
	            String nombreJson = ingredienteJson.get("nombre").asText();

	            if (nombreReceta.equals(nombreJson)) {
	                coincidencias++;
	            }
	        }
	    }

	    return coincidencias;
	}
	
	public static List<AvailableRS> ConstruirAvailableList(List<RecetaCompleta> recetas){
		
		List<AvailableRS> disponibilidad = new ArrayList();
		
		for (RecetaCompleta receta : recetas) {
			AvailableRS dispo = new AvailableRS();
			
			dispo.setNombre(receta.getReceta().getNombre());
			dispo.setDuracion(receta.getReceta().getTiempopreparacion());
			dispo.setDescripcioncorta(receta.getReceta().getDescripcioncorta());
			dispo.setIngredientes(receta.getIngredientes());
			dispo.setImagen(receta.getReceta().getImagen());
			
			disponibilidad.add(dispo);
		}
		
		return disponibilidad;
	}
	
	@GetMapping("recetat/{tiempopreparacion}")
	public List<Receta> filtrarRecetaPorTiempo(@PathVariable int tiempopreparacion){
		return recetaService.filtrarPorTiempo(tiempopreparacion);
	}
	@GetMapping("recetac/{idcategoria}")
	public List<Receta> filtrarRecetaPorCategoria(@PathVariable int idcategoria){
		return  recetaService.filtrarPorCategoria(idcategoria);
	}
	@GetMapping("recetaf")
	public List<Receta> filtrarRecetaPorTiempoyCategoria(@RequestParam int tiempopreparacion,@RequestParam int idcategoria){
		return  recetaService.filtrarPorTiempoyCategoria(tiempopreparacion,idcategoria);
	}
}
