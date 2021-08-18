package com.carrito.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrito.model.Vinculo;
import com.carrito.service.IVinculoService;

@RestController
@RequestMapping(path = "api/vinculo")
public class VinculoController {

	@Autowired
	private IVinculoService service;

	@PostMapping(path = "insertar")
	public void insertar(@RequestBody Vinculo vinculo, HttpServletRequest request) {

		service.insertar(vinculo, request);
	}

	@GetMapping(path = "listar")
	public List<Vinculo> listar() {

		return service.listar();
	}
	
	@PutMapping(path = "actualizar")
	public void actualizar(@RequestBody Vinculo vinculo, HttpServletRequest request) {
		
		service.actualizar(vinculo, request);
		
	}
	
	@PutMapping(path = "eliminar")
	public void eliminar(@RequestBody Vinculo vinculo, HttpServletRequest request) {
		
		service.eliminar(vinculo, request);
	}
	
	@GetMapping(path = "listar-admin")
	public List<Vinculo> listarAdmin() {

		return service.listarAdmin();
	}
}
