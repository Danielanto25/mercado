package com.carrito.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.dto.InfoAuditoria;
import com.carrito.model.Estado;
import com.carrito.repository.EstadoRepository;
import com.carrito.service.IEstadoService;
import com.carrito.util.InformacionAuditoriaComponent;

@Service
public class EstadoServiceImpl implements IEstadoService {

	@Autowired
	private EstadoRepository repo;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	
	@Override
	public void insertar(Estado estado, HttpServletRequest request) {

		llenarDatosAuditoria(estado, request);
		
		repo.insertar(estado);
		
	}

	
	private void llenarDatosAuditoria(Estado estado, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		estado.setCliente(infoAuditoria.getCliente());
		estado.setIp(infoAuditoria.getIp());
		estado.setUsuario(infoAuditoria.getUsuario());

	}


	@Override
	public List<Estado> listar() {
		
		return repo.listar();
	}

}
