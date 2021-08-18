package com.carrito.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrito.model.Estado;
import com.carrito.service.IEstadoService;

@RestController
@RequestMapping(path = "api/estado")
public class EstadoController {

	@Autowired
	private IEstadoService service;

	@PostMapping("insertar")
	public void insertar(@RequestBody Estado estado, HttpServletRequest request) {
		service.insertar(estado, request);
	}

	@GetMapping ("listar")
	public List<Estado> listar(){
		return service.listar();
	}

}
