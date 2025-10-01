package com.itb.mif3an.pizzariaitaliana.model.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.itb.mif3an.pizzariaitaliana.model.entity.Permission.*;

public enum Role {

    ADMIN (
            Set.of (
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    FUNCIONARIO_READ,
                    FUNCIONARIO_UPDATE,
                    FUNCIONARIO_DELETE,
                    FUNCIONARIO_CREATE
            )
    ),
    CLIENTE (
            Set.of (
                    CLIENTE_READ,
                    CLIENTE_UPDATE,
                    CLIENTE_DELETE,
                    CLIENTE_CREATE
            )
    ),
    FUNCIONARIO (
            Set.of (
                    FUNCIONARIO_READ,
                    FUNCIONARIO_UPDATE,
                    FUNCIONARIO_DELETE,
                    FUNCIONARIO_CREATE
            )
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {this.permissions = permissions;}

    public List<SimpleGrantedAuthority> getAuthorities() {

        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.toString()))
                .collect(Collectors.toList());
                authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }


}
