package com.carrito.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carrito.model.Categoria;
import com.carrito.model.Estado;

@Repository
public class CategoriaRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insertar(Categoria categoria) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", categoria.getNombre());
		parameter.addValue("usuario", categoria.getUsuario());
		parameter.addValue("ip", categoria.getIp());
		parameter.addValue("cliente", categoria.getCliente());
		parameter.addValue("estado", categoria.getEstado().getCodigo());

		String sql = "insert into categoria(cat_nombre,cat_usuario,cat_ip,cat_cliente,est_codigo) values(:nombre,:usuario,:ip,:cliente,:estado)";

		namedJdbcTemplate.update(sql, parameter);
	}

	public void actualizar(Categoria categoria) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", categoria.getNombre());
		parameter.addValue("usuario", categoria.getUsuario());
		parameter.addValue("ip", categoria.getIp());
		parameter.addValue("cliente", categoria.getCliente());
		parameter.addValue("estado", categoria.getEstado().getCodigo());
		parameter.addValue("codigo", categoria.getCodigo());

		String sql = "update categoria set cat_nombre=:nombre,cat_usuario=:usuario,cat_ip=:ip,"
				+ "cat_cliente=:cliente,est_codigo=:estado where cat_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	public List<Categoria> listar() {

		String sql = "select * from categoria v join estado e on v.est_codigo=e.est_codigo where e.est_nombre='activo'";

		List<Categoria> lstCategoria = namedJdbcTemplate.query(sql, new RowMapper<Categoria>() {

			@Override
			public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Categoria categoria = new Categoria();
				categoria.setNombre(rs.getString("cat_nombre"));
				categoria.setCodigo(rs.getInt("cat_codigo"));
				categoria.setEstado(estado);

				return categoria;
			}

		});

		return lstCategoria;
	}
	public List<Categoria> listarAdmin() {

		String sql = "select * from categoria v join estado e on v.est_codigo=e.est_codigo ";

		List<Categoria> lstCategoria = namedJdbcTemplate.query(sql, new RowMapper<Categoria>() {

			@Override
			public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Categoria categoria = new Categoria();
				categoria.setNombre(rs.getString("cat_nombre"));
				categoria.setCodigo(rs.getInt("cat_codigo"));
				categoria.setEstado(estado);

				return categoria;
			}

		});

		return lstCategoria;
	}
	
	public void eliminar(Categoria categoria) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", categoria.getNombre());
		parameter.addValue("usuario", categoria.getUsuario());
		parameter.addValue("ip", categoria.getIp());
		parameter.addValue("cliente", categoria.getCliente());
		parameter.addValue("estado", 11);
		parameter.addValue("codigo", categoria.getCodigo());

		String sql = "update categoria set est_codigo=:estado,cat_usuario=:usuario,cat_ip=:ip,"
				+ "cat_cliente=:cliente where cat_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

}
