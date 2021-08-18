package com.carrito.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrito.model.Usuario;
import com.carrito.service.IUsuarioService;

@RestController
@RequestMapping(path = "api/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;

	@PostMapping(path = "insertar")
	public void insertar(@RequestBody Usuario usuario, HttpServletRequest request) {

		service.insertar(usuario, request);

	}

	@PutMapping(path = "actualizar")
	public void actualizar(@RequestBody Usuario usuario, HttpServletRequest request) {

		service.actualizar(usuario, request);

	}

	@PutMapping(path = "eliminar")
	public void eliminar(@RequestBody Usuario usuario, HttpServletRequest request) {

		service.eliminar(usuario, request);
	}
	
	@GetMapping(path = "listar-role/{usuario}")
	public List<String> listarRole(@PathVariable String usuario) {

		return service.buscarRolePorUsuario(usuario);

	}

}
