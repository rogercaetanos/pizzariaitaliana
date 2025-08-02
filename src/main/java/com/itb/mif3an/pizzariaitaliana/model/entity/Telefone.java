package com.itb.mif3an.pizzariaitaliana.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telefone {

    private Long id;
    private String numero;
    private String ddd;
    private String tipo;
    private boolean codStatus;
}
