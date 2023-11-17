package com.FoodMakerServices.controller;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.entity.dto.LoginDao;
import com.FoodMakerServices.service.UsuarioService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/usuarios")
	public List<Usuario> allUsuarios() {
		return usuarioService.getAll();
	}
	
	@PostMapping("/registrar")
	@ResponseBody
	public Usuario addUsuario(@RequestBody Usuario usuario) {
		return usuarioService.addUsuario(usuario);
	}
	
	@PostMapping(value = "/login")
	@ResponseBody
	public Usuario login(@RequestBody LoginDao login) {
		Usuario usuarioExistente = usuarioService.login(login);
		if(usuarioExistente != null){
			return usuarioExistente;
		}else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontró el usuario");
		}
	}
	@GetMapping("usuarios/{idusuario}")
	public Usuario obtenerUsuario(@PathVariable int idusuario){
		return usuarioService.obtenerUsuario(idusuario);
	}
	@PutMapping("usuarios/{idusuario}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int idusuario,@RequestBody Usuario usuario){
		usuario.setIdusuario(idusuario);
		Usuario usuarioActualizado = usuarioService.actualizarUsuario(usuario);
		if(usuarioActualizado !=null){
			return ResponseEntity.ok(usuarioActualizado);
		}else{
			return ResponseEntity.notFound().build();
		}

	}
	
}
