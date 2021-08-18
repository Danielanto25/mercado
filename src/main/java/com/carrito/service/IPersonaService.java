package com.carrito.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.carrito.model.Persona;

public interface IPersonaService {

	void insertar(Persona persona, HttpServletRequest request);

	void actualizar(Persona persona, HttpServletRequest request);

	void eliminar(Persona persona, HttpServletRequest request);

	List<Persona> listar();

	Persona listarPersonaPorUsuario(String token);
}
