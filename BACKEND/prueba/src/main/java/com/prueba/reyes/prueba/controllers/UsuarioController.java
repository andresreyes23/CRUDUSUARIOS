package com.prueba.reyes.prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.reyes.prueba.entity.ResponseUsuario;
import com.prueba.reyes.prueba.entity.Usuario;
import com.prueba.reyes.prueba.services.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("listar")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Usuario>listar(){
		return usuarioService.listar();
	}
	
	@PostMapping("agregar")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseUsuario listar(@RequestBody Usuario u){
		return usuarioService.add(u);
	}
	
	@GetMapping("buscar/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Usuario listarId(@PathVariable("id") int id) {
		return usuarioService.listarId(id);
	}
	
	@PutMapping("editar/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseUsuario editar(@RequestBody Usuario u,@PathVariable("id") int id) {
		u.setId(id);
		return usuarioService.edit(u);
	}
	
	@DeleteMapping("eliminar/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Usuario eliminar(@PathVariable("id") int id) {
		return usuarioService.delete(id);
	}

}
