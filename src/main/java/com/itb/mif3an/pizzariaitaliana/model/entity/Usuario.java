package com.itb.mif3an.pizzariaitaliana.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected Long id;
    @Column(nullable = false, length = 45)  // false: NOT NULL
    protected String nome;
    @Column(nullable = false, length = 45)  // false: NOT NULL
    protected String email;
    @Column(nullable = false, length = 255)
    protected String password;
    @Column(nullable = true, length = 15)  // false: NOT NULL
    protected String cpf;
    @Column(nullable = true, length = 100)
    protected String logradouro;
    @Column(nullable = true, length = 10)
    protected String cep;
    @Column(nullable = true, length = 45)
    protected String bairro;
    @Column(nullable = true, length = 45)
    protected String cidade;
    @Column(nullable = true, length = 2)
    protected String uf;
    protected boolean codStatus;

    // insertable = false -> o valor desse campo não será considerado na hora de inserir um novo registro
    // updatable = false -> o valor desse campo não será atualizado quando houver update
    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    protected Role role;   // ADMIN, CLIENTE, FUNCIONARIO

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    protected List<Telefone> telefones;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
