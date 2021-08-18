package com.carrito.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.carrito.model.UsuarioRole;

public interface IUsuarioRoleSerice {

	List<UsuarioRole> listar();

	void insertar(UsuarioRole usuarioRole, HttpServletRequest request);

	void actualizar(UsuarioRole usuarioRole, HttpServletRequest request);

	void eliminar(UsuarioRole usuarioRole, HttpServletRequest request);

}
