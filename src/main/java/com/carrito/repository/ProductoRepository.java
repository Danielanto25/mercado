package com.carrito.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.carrito.model.Categoria;
import com.carrito.model.Estado;
import com.carrito.model.Producto;

@Repository
public class ProductoRepository {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insertar(Producto producto) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", producto.getNombre());
		parameter.addValue("usuario", producto.getUsuario());
		parameter.addValue("ip", producto.getIp());
		parameter.addValue("cliente", producto.getCliente());
		parameter.addValue("estado", producto.getEstado().getCodigo());
		parameter.addValue("descripcion", producto.getDescripcion());
		parameter.addValue("imagen", producto.getImagen());
		parameter.addValue("precio", producto.getPrecio());
		parameter.addValue("referencia", encoder.encode(producto.getReferencia()));
		parameter.addValue("categoria", producto.getCategoria().getCodigo());

		String sql = "insert into producto(pro_nombre,pro_descripcion,pro_imagen,pro_precio,"
				+ "pro_referencia,pro_ip,pro_usuario,pro_cliente,est_codigo,cat_codigo) values"
				+ " (:nombre,:descripcion,:imagen,:precio,:referencia,:ip,:usuario,:cliente,:estado,:categoria)";

		namedJdbcTemplate.update(sql, parameter);

	}

	public void actualizar(Producto producto) {

		String sql = null;

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", producto.getNombre());
		parameter.addValue("usuario", producto.getUsuario());
		parameter.addValue("ip", producto.getIp());
		parameter.addValue("cliente", producto.getCliente());
		parameter.addValue("estado", producto.getEstado().getCodigo());
		parameter.addValue("descripcion", producto.getDescripcion());
		parameter.addValue("precio", producto.getPrecio());
		parameter.addValue("referencia", encoder.encode(producto.getReferencia()));
		parameter.addValue("categoria", producto.getCategoria().getCodigo());
		parameter.addValue("codigo", producto.getCodigo());

		if (producto.getImagen() != null) {

			parameter.addValue("imagen", producto.getImagen());
			
			sql = "update producto set pro_nombre=:nombre,pro_descripcion=:descripcion,pro_imagen=:imagen,"
					+ "pro_precio=:precio,pro_referencia=:referencia,pro_ip=:ip,pro_usuario=:usuario,pro_cliente=:cliente,"
					+ "est_codigo=:estado,cat_codigo=:categoria where pro_codigo=:codigo";
			
		}else {
			
			sql = "update producto set pro_nombre=:nombre,pro_descripcion=:descripcion,"
					+ "pro_precio=:precio,pro_referencia=:referencia,pro_ip=:ip,pro_usuario=:usuario,pro_cliente=:cliente,"
					+ "est_codigo=:estado,cat_codigo=:categoria where pro_codigo=:codigo";
			
		}

		namedJdbcTemplate.update(sql, parameter);

	}

	public void eliminar(Producto producto) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", producto.getNombre());
		parameter.addValue("usuario", producto.getUsuario());
		parameter.addValue("ip", producto.getIp());
		parameter.addValue("cliente", producto.getCliente());
		parameter.addValue("estado", 11);
		parameter.addValue("descripcion", producto.getDescripcion());
		parameter.addValue("precio", producto.getPrecio());
		parameter.addValue("referencia", encoder.encode(producto.getReferencia()));
		parameter.addValue("categoria", producto.getCategoria().getCodigo());
		parameter.addValue("codigo", producto.getCodigo());

		String sql = "update producto set pro_nombre=:nombre,pro_descripcion=:descripcion,"
				+ "pro_precio=:precio,pro_referencia=:referencia,pro_ip=:ip,pro_usuario=:usuario,pro_cliente=:cliente,"
				+ "est_codigo=:estado,cat_codigo=:categoria where pro_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

	public List<Producto> listar() {

		String sql = "select * from producto v join estado e on v.est_codigo=e.est_codigo join categoria c on v.cat_codigo=c.cat_codigo  where est_nombre='activo'";

		List<Producto> lstProducto = namedJdbcTemplate.query(sql, new RowMapper<Producto>() {

			@Override
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("cat_codigo"));
				categoria.setNombre("cat_nombre");

				Producto producto = new Producto();
				producto.setNombre(rs.getString("pro_nombre"));
				producto.setCodigo(rs.getInt("pro_codigo"));
				producto.setEstado(estado);
				producto.setCategoria(categoria);
				producto.setReferencia(rs.getString("pro_referencia"));
				producto.setDescripcion(rs.getString("pro_descripcion"));
				producto.setImagen(rs.getString("pro_imagen"));
				producto.setPrecio(rs.getInt("pro_precio"));

				return producto;
			}

		});

		return lstProducto;
	}

	public List<Producto> listarAdmin() {

		String sql = "select * from producto v join estado e on v.est_codigo=e.est_codigo join categoria c on v.cat_codigo=c.cat_codigo ";

		List<Producto> lstProducto = namedJdbcTemplate.query(sql, new RowMapper<Producto>() {

			@Override
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("cat_codigo"));
				categoria.setNombre(rs.getString("cat_nombre"));

				Producto producto = new Producto();
				producto.setNombre(rs.getString("pro_nombre"));
				producto.setCodigo(rs.getInt("pro_codigo"));
				producto.setEstado(estado);
				producto.setCategoria(categoria);
				producto.setReferencia(rs.getString("pro_referencia"));
				producto.setDescripcion(rs.getString("pro_descripcion"));
				producto.setImagen(rs.getString("pro_imagen"));
				producto.setPrecio(rs.getInt("pro_precio"));

				return producto;
			}

		});

		return lstProducto;
	}

	public Producto listaProductoPorReferencia(String referencia) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("referencia", referencia);

		String sql = "select * from producto v join estado e on v.est_codigo=e.est_codigo join categoria c on v.cat_codigo=c.cat_codigo where v.pro_referencia=:referencia ";

		List<Producto> lstProducto = namedJdbcTemplate.query(sql, parameter, new RowMapper<Producto>() {

			@Override
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("cat_codigo"));
				categoria.setNombre(rs.getString("cat_nombre"));

				Producto producto = new Producto();
				producto.setNombre(rs.getString("pro_nombre"));
				producto.setCodigo(rs.getInt("pro_codigo"));
				producto.setEstado(estado);
				producto.setCategoria(categoria);
				producto.setReferencia(rs.getString("pro_referencia"));
				producto.setDescripcion(rs.getString("pro_descripcion"));
				producto.setImagen(rs.getString("pro_imagen"));
				producto.setPrecio(rs.getInt("pro_precio"));

				return producto;
			}

		});

		return lstProducto.get(0);
	}
	public Producto listaProductoPorCodigo(Integer codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "select * from producto v join estado e on v.est_codigo=e.est_codigo join categoria c on v.cat_codigo=c.cat_codigo where v.pro_codigo=:codigo ";

		List<Producto> lstProducto = namedJdbcTemplate.query(sql, parameter, new RowMapper<Producto>() {

			@Override
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("cat_codigo"));
				categoria.setNombre(rs.getString("cat_nombre"));

				Producto producto = new Producto();
				producto.setNombre(rs.getString("pro_nombre"));
				producto.setCodigo(rs.getInt("pro_codigo"));
				producto.setEstado(estado);
				producto.setCategoria(categoria);
				producto.setReferencia(rs.getString("pro_referencia"));
				producto.setDescripcion(rs.getString("pro_descripcion"));
				producto.setImagen(rs.getString("pro_imagen"));
				producto.setPrecio(rs.getInt("pro_precio"));

				return producto;
			}

		});

		return lstProducto.get(0);
	}
	
	public List<Producto> listaProductosPorCategoria(Integer codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "select * from producto v join estado e on v.est_codigo=e.est_codigo join categoria c on v.cat_codigo=c.cat_codigo where v.cat_codigo=:codigo ";

		List<Producto> lstProducto = namedJdbcTemplate.query(sql, parameter, new RowMapper<Producto>() {

			@Override
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {

				Estado estado = new Estado();
				estado.setNombre(rs.getString("est_nombre"));
				estado.setCodigo(rs.getInt("est_codigo"));

				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("cat_codigo"));
				categoria.setNombre(rs.getString("cat_nombre"));

				Producto producto = new Producto();
				producto.setNombre(rs.getString("pro_nombre"));
				producto.setCodigo(rs.getInt("pro_codigo"));
				producto.setEstado(estado);
				producto.setCategoria(categoria);
				producto.setReferencia(rs.getString("pro_referencia"));
				producto.setDescripcion(rs.getString("pro_descripcion"));
				producto.setImagen(rs.getString("pro_imagen"));
				producto.setPrecio(rs.getInt("pro_precio"));

				return producto;
			}

		});

		return lstProducto;
	}

}
