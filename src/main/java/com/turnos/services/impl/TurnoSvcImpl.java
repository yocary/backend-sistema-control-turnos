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
 * @author pdmelend
 */
@Service
public class TurnoSvcImpl extends CommonSvcImpl<Turno, TurnoRepository> implements TurnoSvc {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void guardarCambioTurno(String usuario, String fechaInicio, String fechaFin, String turno) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        ObtenerDpiUsuarioProjection dpi = this.empleadoRepository.obtenerDpiUsuario(usuario);

        String dpiUsuario = dpi.getDpi();
        try {
            Date fechaInicioDate = dateFormat.parse(fechaInicio);
            Date fechaFinDate = dateFormat.parse(fechaFin);

            // Convertir java.util.Date a java.sql.Timestamp
            Timestamp timestamp = new Timestamp(date.getTime());

            this.repository.insertTurno(dpiUsuario, fechaInicioDate, fechaFinDate, timestamp);
            this.empleadoRepository.updateTurnoActual(dpiUsuario, turno);
        } catch (ParseException e) {
            System.out.println("Error " + e);
            throw new Excepciones("Error al guardar el cambio de turno");
        }
    }

}
