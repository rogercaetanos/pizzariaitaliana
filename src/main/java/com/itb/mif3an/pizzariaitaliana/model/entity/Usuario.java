package com.itb.mif3an.pizzariaitaliana.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String password;
    private String cpf;
    private String logradouro;
    private String cep;
    private String bairro;
    private String cidade;
    private String uf;
    private boolean codStatus;
}
