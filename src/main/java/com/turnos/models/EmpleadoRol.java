/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author yocary
 */
@Entity
@Table(name = "empleado_rol", schema = "control_turnos")
public class EmpleadoRol implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EmpleadoRolId id;

    @ManyToOne
    @JoinColumn(name = "empleado_dpi", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_empleado"))
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "rol_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_rol"))
    private Rol rol;

    @Column(name = "usuario_adiciono", unique = true, nullable = false)
    private String usuarioAdiciono;
    
    public EmpleadoRol() {
    }

    public EmpleadoRol(EmpleadoRolId id) {
        this.id = id;
    }

    public EmpleadoRolId getId() {
        return id;
    }

    public void setId(EmpleadoRolId id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUsuarioAdiciono() {
        return usuarioAdiciono;
    }

    public void setUsuarioAdiciono(String usuarioAdiciono) {
        this.usuarioAdiciono = usuarioAdiciono;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmpleadoRol that = (EmpleadoRol) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @javax.persistence.Embeddable
    public static class EmpleadoRolId implements Serializable {

        private static final long serialVersionUID = 1L;

        @Column(name = "empleado_dpi")
        private String empleadoDpi;

        @Column(name = "rol_id")
        private int rolId;

        public EmpleadoRolId() {
        }

        public EmpleadoRolId(String empleadoDpi, int rolId) {
            this.empleadoDpi = empleadoDpi;
            this.rolId = rolId;
        }

        public String getEmpleadoDpi() {
            return empleadoDpi;
        }

        public void setEmpleadoDpi(String empleadoDpi) {
            this.empleadoDpi = empleadoDpi;
        }

        public int getRolId() {
            return rolId;
        }

        public void setRolId(int rolId) {
            this.rolId = rolId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EmpleadoRolId that = (EmpleadoRolId) o;
            return rolId == that.rolId && Objects.equals(empleadoDpi, that.empleadoDpi);
        }

        @Override
        public int hashCode() {
            return Objects.hash(empleadoDpi, rolId);
        }
    }
}
