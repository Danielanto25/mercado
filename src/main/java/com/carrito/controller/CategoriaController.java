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

import com.carrito.model.Categoria;
import com.carrito.service.ICategoriaService;

@RestController
@RequestMapping(path = "api/categoria")
public class CategoriaController {

	@Autowired
	private ICategoriaService service;

	@PostMapping(path = "insertar")
	public void insertar(@RequestBody Categoria categoria, HttpServletRequest request) {

		service.insertar(categoria, request);

	}

	@PutMapping(path = "eliminar")
	public void eliminar(@RequestBody Categoria categoria, HttpServletRequest request) {

		service.eliminar(categoria, request);

	}

	@PutMapping(path = "actualizar")
	public void actualizar(@RequestBody Categoria categoria, HttpServletRequest request) {

		service.actualizar(categoria, request);

	}

	@GetMapping(path = "listar")
	public List<Categoria> listar() {

		return service.listar();

	}
	@GetMapping(path = "listar-admin")
	public List<Categoria> listarAdmin(){
		
		return service.listarAdmin();
	}
}
