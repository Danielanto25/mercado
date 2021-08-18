package com.carrito.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.dto.InfoAuditoria;
import com.carrito.model.Factura;
import com.carrito.repository.FacturaRepository;
import com.carrito.repository.IFacturaRepository;
import com.carrito.service.IFacturaService;
import com.carrito.util.InformacionAuditoriaComponent;

@Service
public class FacturaServiceImpl implements IFacturaService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private IFacturaRepository repo;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Override
	public List<Factura> listar() {

		return repository.listar();
	}

	@Override
	public void actualizar(Factura factura, HttpServletRequest request) {

		llenarDatosAuditoria(factura, request);

		repository.actualizar(factura);

	}

	@Override
	public void insertar(Factura factura, HttpServletRequest request) {

		llenarDatosAuditoria(factura, request);

		repository.insertar(factura);
	}

	@Override
	public void generarFactura(Factura entity) {

		entity.getLstProductoFactura().forEach(item -> {

			// aca se mete el codigo

			item.setFactura(entity);
		});

		repo.save(entity);

	}

	private void llenarDatosAuditoria(Factura factura, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		factura.setCliente(infoAuditoria.getCliente());
		factura.setIp(infoAuditoria.getIp());
		factura.setUsuario(infoAuditoria.getUsuario());

	}

}
