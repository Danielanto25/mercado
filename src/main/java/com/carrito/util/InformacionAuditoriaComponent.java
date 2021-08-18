package com.carrito.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.carrito.dto.InfoAuditoria;

@Component
public class InformacionAuditoriaComponent {

	@Autowired
	private TokenHelperComponent tokenConverter;
	
	@Value("${application.name}")
	private String cliente;
	
	public InfoAuditoria getInfoAuditoria(HttpServletRequest request) {
		
		String ipF5 = request.getRemoteAddr();
		String ipClient = request.getHeader("X-FORWARDED-FOR");
		String token = request.getHeader("Authorization");
		
		String usuario = tokenConverter.obtenerUsuarioDelToken(token);
		
		InfoAuditoria info = new InfoAuditoria();
		
		info.setIp(String.format("%s - %s", ipF5, ipClient));
		info.setUsuario(usuario);
		info.setCliente(cliente);
		
		return info;
	}
}
