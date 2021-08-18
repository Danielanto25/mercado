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
import com.carrito.model.Persona;
import com.carrito.model.Usuario;

@Repository
public class UsuarioRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public void insertar(Usuario usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", usuario.getNombre());
		parameter.addValue("clave", usuario.getClave());
		parameter.addValue("persona", usuario.getPersona().getCodigo());
		parameter.addValue("estado", usuario.getEstado().getCodigo());
		parameter.addValue("usuario", usuario.getUsuario());
		parameter.addValue("ip", usuario.getIp());
		parameter.addValue("cliente", usuario.getCliente()); 

		String sql = "insert into usuario(usu_usuario,usu_clave,per_codigo,est_codigo,usu_ip,usu_cliente, usu_usu_usuario) "
				+ "values(:nombre,:clave,:persona,:estado,:ip,:cliente,:usuario)";

		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void actualizar(Usuario usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", usuario.getNombre());
		parameter.addValue("clave", usuario.getClave());
		parameter.addValue("persona", usuario.getPersona().getCodigo());
		parameter.addValue("estado", usuario.getEstado().getCodigo());
		parameter.addValue("usuario", usuario.getUsuario());
		parameter.addValue("ip", usuario.getIp());
		parameter.addValue("cliente", usuario.getCliente()); 
		parameter.addValue("codigo", usuario.getCodigo());

		String sql = "update usuario set (usu_usuario=:nombre,usu_clave=:clave,per_codigo=:persona,est_codigo=:estado"
				+ ",usu_ip=:ip,usu_cliente=:cliente, usu_usu_usuario=:usuario where usu_codigo=:codigo ";

		namedJdbcTemplate.update(sql, parameter);
	}
	public void eliminar(Usuario usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", usuario.getNombre());
		parameter.addValue("clave", usuario.getClave());
		parameter.addValue("persona", usuario.getPersona().getCodigo());
		parameter.addValue("estado", 11);
		parameter.addValue("usuario", usuario.getUsuario());
		parameter.addValue("ip", usuario.getIp());
		parameter.addValue("cliente", usuario.getCliente()); 
		parameter.addValue("codigo", usuario.getCodigo());

		String sql = "update usuario set (usu_usuario=:nombre,usu_clave=:clave,per_codigo=:persona,est_codigo=:estado"
				+ ",usu_ip=:ip,usu_cliente=:cliente, usu_usu_usuario=:usuario where usu_codigo=:codigo ";

		namedJdbcTemplate.update(sql, parameter);
	}
	
	public List<Usuario> listar() {

		String sql = "select * from usuario v join estado e on v.est_codigo=e.est_codigo join persona p on v.per_codigo=p.per_codigo";

		List<Usuario> lstCategoria = namedJdbcTemplate.query(sql, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));
				
				Persona persona =new Persona();
				persona.setCodigo(rs.getInt("per_codigo"));
				persona.setNombre(rs.getString("per_nombre"));
				persona.setApellido(rs.getString("per_apellido"));
				
				Usuario usuario = new Usuario();
				usuario.setNombre(rs.getString("usu_usuario"));
				usuario.setCodigo(rs.getInt("usu_codigo"));
				usuario.setClave(rs.getString("usu_clave"));
				usuario.setEstado(estado);
				usuario.setPersona(persona);


				return usuario;
			}

		});

		return lstCategoria;
	}
	
	public Usuario buscarUsuarioClaveEstadoPorUsuario(String usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);

		String sql = "select * from usuario u  join estado e on u.est_codigo=e.est_codigo where usu_usuario=:usuario ";

		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql, parameter, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado =new Estado();
				estado.setCodigo(rs.getInt("est_codigo"));
				
				Usuario user = new Usuario();
				user.setClave(rs.getString("usu_clave"));
				user.setEstado(estado);
				user.setUsuario(rs.getString("usu_usuario"));
				return user;
			}

		});

		if (lstUsuario.size() == 0) {
			throw new RuntimeException("el usuario no existe -> " + usuario);
		}

		return lstUsuario.get(0);
	}
	public List<String> buscarRolePorUsuario(String usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);

		String sql = "select rol_nombre from usuario u inner join usuario_role ur on u.usu_codigo  = ur.usu_codigo inner "
				+ "join role r on ur.rol_codigo  = r.rol_codigo  where u.usu_usuario  =:usuario";

		List<String> lstRoles = namedJdbcTemplate.query(sql, parameter, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				return rs.getString("rol_nombre");
			}

		});

		if (lstRoles.size() == 0) {
			throw new RuntimeException("Usuario sin permisos -> " + usuario);
		}

		return lstRoles;
	}
}
