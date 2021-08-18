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

import com.carrito.model.Estado;
import com.carrito.model.Persona;
import com.carrito.model.Vinculo;

@Repository
public class PersonaRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insertar(Persona persona) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", persona.getNombre());
		parameter.addValue("apellido", persona.getApellido());
		parameter.addValue("nacimiento", persona.getNacimiento());
		parameter.addValue("vinculo", persona.getVinculo().getCodigo());
		parameter.addValue("estado", persona.getEstado().getCodigo());
		parameter.addValue("ip", persona.getIp());
		parameter.addValue("usuario", persona.getUsuario());
		parameter.addValue("cliente", persona.getCliente());

		String sql = "insert into persona(per_nombre,per_apellido,per_nacimiento,est_codigo,vin_codigo,"
				+ "per_ip,per_usuario,per_cliente) values (:nombre,:apellido,:nacimiento,:estado,:vinculo,"
				+ ":ip,:usuario,:cliente";

		namedJdbcTemplate.update(sql, parameter);
	}

	public void actualizar(Persona persona) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", persona.getNombre());
		parameter.addValue("apellido", persona.getApellido());
		parameter.addValue("nacimiento", persona.getNacimiento());
		parameter.addValue("vinculo", persona.getVinculo().getCodigo());
		parameter.addValue("estado", persona.getEstado().getCodigo());
		parameter.addValue("ip", persona.getIp());
		parameter.addValue("usuario", persona.getUsuario());
		parameter.addValue("cliente", persona.getCliente());
		parameter.addValue("codigo", persona.getCodigo());

		String sql = "insert persona set per_nombre=:nombre,per_apellido=:apellido,per_nacimiento=:nacimiento,est_codigo=:estado,vin_codigo=:vinculo,"
				+ "per_ip=:ip,per_usuario=:usuario,per_cliente=:cliente where per_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	public void eliminar(Persona persona) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", persona.getNombre());
		parameter.addValue("apellido", persona.getApellido());
		parameter.addValue("nacimiento", persona.getNacimiento());
		parameter.addValue("vinculo", persona.getVinculo().getCodigo());
		parameter.addValue("estado", 11);
		parameter.addValue("ip", persona.getIp());
		parameter.addValue("usuario", persona.getUsuario());
		parameter.addValue("cliente", persona.getCliente());
		parameter.addValue("codigo", persona.getCodigo());

		String sql = "insert persona set per_nombre=:nombre,per_apellido=:apellido,per_nacimiento=:nacimiento,est_codigo=:estado,vin_codigo=:vinculo,"
				+ "per_ip=:ip,per_usuario=:usuario,per_cliente=:cliente where per_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	public List<Persona> listar() {

		String sql = "select * from persona v join estado e on v.est_codigo=e.est_codigo join vinculo vin on v.vin_codigo=vin.vin_codigo ";

		List<Persona> lstProducto = namedJdbcTemplate.query(sql, new RowMapper<Persona>() {

			@Override
			public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Vinculo vinculo=new Vinculo();
				vinculo.setCodigo(rs.getInt("vin_codigo"));
				vinculo.setNombre(rs.getString("vin_nombre"));

				Persona persona = new Persona();
				persona.setNombre(rs.getString("per_nombre"));
				persona.setCodigo(rs.getInt("per_codigo"));
				persona.setNacimiento(rs.getObject("per_nacimiento",LocalDate.class));
				persona.setApellido(rs.getString("per_apellido"));
				persona.setEstado(estado);
				persona.setVinculo(vinculo);


				return persona;
			}

		});

		return lstProducto;
	}
	
	public Persona listarPersonaPorUsuario(String usuario) {

		

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);
		
		String sql = "select * from usuario u full outer join persona p on u.per_codigo =p.per_codigo where usu_usuario=:usuario";

		List<Persona> lstProducto = namedJdbcTemplate.query(sql,parameter, new RowMapper<Persona>() {

			@Override
			public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {

				Persona persona = new Persona();
				persona.setNombre(rs.getString("per_nombre"));
				persona.setCodigo(rs.getInt("per_codigo"));
				persona.setApellido(rs.getString("per_apellido"));

				return persona;
			}

		});

		return lstProducto.get(0);
	}
}
