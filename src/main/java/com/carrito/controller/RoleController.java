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

import com.carrito.model.Role;
import com.carrito.service.IRoleService;

@RestController
@RequestMapping(path = "api/role")
public class RoleController {

	@Autowired
	private IRoleService service;

	@PostMapping(path = "insertar")
	public void insertar(@RequestBody Role role, HttpServletRequest request) {

		service.insertar(role, request);

	}

	@PutMapping(path = "eliminar")
	public void eliminar(@RequestBody Role role, HttpServletRequest request) {

		service.eliminar(role, request);

	}

	@PutMapping(path = "actualizar")
	public void actualizar(@RequestBody Role role, HttpServletRequest request) {

		service.actualizar(role, request);

	}

	@GetMapping(path = "listar")
	public List<Role> listar() {

		return service.listar();

	}

}
