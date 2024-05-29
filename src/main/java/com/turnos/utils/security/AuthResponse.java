package com.turnos.utils.security;

/**
 *
 * @author yocary
 */
public class AuthResponse {
    private final String jwt;

public class AuthResponse {
    private String jwt;
    private String dpi;
    private Set<String> roles;

    public AuthResponse(String jwt, String dpi, Set<String> roles) {
        this.jwt = jwt;
        this.dpi = dpi;
        this.roles = roles;
    }

    // Getters y setters

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}