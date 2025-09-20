package com.itb.mif3an.pizzariaitaliana.token;

import com.itb.mif3an.pizzariaitaliana.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true)
    public String token;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;
    public boolean revoked;
    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    public Usuario usuario;
}
