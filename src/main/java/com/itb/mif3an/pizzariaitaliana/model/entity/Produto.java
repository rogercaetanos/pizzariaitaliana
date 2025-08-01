package com.itb.mif3an.pizzariaitaliana.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    private Long id;
    private String nome;
    private String descricao;
    private double valorVenda;
    private double valorCompra;
    private String tipo;
    private int quantidadeEstoque;
    private boolean codStatus;

}
