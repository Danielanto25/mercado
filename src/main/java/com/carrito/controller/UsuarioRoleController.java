package com.carrito.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrito.model.UsuarioRole;
import com.carrito.service.IUsuarioRoleSerice;

@RestController
@RequestMapping(path = "api/usuario-role")
public class UsuarioRoleController {

	@Autowired
	private IUsuarioRoleSerice service;

	@GetMapping(path = "listar")
	public List<UsuarioRole> listar() {

		return service.listar();
	}

	@PostMapping(path = "insertar")
	public void insertar(UsuarioRole usuarioRole, HttpServletRequest request) {

		service.insertar(usuarioRole, request);
	}

	@PutMapping(path = "actualizar")
	public void actualizar(UsuarioRole usuarioRole, HttpServletRequest request) {

		service.actualizar(usuarioRole, request);
	}
	
	@PutMapping(path = "eliminar")
	public void eliminar(UsuarioRole usuarioRole, HttpServletRequest request) {

		service.eliminar(usuarioRole, request);
	}
}
