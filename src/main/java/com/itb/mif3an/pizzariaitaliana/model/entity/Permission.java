package com.itb.mif3an.pizzariaitaliana.model.entity;

public enum Permission {

    ADMIN_READ("ADMIN_READ"),
    ADMIN_UPDATE("ADMIN_UPDATE"),
    ADMIN_CREATE("ADMIN_CREATE"),
    ADMIN_DELETE("ADMIN_DELETE"),
    CLIENTE_READ("CLIENTE_READ"),
    CLIENTE_UPDATE("CLIENTE_UPDATE"),
    CLIENTE_CREATE("CLIENTE_CREATE"),
    CLIENTE_DELETE("CLIENTE_DELETE"),
    FUNCIONARIO_READ("FUNCIONARIO_READ"),
    FUNCIONARIO_UPDATE("FUNCIONARIO_UPDATE"),
    FUNCIONARIO_CREATE("FUNCIONARIO_CREATE"),
    FUNCIONARIO_DELETE("FUNCIONARIO_DELETE");


    private String permission;

    Permission (String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }

}
