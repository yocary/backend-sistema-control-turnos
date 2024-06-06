/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services.impl;

import com.turnos.commons.CommonSvcImpl;
import com.turnos.models.Empleado;
import com.turnos.models.SolLicenciasEmpleado;
import com.turnos.projections.obtenerLicenciaProjection;
import com.turnos.projections.obtenerSolLicenciasProjection;
import com.turnos.repositories.SolLicenciasEmpleadoRepository;
import com.turnos.services.SolLicenciasEmpleadoSvc;
import com.turnos.utils.security.AuthController;
import com.turnos.utils.security.AuthUtil;
import com.turnos.utils.security.UserDetailsServiceImpl;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yocary
 */
@Service
public class SolLicenciasEmpleadoSvcImpl extends CommonSvcImpl<SolLicenciasEmpleado, SolLicenciasEmpleadoRepository> implements SolLicenciasEmpleadoSvc {

    @Autowired
    private EmailServiceImpl email;

    @Autowired
    private AuthUtil authUtil;

    @Override
    public List<obtenerSolLicenciasProjection> obtenerSolLicencias(String estado) {
        return repository.obtenerSolLicencias(estado);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void actualizarEstadoLicencia(String estadoSol, Long idLicencia) {

        obtenerLicenciaProjection licencia = repository.obtenerLicencia(idLicencia);

        String usuarioAprobo = authUtil.getCurrentUsername();//el usuario que aprobo la solicitud que es el usuario que esta logueado en ese momento

        String estadoSolicitud = "";
        String estadoSolCorreo = "";
        String admin = "";

        try {
            if ("AAA".equals(estadoSol)) {//aprobación por el admin
                estadoSolicitud = "Aprobada";
                estadoSolCorreo = "aprobado";
                admin = "administrador del área";
            } else if ("RAA".equals(estadoSol)) {
                estadoSolicitud = "Rechazada";
                estadoSolCorreo = "rechazado";
                admin = "administrador del área";
            } else if ("AAR".equals(estadoSol)) {
                estadoSolicitud = "Aprobada";
                estadoSolCorreo = "aprobado";
                admin = "administrador de RRHH";
            } else if ("RAR".equals(estadoSol)) {
                estadoSolicitud = "Rechazada";
                estadoSolCorreo = "rechazado";
                admin = "administrador de RRHH";
            }

            String asuntoCorreo = "Solicitud de Licencia " + estadoSolicitud;
            String content = "Estimado(a) " + licencia.getNombre() + " <br> \n"
                    + " Se ha " + estadoSolCorreo + " la solicitud de licencia por el " + admin + ".";

            email.sendHtmlEmail(licencia.getCorreo(), asuntoCorreo, content);

            if ("AAR".equals(estadoSol)) { // inactivar el usuario cuando se aprueba la licencia 

                String asuntoCorreoInactivar = "Solicitud de Licencia " + estadoSolicitud;
                String contentInactivar = "Estimado(a) " + licencia.getNombre() + " <br> \n"
                        + "Queremos informarle que tu usuario en el sistema ha sido marcado como inactivo. "
                        + "Esta decisión se ha tomado debido a la aprobación de la licencia: " + licencia.getLicencia() + ".";

                email.sendHtmlEmail(licencia.getCorreo(), asuntoCorreoInactivar, contentInactivar);

                repository.actualizarEstadoUsuario("Inactivo", licencia.getUsuario(), usuarioAprobo);
            }

            repository.actualizarEstadoLicencia(estadoSol, usuarioAprobo, idLicencia, usuarioAprobo);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

}
