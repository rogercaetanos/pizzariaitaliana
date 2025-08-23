package com.itb.mif3an.pizzariaitaliana.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "Usuario")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Admin.class, name = "ADMIN"),
        @JsonSubTypes.Type(value = Cliente.class, name = "CLIENTE"),
        @JsonSubTypes.Type(value = Funcionario.class, name = "FUNCIONARIO")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 45)  // false: NOT NULL
    private String nome;
    @Column(nullable = false, length = 45)  // false: NOT NULL
    private String email;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = true, length = 15)  // false: NOT NULL
    private String cpf;
    @Column(nullable = true, length = 100)
    private String logradouro;
    @Column(nullable = true, length = 10)
    private String cep;
    @Column(nullable = true, length = 45)
    private String bairro;
    @Column(nullable = true, length = 45)
    private String cidade;
    @Column(nullable = true, length = 2)
    private String uf;
    private boolean codStatus;

    // insertable = false -> o valor desse campo não será considerado na hora de inserir um novo registro
    // updatable = false -> o valor desse campo não será atualizado quando houver update
    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private Role role;   // ADMIN, CLIENTE, FUNCIONARIO

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Telefone> telefones;
}
