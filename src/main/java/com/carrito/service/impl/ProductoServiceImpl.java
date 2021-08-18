package com.carrito.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carrito.dto.InfoAuditoria;
import com.carrito.model.Producto;
import com.carrito.repository.ProductoRepository;
import com.carrito.service.IProductoService;
import com.carrito.util.InformacionAuditoriaComponent;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Value("${ruta-img}")
	private String rutaImagen;

	@Autowired
	private ProductoRepository repository;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Override
	public void insertar(String json, HttpServletRequest request, MultipartFile imagen) throws IOException {

		String extencion2 = FilenameUtils.getExtension(imagen.getOriginalFilename());

		int numero = (int) (Math.random() * 200000000 + 1);

		File picture = new File(rutaImagen + "/imagen" + numero + "." + extencion2);
		imagen.transferTo(picture);

		ObjectMapper objectMapper = new ObjectMapper();

		Producto producto = objectMapper.readValue(json, Producto.class);

		producto.setImagen(picture.getPath().replace('\\', '/'));

		llenarDatosAuditoria(producto, request);

		repository.insertar(producto);

	}

	@Override
	public void actualizarSinImagen(String json, HttpServletRequest request) throws IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Producto producto = objectMapper.readValue(json, Producto.class);
		
		llenarDatosAuditoria(producto, request);

		repository.actualizar(producto);
		

	}

	@Override
	public void actualizar(String json, HttpServletRequest request, MultipartFile imagen) throws IOException {

		String extencion2 = FilenameUtils.getExtension(imagen.getOriginalFilename());

		int numero = (int) (Math.random() * 200000000 + 1);

		File picture = new File(rutaImagen + "/imagen" + numero + "." + extencion2);
		imagen.transferTo(picture);

		ObjectMapper objectMapper = new ObjectMapper();

		Producto producto = objectMapper.readValue(json, Producto.class);

		producto.setImagen(picture.getPath().replace('\\', '/'));

		llenarDatosAuditoria(producto, request);

		repository.actualizar(producto);

	}

	@Override
	public void eliminar(Producto producto, HttpServletRequest request) {

		llenarDatosAuditoria(producto, request);

		repository.eliminar(producto);

	}

	@Override
	public List<Producto> listar() {

		return repository.listar();
	}

	@Override
	public List<Producto> listarAdmin() {

		return repository.listarAdmin();
	}
	
	@Override
	public byte[] obtenerImagen(Integer codigo) throws IOException {
		
		Producto producto=listaProductoPorCodigo(codigo);
		
		
		File imagen = new File(producto.getImagen());

		InputStream in = new FileInputStream(imagen);

		return IOUtils.toByteArray(in);
		
	}

	@Override
	public Producto listaProductoPorReferencia(String referencia) {

		return repository.listaProductoPorReferencia(referencia);
	}
	
	
	@Override
	public Producto listaProductoPorCodigo(Integer codigo) {
		
		return repository.listaProductoPorCodigo(codigo);
	}
	
	@Override
	public List<Producto> listaProductosPorCategoria(Integer codigo) {
		
		return repository.listaProductosPorCategoria(codigo);
	}
	

	private void llenarDatosAuditoria(Producto producto, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		producto.setCliente(infoAuditoria.getCliente());
		producto.setIp(infoAuditoria.getIp());
		producto.setUsuario(infoAuditoria.getUsuario());

	}

	

	

	

}
