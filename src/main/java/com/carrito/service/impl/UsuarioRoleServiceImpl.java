package com.carrito.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.dto.InfoAuditoria;
import com.carrito.model.UsuarioRole;
import com.carrito.repository.UsuarioRoleRepository;
import com.carrito.service.IUsuarioRoleSerice;
import com.carrito.util.InformacionAuditoriaComponent;

@Service
public class UsuarioRoleServiceImpl implements IUsuarioRoleSerice {

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Autowired
	private UsuarioRoleRepository repository;

	@Override
	public List<UsuarioRole> listar() {

		return repository.listar();
	}

	@Override
	public void insertar(UsuarioRole usuarioRole, HttpServletRequest request) {

		llenarDatosAuditoria(usuarioRole, request);

		repository.insertar(usuarioRole);

	}

	@Override
	public void actualizar(UsuarioRole usuarioRole, HttpServletRequest request) {

		llenarDatosAuditoria(usuarioRole, request);

		repository.actualizar(usuarioRole);

	}

	@Override
	public void eliminar(UsuarioRole usuarioRole, HttpServletRequest request) {

		llenarDatosAuditoria(usuarioRole, request);

		repository.eliminar(usuarioRole);
	}

	private void llenarDatosAuditoria(UsuarioRole usuarioRole, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		usuarioRole.setCliente(infoAuditoria.getCliente());
		usuarioRole.setIp(infoAuditoria.getIp());
		usuarioRole.setUsuarioUsuario(infoAuditoria.getUsuario());

	}

}
