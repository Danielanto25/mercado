package com.carrito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_codigo")
	private Integer codigo;
	
	@Column(name = "pro_nombre")
	private String nombre;
	
	@Column(name = "pro_descripcion")
	private String descripcion;
	
	@Column(name = "pro_imagen")
	private String imagen;
	
	@Column(name = "pro_precio")
	private int precio;
	
	@Column(name = "pro_referencia")
	private String referencia;
	
	@Column(name = "pro_ip")
	private String ip;
	
	@Column(name = "pro_usuario")
	private String usuario;
	
	@Column(name = "pro_cliente")
	private String cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_codigo")
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "est_codigo")
	private Estado estado;
	
	public Producto(Integer codigo) {
		
		this.codigo=codigo;
	}
}
