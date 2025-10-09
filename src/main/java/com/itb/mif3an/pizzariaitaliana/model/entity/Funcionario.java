package com.itb.mif3an.pizzariaitaliana.model.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "FUNCIONARIO")
public class Funcionario extends Usuario {

    public Funcionario() {
        this.role = Role.FUNCIONARIO;
    }
}
