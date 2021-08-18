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
@Table(name = "vinculo")
public class Vinculo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vin_codigo")
	private Integer codigo;
	
	@Column(name = "vin_nombre")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "est_codigo")
	private Estado estado;
	
	@Column(name = "vin_ip")
	private String ip;
	
	@Column(name = "vin_usuario")
	private String usuario;
	
	@Column(name = "vin_cliente")
	private String cliente;
	
}
