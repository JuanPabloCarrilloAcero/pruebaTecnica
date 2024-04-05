package com.pruebatecnica.repository;

import com.pruebatecnica.model.Empresa;
import com.pruebatecnica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

    public Producto[] findByEmpresaNit(Empresa empresa);

}
