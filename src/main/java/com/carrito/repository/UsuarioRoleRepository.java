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
import com.carrito.model.Usuario;
import com.carrito.model.UsuarioRole;

@Repository
public class UsuarioRoleRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public List<UsuarioRole> listar() {

		String sql = "select * from usuario_role ur join role p on ur .rol_codigo= p.rol_codigo join usuario  u on ur.usu_codigo=u.usu_codigo ";

		List<UsuarioRole> lstUsuarioRole = namedJdbcTemplate.query(sql, new RowMapper<UsuarioRole>() {

			@Override
			public UsuarioRole mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario user = new Usuario();
				user.setUsuario(rs.getString("usu_usuario"));
				user.setCodigo(rs.getInt("usu_codigo"));

				Role role = new Role();
				role.setCodigo(rs.getInt("rol_codigo"));
				role.setNombre(rs.getString("rol_nombre"));

				Estado estado = new Estado();
				estado.setCodigo(rs.getInt("est_codigo"));
				estado.setNombre(rs.getString("est_nombre"));

				UsuarioRole usuarioRole = new UsuarioRole();
				usuarioRole.setCodigo(rs.getInt("usr_codigo"));
				usuarioRole.setEstado(estado);
				usuarioRole.setUsuario(user);
				usuarioRole.setRole(role);

				return usuarioRole;
			}

		});

		return lstUsuarioRole;
	}

	public void insertar(UsuarioRole usuarioRole) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("usuUsuario", usuarioRole.getUsuario().getCodigo());
		parameter.addValue("ip", usuarioRole.getIp());
		parameter.addValue("usuario", usuarioRole.getUsuarioUsuario());
		parameter.addValue("cliente", usuarioRole.getCliente());
		parameter.addValue("estado", usuarioRole.getEstado().getCodigo());
		parameter.addValue("role", usuarioRole.getRole().getCodigo());

		String sql = "insert into usuario_role(usu_codigo,usr_cliente,usr_usuario,usr_ip,est_codigo,rol_codigo)"
				+ "values(:usuUsuario,:cliente,:usuario,:ip,:estado,:role)";

		namedJdbcTemplate.update(sql, parameter);

	}

	public void actualizar(UsuarioRole usuarioRole) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("usuUsuario", usuarioRole.getUsuario().getCodigo());
		parameter.addValue("ip", usuarioRole.getIp());
		parameter.addValue("usuario", usuarioRole.getUsuarioUsuario());
		parameter.addValue("cliente", usuarioRole.getCliente());
		parameter.addValue("estado", usuarioRole.getEstado().getCodigo());
		parameter.addValue("role", usuarioRole.getRole().getCodigo());
		parameter.addValue("codigo", usuarioRole.getCodigo());

		String sql = "update usuario_role set usu_codigo=:usuUsuario,usr_cliente=:cliente,usr_usuario=:usuario,usr_ip=:ip"
				+ ",est_codigo=:estado,rol_codigo=:role where usr_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

	public void eliminar(UsuarioRole usuarioRole) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("ip", usuarioRole.getIp());
		parameter.addValue("usuario", usuarioRole.getUsuarioUsuario());
		parameter.addValue("cliente", usuarioRole.getCliente());
		parameter.addValue("estado", 11);
		parameter.addValue("codigo", usuarioRole.getCodigo());

		String sql = "update usuario_role set usr_cliente=:cliente,usr_usuario=:usuario,usr_ip=:ip"
				+ ",usr_estado=:estado where usr_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}
}
