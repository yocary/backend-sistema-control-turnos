/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services;

import com.turnos.commons.CommonSvc;
import com.turnos.models.SolLicenciasEmpleado;
import com.turnos.projections.obtenerSolLicenciasProjection;
import java.util.List;

/**
 *
 * @author yocary
 */
public interface SolLicenciasEmpleadoSvc extends CommonSvc<SolLicenciasEmpleado> {

    public List<obtenerSolLicenciasProjection> obtenerSolLicencias(String estado);

    public void actualizarEstadoLicencia(String estadoSol, Long idLicencia);
}
