package com.carrito.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {

	private Integer codigo;
	private String nombre;
	private String clave;
	private Estado estado;
	private Persona persona;
	private String ip;
	private String usuario;
	private String cliente;
}
