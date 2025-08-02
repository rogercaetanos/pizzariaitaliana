package com.itb.mif3an.pizzariaitaliana.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedido {

    private Long id;
    private int quantidadeItem;
    private double valorUnitario;
    private boolean codStatus;

}
