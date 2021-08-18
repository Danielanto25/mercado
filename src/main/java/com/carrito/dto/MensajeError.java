package com.carrito.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MensajeError {

	private String timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
}
