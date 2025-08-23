package com.itb.mif3an.pizzariaitaliana.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itb.mif3an.pizzariaitaliana.util.BigDecimalDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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
    private BigDecimal valorVenda;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal valorCompra;
    @Column(nullable = true, length = 20)
    private String tipo;
    @JsonIgnore
    private int quantidadeEstoque;
    private boolean codStatus;

    // Relacionametos
    // @ManyToOne : Muitos p/ um
    // CascadeType: Define como as operações de persistência (INSERT, UPDATE, DELETE) serão
    //              propagadas para uma entidade filha no banco de dados.
    // MERGE: Propapaga operações de atualização da entidade pai para as filhas
    //        O MERGE é usado para atuallizar uma entidade desaclopada no banco de dados,
    //        Se a entidade não existe no banco, o MERGE a insere,
    //        Se já existe, o MERGE atualiza os dados com os novos valores
    // Além do MERGE, temos: ALL, PERSIST, REMOVE, REFRESH E DETACH
    // fetch: Define como os dados relacionados serão carregados do banco de dados quando
    //        a entidade for consultada
    // FetchType.LAZY: Os dados só serão carregados quando forem acessados explicitamente no código
    // FetchType.EAGER: Join automático, os dados relacionados serão carregados quando a entidade for
    //                  consultada


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = true)
    private Categoria categoria;


    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ItemPedido> itensPedido;


}
