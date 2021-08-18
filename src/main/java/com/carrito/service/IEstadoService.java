package com.carrito.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.carrito.model.Estado;

public interface IEstadoService {

	void insertar(Estado estado, HttpServletRequest request);

	List<Estado> listar();
}
