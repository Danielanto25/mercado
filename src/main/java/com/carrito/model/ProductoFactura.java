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
@Table(name = "producto_factura")
public class ProductoFactura {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prf_codigo")
	private Integer codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pro_codigo")
	private Producto producto;
	
	@Column(name = "prf_cantidad")
	private Integer cantidad;
	
	@Column(name = "prf_subtotal")
	private Integer Subtotal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fac_codigo")
	private Factura factura;
	
	@Column(name = "prf_ip")
	private String ip;
	
	@Column(name = "prf_cliente")
	private String cliente;
	
	@Column(name = "prf_usuario")
	private String usuario;
}
