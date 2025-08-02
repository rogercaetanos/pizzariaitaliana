package com.itb.mif3an.pizzariaitaliana.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    private Long id;
    private LocalDateTime dataHoraPedido;
    private LocalDateTime dataHoraEntrega;
    private String status;
    private double valorPedido;
    private boolean codStatus;

}
