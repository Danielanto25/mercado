package com.carrito.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.dto.InfoAuditoria;
import com.carrito.model.Categoria;
import com.carrito.repository.CategoriaRepository;
import com.carrito.service.ICategoriaService;
import com.carrito.util.InformacionAuditoriaComponent;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Override
	public void insertar(Categoria categoria, HttpServletRequest request) {

		llenarDatosAuditoria(categoria, request);

		repository.insertar(categoria);

	}

	@Override
	public void actualizar(Categoria categoria, HttpServletRequest request) {

		llenarDatosAuditoria(categoria, request);

		repository.actualizar(categoria);

	}

	@Override
	public List<Categoria> listar() {

		return repository.listar();
	}

	@Override
	public void eliminar(Categoria categoria, HttpServletRequest request) {

		llenarDatosAuditoria(categoria, request);

		repository.eliminar(categoria);

	}
	
	@Override
	public List<Categoria> listarAdmin() {
		
		return repository.listarAdmin();
	}


	private void llenarDatosAuditoria(Categoria categoria, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		categoria.setCliente(infoAuditoria.getCliente());
		categoria.setIp(infoAuditoria.getIp());
		categoria.setUsuario(infoAuditoria.getUsuario());

	}

	
}
