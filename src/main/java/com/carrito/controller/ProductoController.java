package com.carrito.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carrito.model.Producto;
import com.carrito.service.IProductoService;

@RestController
@RequestMapping(path = "api/producto")
public class ProductoController {

	@Autowired
	private IProductoService service;

	@GetMapping(path = "listar")
	public List<Producto> listar() {

		return service.listar();
	}

	@PostMapping(path = "insertar")
	public void insertar(@RequestParam String json, @RequestPart MultipartFile imagen, HttpServletRequest request)
			throws IOException {

		service.insertar(json, request, imagen);
	}

	@PutMapping(path = "actualizar")
	public void actualizar(@RequestParam String json, @RequestPart MultipartFile imagen, HttpServletRequest request)
			throws IOException {

		service.actualizar(json, request, imagen);

	}

	@PutMapping(path = "eliminar")
	public void eliminar(@RequestBody Producto producto, HttpServletRequest request) {

		service.eliminar(producto, request);
	}

	@GetMapping(path = "listar-admin")
	public List<Producto> listarAdmin() {

		return service.listarAdmin();
	}

	@GetMapping(path = "listar-producto")
	public Producto listarProductoPorReferencia(String referencia) {

		return service.listaProductoPorReferencia(referencia);
	}

	@PutMapping(path = "actualizar-sin-imagen")
	public void actualizarSinImagen(String json, HttpServletRequest request) throws IOException {

		service.actualizarSinImagen(json, request);
	}

	@GetMapping(value = "/imagen/{codigo}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] obtenerImagen(@PathVariable Integer codigo) throws IOException {

		return service.obtenerImagen(codigo);
	}
	
	@GetMapping(path = "listar-por-categoria/{codigo}")
	public List<Producto> listarProductoPorCategoria(@PathVariable Integer codigo) {

		return service.listaProductosPorCategoria(codigo);
	}
	
}
