/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services.impl;

import com.turnos.commons.CommonSvcImpl;
import com.turnos.models.Empleado;
import com.turnos.repositories.EmpleadoRepository;
import com.turnos.services.EmpleadoSvc;
import org.springframework.stereotype.Service;

/**
 *
 * @author yocary
 */
@Service
public class EmpleadoSvcImpl extends CommonSvcImpl<Empleado, EmpleadoRepository> implements EmpleadoSvc{
    
}
