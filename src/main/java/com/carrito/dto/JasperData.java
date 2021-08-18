package com.carrito.dto;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JasperData {
	private String pathJrxml;
	private Map<String, Object> dataSource;
	private HttpServletResponse response;

}
