/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services.impl;

import com.turnos.commons.CommonSvcImpl;
import com.turnos.exception.Excepciones;
import com.turnos.models.Turno;
import com.turnos.projections.ObtenerDpiUsuarioProjection;
import com.turnos.repositories.EmpleadoRepository;
import com.turnos.repositories.TurnoRepository;
import com.turnos.services.TurnoSvc;
import com.turnos.utils.security.AuthUtil;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yocary
 */
@Service
public class TurnoSvcImpl extends CommonSvcImpl<Turno, TurnoRepository> implements TurnoSvc {

    @Autowired
    private EmpleadoRepository empleadoRepository; // se hace el llamado del repositorio de otro modelo para ser utilizado en esta clase

    @Autowired
    private AuthUtil authUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void guardarCambioTurno(String usuario, LocalDate fechaInicio, LocalDate fechaFin, String turno) { //se recibe de tipo string del front
        Date date = new Date();

        String usuarioAdiciono = authUtil.getCurrentUsername();

//        ObtenerDpiUsuarioProjection dpi = this.empleadoRepository.obtenerDpiUsuario(usuario); //se declaro una variable dpi que es de tipo ObtenerDpiUsuarioProjection, esto se iguala a la  
//        //consulta que se encuentra en empleadorepository que se llama obtenerDpiUsuario
//        String dpiUsuario = dpi.getDpi(); //se declara una variable de tipo string y se iguala a la variable dpi donde se utiliza el  ObtenerDpiUsuarioProjection

        Timestamp timestamp = new Timestamp(date.getTime());// se convierte de date a timestamp para llenar el campo hora de la BD
        this.repository.insertTurno(usuario, fechaInicio, fechaFin, timestamp, usuarioAdiciono); // se llama al repositorio de turno, y se hace el llamado a la query TurnoRepository.java
        this.empleadoRepository.updateTurnoActual(usuario, turno); // Se utiliza la query updateTurnoActual para actualizar el turno de un empleado
    }

}
