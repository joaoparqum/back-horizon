package com.api.casadoconstrutor.horizonte.user;

public enum UserRole {

    ADMIN("admin"),
    ADMIN_VISTAS("admin_vistas"),
    ADMIN_SGHT("admin_sght"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
