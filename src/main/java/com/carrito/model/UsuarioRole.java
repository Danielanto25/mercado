package com.carrito.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioRole {

	private Integer codigo;
	private Usuario usuario;
	private Role role;
	private Estado estado;
	private String ip;
	private String usuarioUsuario;
	private String cliente;
	
}
