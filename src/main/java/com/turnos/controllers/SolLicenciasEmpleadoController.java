package com.turnos.controllers;

import com.turnos.commons.CommonController;
import com.turnos.models.SolLicenciasEmpleado;
import com.turnos.projections.obtenerSolLicenciasProjection;
import com.turnos.services.SolLicenciasEmpleadoSvc;
import com.turnos.utils.security.AuthUtil;
import com.turnos.validator.SolLicenciasEmpleadoValidator;
import io.swagger.annotations.ApiOperation;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/solLicenciasEmpleado")
@RestController
public class SolLicenciasEmpleadoController extends CommonController<SolLicenciasEmpleado, SolLicenciasEmpleadoSvc, SolLicenciasEmpleadoValidator> {

    @Autowired
    private AuthUtil authUtil;

    @GetMapping("/obtenerSolLicencias/{estado}")
    public List<obtenerSolLicenciasProjection> obtenerSolLicenciasPA(@PathVariable String estado) {
        return service.obtenerSolLicencias(estado);
    }

    @PostMapping("/actualizarEstadoLicencia/{estadoSol}/{idLicencia}")
    @ApiOperation("Actualizar estado solLicencia")
    public void actualizarEstadoLicencia(@PathVariable String estadoSol, @PathVariable Long idLicencia) {
        service.actualizarEstadoLicencia(estadoSol, idLicencia);
    }

    @PostMapping("/guardar")
    public SolLicenciasEmpleado solicitarLicencia(@RequestBody SolLicenciasEmpleado solicitud) { //esto es un json que lleva los datos de la tabla en la que se van a insertar datos 
        Date date = new Date();
        
        String usuarioAdicino = authUtil.getCurrentUsername();
        
        SolLicenciasEmpleado nuevaSolicitud = new SolLicenciasEmpleado();
        nuevaSolicitud.setUsuario(usuarioAdicino);
        nuevaSolicitud.setCodLicencia(solicitud.getCodLicencia());
        nuevaSolicitud.setEstadoSolicitud(solicitud.getEstadoSolicitud());
        nuevaSolicitud.setFechaInicio(solicitud.getFechaInicio());
        nuevaSolicitud.setFechaFin(solicitud.getFechaFin());
        nuevaSolicitud.setMotivoSolicitud(solicitud.getMotivoSolicitud());
        nuevaSolicitud.setUsuarioAdiciono(usuarioAdicino);
        nuevaSolicitud.setFechaCreacion(date);

        service.save(nuevaSolicitud); //esto es un json que lleva los datos de la tabla en la que se van a insertar datos 

        return nuevaSolicitud; // retornan como tal el modelo con los datos que ingresamos 
    }

}
