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

@Repository
public class EstadoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insertar(Estado estado) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", estado.getNombre());
		parameter.addValue("usuario", estado.getUsuario());
		parameter.addValue("ip", estado.getIp());
		parameter.addValue("cliente", estado.getCliente());

		String sql = "insert into estado(est_nombre,est_usuario,est_ip,est_cliente) values(:nombre,:usuario,:ip,:cliente) ";

		namedJdbcTemplate.update(sql, parameter);
	}

	public List<Estado> listar(){
		
		String sql="select * from estado";

		List<Estado> lstEstado = namedJdbcTemplate.query(sql,new RowMapper<Estado>() {


					@Override
					public Estado mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						Estado estado=new Estado();
						estado.setNombre(rs.getString("est_nombre"));
						estado.setCodigo(rs.getInt("est_codigo"));
						
						return estado;
					}

				});

		return lstEstado;
	
	}
	
	public void actualizar(Estado estado) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", estado.getNombre());
		parameter.addValue("usuario", estado.getUsuario());
		parameter.addValue("ip", estado.getIp());
		parameter.addValue("cliente", estado.getCliente());
		parameter.addValue("codigo", estado.getCodigo());

		String sql = "update estado set est_nombre:nombre,est_usuario=:usuario,est_ip=:ip,est_cliente=:cliente where est_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}
 
	
}
