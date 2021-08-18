package com.carrito.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "factura")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fac_codigo")
	private Integer codigo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "per_codigo")
	private Persona persona;

	@Column(name = "fac_total")
	private Integer total;

	@Column(name = "fac_ip")
	private String ip;

	@Column(name = "fac_cliente")
	private String cliente;

	@Column(name = "fac_usuario")
	private String usuario;

	@Column(name = "fac_fecha")
	private LocalDate creacion;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "factura")
	private List<ProductoFactura> lstProductoFactura;

}
