package com.carrito.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.carrito.model.Role;

public interface IRoleService {
	
	void insertar(Role role, HttpServletRequest request);

	void actualizar(Role role, HttpServletRequest request);

	void eliminar(Role role, HttpServletRequest request);

	List<Role> listar();

}
