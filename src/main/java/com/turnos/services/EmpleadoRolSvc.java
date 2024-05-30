/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services;

import com.turnos.commons.CommonSvc;
import com.turnos.models.EmpleadoRol;

/**
 *
 * @author yocary
 */
public interface EmpleadoRolSvc extends CommonSvc<EmpleadoRol> {

    public EmpleadoRol saveEmpleadoRol(String usuario, int rolId);

    public void deleteEmpleadoRol(String usuario, int rolId);

}
