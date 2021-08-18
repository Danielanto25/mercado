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
import com.carrito.model.Role;

@Repository
public class RoleRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insertar(Role role) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", role.getNombre());
		parameter.addValue("estado", role.getEstado().getCodigo());
		parameter.addValue("ip", role.getIp());
		parameter.addValue("usuario", role.getUsuario());
		parameter.addValue("cliente", role.getCliente());

		String sql = "insert into role(rol_nombre,est_codigo,rol_ip,rol_usuario,rol_cliente)"
				+ "values(:nombre,:estado,:ip,:usuario,:cliente)";

		namedJdbcTemplate.update(sql, parameter);

	}

	public void actualizar(Role role) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", role.getNombre());
		parameter.addValue("estado", role.getEstado().getCodigo());
		parameter.addValue("ip", role.getIp());
		parameter.addValue("usuario", role.getUsuario());
		parameter.addValue("cliente", role.getCliente());
		parameter.addValue("codigo", role.getCodigo());

		String sql = "update roleset rol_nombre=:nombre,est_codigo=:estado,rol_ip=:ip,rol_usuario=:usuario,rol_cliente=:cliente where rol_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	public void eliminar(Role role) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", role.getNombre());
		parameter.addValue("estado", 11);
		parameter.addValue("ip", role.getIp());
		parameter.addValue("usuario", role.getUsuario());
		parameter.addValue("cliente", role.getCliente());
		parameter.addValue("codigo", role.getCodigo());

		String sql = "update roleset rol_nombre=:nombre,est_codigo=:estado,rol_ip=:ip,rol_usuario=:usuario,rol_cliente=:cliente where rol_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	public List<Role> listar() {

		String sql = "select * from role v join estado e on v.est_codigo=e.est_codigo";

		List<Role> lstRole = namedJdbcTemplate.query(sql, new RowMapper<Role>() {

			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Role role = new Role();
				role.setNombre(rs.getString("vin_nombre"));
				role.setCodigo(rs.getInt("vin_codigo"));
				role.setEstado(estado);

				return role;
			}

		});

		return lstRole;
	}
}
