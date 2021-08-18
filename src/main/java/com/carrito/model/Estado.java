package com.carrito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "estado")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "est_codigo")
	private Integer codigo;

	@Column(name = "est_nombre")
	private String nombre;

	@Column(name = "est_ip")
	private String ip;
	
	@Column(name = "est_usuario")
	private String usuario;
	
	@Column(name = "est_cliente")
	private String cliente;

}
