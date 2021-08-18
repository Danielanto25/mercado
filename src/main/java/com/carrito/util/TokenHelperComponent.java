package com.carrito.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;

import com.carrito.exception.JsonParserException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TokenHelperComponent {

	@SuppressWarnings("unchecked")
	public Map<String, Object> convertirAMap(String bearerToken) {

		Jwt jwt = JwtHelper.decode(bearerToken.replace("Bearer ", "").trim());

		String token = jwt.getClaims();

		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> dataToken = new HashMap<>();

		try {

			dataToken = objectMapper.readValue(token, Map.class);

		} catch (JsonProcessingException e) {

			e.printStackTrace();
			throw new JsonParserException(
					String.format("No se ha podido parsear el token a object -> %s", jwt.getClaims()));
		}

		return dataToken;
	}

	public String obtenerUsuarioDelToken(String token) {

		Map<String, Object> tokenDecoded = convertirAMap(token);

		return tokenDecoded.get("user_name").toString();
	}

	@SuppressWarnings("unchecked")
	public List<String> obtenerRoles(String token) {

		Map<String, Object> tokenDecoded = convertirAMap(token);

		return (List<String>) tokenDecoded.get("authorities");
	}
}
