package com.pruebatecnica.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    private String codigo;

    private String nombre;

    @ManyToMany
    private Set<Caracteristica> caracteristicas;

    private String moneda;

    private String precio;

    @ManyToOne
    private Empresa empresaNit;

}
