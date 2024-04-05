package com.pruebatecnica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    private String nit;

    private String nombre;

    private String direccion;

    private String telefono;
}
