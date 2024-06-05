/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.Table;

/**
 *
 * @author yocary
 */
@Entity
@Table(name = "empleado", schema = "control_turnos")
//mapeo de cada campo que esta en la tabla, el tipo de campo que esta en la tabla y se agregan los metodos getter y setter
public class Empleado {

    @Id    //es una anotacion
    @Column(name = "dpi", unique = true, nullable = false)
    private String dpi;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String correo;

    @Column(unique = true, nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String contrasenia;

    @Column(name = "turno_actual", nullable = false)
    private String turnoActual;

    @Column(unique = true, nullable = false)
    private String usuarioAdiciono;

    @ManyToMany(fetch = FetchType.EAGER) // tiene relacion 
    @JoinTable(
            name = "empleado_rol",
            joinColumns = @JoinColumn(name = "empleado_dpi"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public String getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(String turnoActual) {
        this.turnoActual = turnoActual;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuarioAdiciono() {
        return usuarioAdiciono;
    }

    public void setUsuarioAdiciono(String usuarioAdiciono) {
        this.usuarioAdiciono = usuarioAdiciono;
    }

}
