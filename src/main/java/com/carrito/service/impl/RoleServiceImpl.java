package com.carrito.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.dto.InfoAuditoria;
import com.carrito.model.Role;
import com.carrito.repository.RoleRepository;
import com.carrito.service.IRoleService;
import com.carrito.util.InformacionAuditoriaComponent;

@Service()
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository repository;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Override
	public void insertar(Role role, HttpServletRequest request) {

		llenarDatosAuditoria(role, request);

		repository.insertar(role);

	}

	@Override
	public void actualizar(Role role, HttpServletRequest request) {

		llenarDatosAuditoria(role, request);

		repository.actualizar(role);

	}

	@Override
	public void eliminar(Role role, HttpServletRequest request) {

		llenarDatosAuditoria(role, request);

		repository.eliminar(role);

	}

	@Override
	public List<Role> listar() {

		return repository.listar();
	}

	private void llenarDatosAuditoria(Role role, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		role.setCliente(infoAuditoria.getCliente());
		role.setIp(infoAuditoria.getIp());
		role.setUsuario(infoAuditoria.getUsuario());

	}
}
