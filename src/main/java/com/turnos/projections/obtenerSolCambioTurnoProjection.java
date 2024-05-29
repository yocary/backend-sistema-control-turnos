/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.projections;

/**
 *
 * @author yocary
 */
public interface obtenerSolCambioTurnoProjection {
    
    String getIdSolicitud();
    String getTurnoActual();
    String getTurnoNuevo();
    String getUsuario();
    String getFecha();
    String getJustificacion();
}
