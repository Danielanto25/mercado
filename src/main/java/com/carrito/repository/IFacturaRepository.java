package com.carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrito.model.Factura;

public interface IFacturaRepository extends JpaRepository<Factura, Integer>{

}
