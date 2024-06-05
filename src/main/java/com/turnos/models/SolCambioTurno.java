/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author yocary
 */
@Entity
@Table(name = "sol_cambio_turno", schema = "control_turnos")
public class SolCambioTurno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    private String usuario;

    private Date fechaSolicitud;

    private Date fechaTurnoInicial;

    private Date fechaTurnoNuevo;

    private String turnoInicial;

    private String turnoNuevo;

    private String justificacion;

    private String estado;

    private String usuarioAdiciono;

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaTurnoInicial() {
        return fechaTurnoInicial;
    }

    public void setFechaTurnoInicial(Date fechaTurnoInicial) {
        this.fechaTurnoInicial = fechaTurnoInicial;
    }

    public Date getFechaTurnoNuevo() {
        return fechaTurnoNuevo;
    }

    public void setFechaTurnoNuevo(Date fechaTurnoNuevo) {
        this.fechaTurnoNuevo = fechaTurnoNuevo;
    }

    public String getTurnoInicial() {
        return turnoInicial;
    }

    public void setTurnoInicial(String turnoInicial) {
        this.turnoInicial = turnoInicial;
    }

    public String getTurnoNuevo() {
        return turnoNuevo;
    }

    public void setTurnoNuevo(String turnoNuevo) {
        this.turnoNuevo = turnoNuevo;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioAdiciono() {
        return usuarioAdiciono;
    }

    public void setUsuarioAdiciono(String usuarioAdiciono) {
        this.usuarioAdiciono = usuarioAdiciono;
    }

}
