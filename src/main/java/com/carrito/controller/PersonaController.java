package com.carrito.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrito.model.Persona;
import com.carrito.service.IPersonaService;

@RestController
@RequestMapping(path = "api/persona")
public class PersonaController {

	@Autowired
	private IPersonaService service;

	@GetMapping(path = "listar")
	public List<Persona> listar() {

		return service.listar();
	}

	@PostMapping(path = "insertar")
	public void insertar(@RequestBody Persona persona, HttpServletRequest request) {

		service.insertar(persona, request);

	}

	@PutMapping(path = "actualizar")
	public void actualizar(@RequestBody Persona persona, HttpServletRequest request) {

		service.actualizar(persona, request);
	}

	@PutMapping(path = "eliminar")
	public void eliminar(@RequestBody Persona persona, HttpServletRequest request) {

		service.eliminar(persona, request);
	}

	@GetMapping(path = "listar-persona")
	public Persona listarPersonaPorUsuario(@RequestHeader("Authorization") String token) {

		return service.listarPersonaPorUsuario(token);

	}

}
