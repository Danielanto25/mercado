package com.carrito.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.carrito.model.Producto;

public interface IProductoService {

	void insertar(String json, HttpServletRequest request, MultipartFile imagen) throws IOException;

	void actualizar(String json, HttpServletRequest request, MultipartFile imagen) throws IOException;

	void actualizarSinImagen(String json, HttpServletRequest request) throws IOException;

	void eliminar(Producto producto, HttpServletRequest request);

	List<Producto> listar();

	Producto listaProductoPorReferencia(String referencia);

	List<Producto> listarAdmin();

	byte[] obtenerImagen(Integer codigo) throws IOException;

	Producto listaProductoPorCodigo(Integer codigo);
	
	List<Producto> listaProductosPorCategoria(Integer codigo);
}
