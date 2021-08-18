package com.carrito.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InfoAuditoria {

	private String ip;
	private String usuario;
	private String cliente;

}
