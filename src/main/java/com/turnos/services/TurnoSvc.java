/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services;

import com.turnos.commons.CommonSvc;
import com.turnos.models.Turno;
import java.time.LocalDate;

/**
 *
 * @author yocary
 */
public interface TurnoSvc extends CommonSvc<Turno>{
    
   public void guardarCambioTurno(String usuario, LocalDate fechaInicio, LocalDate fechaFin, String turno);
}
