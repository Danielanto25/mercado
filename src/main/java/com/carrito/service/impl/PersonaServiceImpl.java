package com.carrito.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrito.dto.InfoAuditoria;
import com.carrito.model.Persona;
import com.carrito.repository.PersonaRepository;
import com.carrito.service.IPersonaService;
import com.carrito.util.InformacionAuditoriaComponent;
import com.carrito.util.TokenHelperComponent;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private PersonaRepository repository;

	@Autowired
	private InformacionAuditoriaComponent informacionAuditoriaComponent;

	@Autowired
	private TokenHelperComponent tokenHelper;

	@Override
	public void insertar(Persona persona, HttpServletRequest request) {

		llenarDatosAuditoria(persona, request);

		repository.insertar(persona);

	}

	@Override
	public void actualizar(Persona persona, HttpServletRequest request) {

		llenarDatosAuditoria(persona, request);

		repository.insertar(persona);
	}

	@Override
	public void eliminar(Persona persona, HttpServletRequest request) {

		llenarDatosAuditoria(persona, request);

		repository.insertar(persona);

	}

	@Override
	public List<Persona> listar() {

		return repository.listar();
	}

	private void llenarDatosAuditoria(Persona persona, HttpServletRequest request) {

		InfoAuditoria infoAuditoria = informacionAuditoriaComponent.getInfoAuditoria(request);

		persona.setCliente(infoAuditoria.getCliente());
		persona.setIp(infoAuditoria.getIp());
		persona.setUsuario(infoAuditoria.getUsuario());

	}

	@Override
	public Persona listarPersonaPorUsuario(String token) {
		
		String usuario=tokenHelper.obtenerUsuarioDelToken(token);
		return repository.listarPersonaPorUsuario(usuario);
	}

}
