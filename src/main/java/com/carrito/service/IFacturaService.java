package com.carrito.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.carrito.model.Factura;

public interface IFacturaService {

	List<Factura> listar();

	void actualizar(Factura factura, HttpServletRequest request);

	void insertar(Factura factura, HttpServletRequest request);

	void generarFactura(Factura entity);
}
