package com.carrito.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carrito.model.Estado;
import com.carrito.model.Vinculo;

@Repository
public class VinculoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insertar(Vinculo vinculo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", vinculo.getNombre());
		parameter.addValue("usuario", vinculo.getUsuario());
		parameter.addValue("ip", vinculo.getIp());
		parameter.addValue("cliente", vinculo.getCliente());
		parameter.addValue("estado", vinculo.getEstado().getCodigo());

		String sql = "insert into vinculo(vin_nombre,vin_usuario,vin_ip,vin_cliente,est_codigo) values(:nombre,:usuario,:ip,:cliente,:estado)";

		namedJdbcTemplate.update(sql, parameter);
	}

	public List<Vinculo> listar() {

		String sql = "select * from vinculo v join estado e on v.est_codigo=e.est_codigo where e.est_nombre='activo'";

		List<Vinculo> lstVinculo = namedJdbcTemplate.query(sql, new RowMapper<Vinculo>() {

			@Override
			public Vinculo mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Vinculo vinculo = new Vinculo();
				vinculo.setNombre(rs.getString("vin_nombre"));
				vinculo.setCodigo(rs.getInt("vin_codigo"));
				vinculo.setEstado(estado);

				return vinculo;
			}

		});

		return lstVinculo;
	}

	public List<Vinculo> listarAdmin() {

		String sql = "select * from vinculo v join estado e on v.est_codigo=e.est_codigo";

		List<Vinculo> lstVinculo = namedJdbcTemplate.query(sql, new RowMapper<Vinculo>() {

			@Override
			public Vinculo mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Vinculo vinculo = new Vinculo();
				vinculo.setNombre(rs.getString("vin_nombre"));
				vinculo.setCodigo(rs.getInt("vin_codigo"));
				vinculo.setEstado(estado);

				return vinculo;
			}

		});

		return lstVinculo;
	}

	public void actualizar(Vinculo vinculo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", vinculo.getNombre());
		parameter.addValue("usuario", vinculo.getUsuario());
		parameter.addValue("ip", vinculo.getIp());
		parameter.addValue("cliente", vinculo.getCliente());
		parameter.addValue("estado", vinculo.getEstado().getCodigo());
		parameter.addValue("codigo", vinculo.getCodigo());

		String sql = "update vinculo set vin_nombre=:nombre , vin_usuario=:usuario , vin_ip=:ip , vin_cliente=:cliente , est_codigo=:estado where vin_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	public void eliminar(Vinculo vinculo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", vinculo.getNombre());
		parameter.addValue("usuario", vinculo.getUsuario());
		parameter.addValue("ip", vinculo.getIp());
		parameter.addValue("cliente", vinculo.getCliente());
		parameter.addValue("estado", 11);
		parameter.addValue("codigo", vinculo.getCodigo());

		String sql = "update vinculo set vin_nombre=:nombre , vin_usuario=:usuario , vin_ip=:ip , vin_cliente=:cliente , est_codigo=:estado where vin_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

}
