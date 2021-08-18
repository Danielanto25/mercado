package com.carrito.model;

import java.time.LocalDate;

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
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "per_codigo")
	private Integer codigo;

	@Column(name = "per_nombre")
	private String nombre;

	@Column(name = "per_apellido")
	private String apellido;

	@Column(name = "per_nacimiento")
	private LocalDate nacimiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "est_codigo")
	private Estado estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vin_codigo")
	private Vinculo vinculo;

	@Column(name = "per_ip")
	private String ip;

	@Column(name = "per_usuario")
	private String usuario;

	@Column(name = "per_cliente")
	private String cliente;

	public Persona(Integer codigo) {

		this.codigo = codigo;
	}

}
