package com.itb.mif3an.pizzariaitaliana.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itb.mif3an.pizzariaitaliana.util.BigDecimalDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Produto") // facultativo quando o nome da classe é o mesmo da tabela
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT (Melhor opção para o SQL-SERVER)
    private Long id;
    @Column(nullable = false, length = 45)  // false: NOT NULL
    private String nome;
    @Column(nullable = true, length = 255)
    private String descricao;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private double valorVenda;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private double valorCompra;
    @Column(nullable = true, length = 20)
    private String tipo;
    @JsonIgnore
    private int quantidadeEstoque;
    private boolean codStatus;

}
