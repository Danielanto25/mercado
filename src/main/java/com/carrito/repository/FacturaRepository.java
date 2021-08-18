package com.carrito.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carrito.model.Factura;
import com.carrito.model.Persona;

@Repository
public class FacturaRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insertar(Factura factura) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("cliente", factura.getCliente());
		parameter.addValue("persona", factura.getPersona().getCodigo());
		parameter.addValue("total", factura.getTotal());
		parameter.addValue("ip", factura.getIp());
		parameter.addValue("cliente", factura.getCliente());
		parameter.addValue("usuario", factura.getUsuario());

		String sql = "insert into factura(per_codigo,fac_total,fac_ip,fac_usuario,fac_cliente)"
				+ "values(:persona,:total,:ip,:usuario,:cliente)";

		namedJdbcTemplate.update(sql, parameter);
	}

	public void actualizar(Factura factura) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("cliente", factura.getCliente());
		parameter.addValue("persona", factura.getPersona().getCodigo());
		parameter.addValue("total", factura.getTotal());
		parameter.addValue("ip", factura.getIp());
		parameter.addValue("cliente", factura.getCliente());
		parameter.addValue("usuario", factura.getUsuario());
		parameter.addValue("codigo", factura.getCodigo());

		String sql = "update factura set per_codigo=:persona,fac_total=:total,fac_ip=:ip,"
				+ "fac_usuario=:usuario,fac_cliente=:cliente where fac_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	public List<Factura> listar() {
		String sql = "select * from factura v join persona e on v.per_codigo=e.per_codigo";

		List<Factura> lstFactura = namedJdbcTemplate.query(sql, new RowMapper<Factura>() {

			@Override
			public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {

				Persona persona = new Persona();
				persona.setNombre(rs.getString("per_nombre"));
				persona.setCodigo(rs.getInt("per_codigo"));
				persona.setApellido(rs.getString("per_apellido"));

				Factura factura = new Factura();
				factura.setPersona(persona);
				factura.setTotal(rs.getInt("fac_total"));
				factura.setCreacion(rs.getObject("fac_fecha", LocalDate.class));
				factura.setCodigo(rs.getInt("fac_codigo"));

				return factura;
			}

		});

		return lstFactura;
	}

}
