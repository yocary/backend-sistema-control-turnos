/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.models;

/**
 *
 * @author yocary
 */
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sol_licencias_empleado", schema = "control_turnos")
public class SolLicenciasEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_licencia")
    private Long idLicencia;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    @Column(name = "cod_licencia", nullable = false, length = 5)
    private String codLicencia;

    @Column(name = "estado_solicitud", nullable = false, length = 5)
    private String estadoSolicitud;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "motivo_solicitud", length = 100)
    private String motivoSolicitud;

    @Column(name = "admin_aprobo", length = 50)
    private String adminAprobo;

    public Long getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Long idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCodLicencia() {
        return codLicencia;
    }

    public void setCodLicencia(String codLicencia) {
        this.codLicencia = codLicencia;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMotivoSolicitud() {
        return motivoSolicitud;
    }

    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAdminAprobo() {
        return adminAprobo;
    }

    public void setAdminAprobo(String adminAprobo) {
        this.adminAprobo = adminAprobo;
    }

}
