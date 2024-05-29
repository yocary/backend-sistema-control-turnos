/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services;

import com.turnos.commons.CommonSvc;
import com.turnos.models.SolCambioTurno;
import com.turnos.projections.obtenerSolCambioTurnoProjection;
import java.util.List;

/**
 *
 * @author yocary
 */
public interface SolCambioTurnoSvc extends CommonSvc<SolCambioTurno> {

    public List<obtenerSolCambioTurnoProjection> obtenerSolCambioTurno(String estado);

    public void actualizarEstadoTurno(String estadoSol, Long idSolicitud);
}
