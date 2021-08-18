package com.carrito.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrito.model.Factura;
import com.carrito.model.Persona;
import com.carrito.model.Producto;
import com.carrito.model.ProductoFactura;
import com.carrito.service.IFacturaService;

@RestController
@RequestMapping(path = "api/factura")
public class FacturaController {

	@Autowired
	private IFacturaService service;

	@PostMapping(path = "insertar")
	public void insertar(@RequestBody Factura factura, HttpServletRequest request) {

		service.insertar(factura, request);
	}

	@GetMapping(path = "listar")
	public List<Factura> listar() {

		return service.listar();
	}

	@PutMapping(path = "actualizar")
	public void actualizar(@RequestBody Factura factura, HttpServletRequest request) {

		service.actualizar(factura, request);

	}
	
	@GetMapping(path = "generar-factura")
	public void generarFactura() {
		
		
		Factura factura =new Factura();
		factura.setCliente("cliente");
		factura.setIp("ip");
		factura.setUsuario("usuario");
		factura.setCreacion(LocalDate.now());
		factura.setPersona(new Persona(1));
		factura.setTotal(2000);
		

		ProductoFactura productoFactura =new ProductoFactura();
		
		productoFactura.setCantidad(5);
		productoFactura.setCliente("cliente");
		productoFactura.setIp("ip");
		productoFactura.setProducto(new Producto(1));
		productoFactura.setFactura(factura);
		productoFactura.setSubtotal(2000);
		productoFactura.setUsuario("usuario");

		factura.setLstProductoFactura(Arrays.asList(productoFactura));
		
		service.generarFactura(factura);
		
	}

}
