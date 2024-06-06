/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services.impl;

import com.turnos.commons.CommonSvcImpl;
import com.turnos.models.SolCambioTurno;
import com.turnos.projections.obtenerInfoSolTurnoProjection;
import com.turnos.projections.obtenerSolCambioTurnoProjection;
import com.turnos.repositories.SolCambioTurnoRepository;
import com.turnos.services.SolCambioTurnoSvc;
import com.turnos.utils.security.AuthUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yocary
 */
@Service
public class SolCambioTurnoSvcImpl extends CommonSvcImpl<SolCambioTurno, SolCambioTurnoRepository> implements SolCambioTurnoSvc {

    @Autowired
    private EmailServiceImpl email;

    @Autowired
    private AuthUtil authUtil;

    @Override
    public List<obtenerSolCambioTurnoProjection> obtenerSolCambioTurno(String estado) {
        return repository.obtenerSolCambioTurno(estado);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void actualizarEstadoTurno(String estadoSol, Long idSolicitud) {

        obtenerInfoSolTurnoProjection turno = repository.obtenerInfoSolTurno(idSolicitud);

        String usuarioAdicino = authUtil.getCurrentUsername();

        System.out.println("CORREO::: " + turno.getCorreo());
        System.out.println("NOMBRE::: " + turno.getNombre());
        System.out.println("USUARIO::: " + turno.getUsuario());

        String estadoSolicitud = "";
        String estadoSolCorreo = "";
        String admin = "";

        try {
            if ("TA".equals(estadoSol)) {
                estadoSolicitud = "Aprobada";
                estadoSolCorreo = "aprobado";
                admin = "administrador del área";
            } else if ("TR".equals(estadoSol)) {
                estadoSolicitud = "Rechazada";
                estadoSolCorreo = "rechazado";
                admin = "administrador del área";
            }

            String asuntoCorreo = "Solicitud de cambio de turno " + estadoSolicitud;
            String content = "Estimado(a) " + turno.getNombre() + " <br> \n"
                    + " Se ha " + estadoSolCorreo + " la solicitud de cambio de turno por el " + admin + ".";

            email.sendHtmlEmail(turno.getCorreo(), asuntoCorreo, content);

            if ("TA".equals(estadoSol)) {

                repository.actualizarTurnoEmpleado(turno.getTurnoNuevo(), turno.getUsuario(), usuarioAdicino);
            }

            repository.actualizarEstadoTurno(estadoSol, idSolicitud, usuarioAdicino);

        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }

    }
}
