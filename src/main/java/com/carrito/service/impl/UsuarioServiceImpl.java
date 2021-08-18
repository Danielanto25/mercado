package com.carrito.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.dto.InfoAuditoria;
import com.carrito.model.Usuario;
import com.carrito.repository.UsuarioRepository;
import com.carrito.service.IUsuarioService;
import com.carrito.util.InformacionAuditoriaComponent;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Autowired
	private UsuarioRepository repository;

	@Override
	public void insertar(Usuario usuario, HttpServletRequest request) {

		llenarDatosAuditoria(usuario, request);

		repository.insertar(usuario);

	}

	@Override
	public void actualizar(Usuario usuario, HttpServletRequest request) {

		llenarDatosAuditoria(usuario, request);

		repository.actualizar(usuario);

	}

	@Override
	public void eliminar(Usuario usuario, HttpServletRequest request) {

		llenarDatosAuditoria(usuario, request);

		repository.eliminar(usuario);

	}

	@Override
	public List<Usuario> listar() {

		return repository.listar();
	}

	@Override
	public List<String> buscarRolePorUsuario(String usuario) {

		return repository.buscarRolePorUsuario(usuario);
	}

	private void llenarDatosAuditoria(Usuario usuario, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		usuario.setCliente(infoAuditoria.getCliente());
		usuario.setIp(infoAuditoria.getIp());
		usuario.setUsuario(infoAuditoria.getUsuario());

	}

}
