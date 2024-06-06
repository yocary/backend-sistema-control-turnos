package com.turnos.controllers;

import com.turnos.commons.CommonController;
import com.turnos.models.SolLicenciasEmpleado;
import com.turnos.projections.obtenerSolLicenciasProjection;
import com.turnos.services.SolLicenciasEmpleadoSvc;
import com.turnos.validator.SolLicenciasEmpleadoValidator;
import io.swagger.annotations.ApiOperation;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
    public SolLicenciasEmpleado solicitarLicencia(@RequestBody SolLicenciasEmpleado solicitud) {//esto es un json que lleva los datos de la tabla en la que se van a insertar datos 
        Date date = new Date();
        SolLicenciasEmpleado nuevaSolicitud = new SolLicenciasEmpleado();
        nuevaSolicitud.setUsuario(solicitud.getUsuario());
        nuevaSolicitud.setCodLicencia(solicitud.getCodLicencia());
        nuevaSolicitud.setEstadoSolicitud(solicitud.getEstadoSolicitud());
        nuevaSolicitud.setFechaInicio(solicitud.getFechaInicio());
        nuevaSolicitud.setFechaFin(solicitud.getFechaFin());
        nuevaSolicitud.setMotivoSolicitud(solicitud.getMotivoSolicitud());
        nuevaSolicitud.setFechaCreacion(date);

        service.save(nuevaSolicitud);// se envian los datos de modelo para ser guardados en BD

        return nuevaSolicitud;// retornan como tal el modelo con los datos que ingresamos 
    }

}
