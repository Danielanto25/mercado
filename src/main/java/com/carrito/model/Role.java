package com.carrito.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {

	private Integer codigo;
	private String nombre;
	private Estado estado;
	private String ip;
	private String usuario;
	private String cliente;

}
