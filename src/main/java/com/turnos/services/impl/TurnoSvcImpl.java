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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void guardarCambioTurno(String usuario, String fechaInicio, String fechaFin, String turno) { //se recibe de tipo string del front
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // se forma un formato de fecha 
        
        ObtenerDpiUsuarioProjection dpi = this.empleadoRepository.obtenerDpiUsuario(usuario); //se declaro una variable dpi que es de tipo ObtenerDpiUsuarioProjection, esto se iguala a la  
                                                                                                //consulta que se encuentra en empleadorepository que se llama obtenerDpiUsuario
        String dpiUsuario = dpi.getDpi(); //se declara una variable de tipo string y se iguala a la variable dpi donde se utiliza el  ObtenerDpiUsuarioProjection
        try { // este es donde se maneja la logica que pueda presentar errores e inconvenientes
            Date fechaInicioDate = dateFormat.parse(fechaInicio); // en esta parte los string se convierten a date, y se utiliza el formato dateFormat
            Date fechaFinDate = dateFormat.parse(fechaFin);

            // Convertir java.util.Date a java.sql.Timestamp
            Timestamp timestamp = new Timestamp(date.getTime());// se convierte de date a timestamp para llenar el campo hora de la BD

            this.repository.insertTurno(dpiUsuario, fechaInicioDate, fechaFinDate, timestamp); // se llama al repositorio de turno, y se hace el llamado a la query TurnoRepository.java
            this.empleadoRepository.updateTurnoActual(dpiUsuario, turno); // Se utiliza la query updateTurnoActual para actualizar el turno de un empleado
        } catch (ParseException e) { // es donde se lanza la excepcion donde lo que esta en try dio error 
            System.out.println("Error " + e);
            throw new Excepciones("Error al guardar el cambio de turno");
        }
    }

}
