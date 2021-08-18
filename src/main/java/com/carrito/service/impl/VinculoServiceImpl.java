package com.carrito.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.dto.InfoAuditoria;
import com.carrito.model.Vinculo;
import com.carrito.repository.VinculoRepository;
import com.carrito.service.IVinculoService;
import com.carrito.util.InformacionAuditoriaComponent;

@Service
public class VinculoServiceImpl implements IVinculoService {

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Autowired
	private VinculoRepository repository;

	@Override
	public void insertar(Vinculo vinculo, HttpServletRequest request) {

		llenarDatosAuditoria(vinculo, request);

		repository.insertar(vinculo);

	}

	@Override
	public List<Vinculo> listar() {

		return repository.listar();
	}

	@Override
	public void actualizar(Vinculo vinculo, HttpServletRequest request) {

		llenarDatosAuditoria(vinculo, request);

		repository.actualizar(vinculo);

	}

	@Override
	public void eliminar(Vinculo vinculo, HttpServletRequest request) {

		llenarDatosAuditoria(vinculo, request);

		repository.eliminar(vinculo);

	}

	@Override
	public List<Vinculo> listarAdmin() {

		return repository.listarAdmin();
	}

	private void llenarDatosAuditoria(Vinculo vinculo, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		vinculo.setCliente(infoAuditoria.getCliente());
		vinculo.setIp(infoAuditoria.getIp());
		vinculo.setUsuario(infoAuditoria.getUsuario());

	}

}
