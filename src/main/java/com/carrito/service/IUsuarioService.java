package com.carrito.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.carrito.model.Usuario;

public interface IUsuarioService {

	void insertar(Usuario usuario, HttpServletRequest request);

	void actualizar(Usuario usuario, HttpServletRequest request);

	void eliminar(Usuario usuario, HttpServletRequest request);

	List<Usuario> listar();

	public List<String> buscarRolePorUsuario(String usuario);

}
