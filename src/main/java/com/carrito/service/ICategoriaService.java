package com.carrito.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.carrito.model.Categoria;

public interface ICategoriaService {

	void insertar(Categoria categoria, HttpServletRequest request);

	void actualizar(Categoria categoria, HttpServletRequest request);

	List<Categoria> listar();

	void eliminar(Categoria categoria, HttpServletRequest request);

	List<Categoria> listarAdmin();
}
