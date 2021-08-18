package com.carrito.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.carrito.model.Vinculo;

public interface IVinculoService {

	void insertar(Vinculo vinculo, HttpServletRequest request);

	List<Vinculo> listar();

	void actualizar(Vinculo vinculo, HttpServletRequest request);

	void eliminar(Vinculo vinculo, HttpServletRequest request);

	List<Vinculo> listarAdmin();
}
