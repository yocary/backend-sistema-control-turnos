/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.controllers;

import com.turnos.commons.CommonController;
import com.turnos.models.SolCambioTurno;
import com.turnos.projections.obtenerSolCambioTurnoProjection;
import com.turnos.services.SolCambioTurnoSvc;
import com.turnos.utils.security.AuthUtil;
import com.turnos.validator.SolCambioTurnoValidator;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yocary
 */
@RequestMapping("/solCambioTurno")
@RestController
public class SolCambioTurnoController extends CommonController<SolCambioTurno, SolCambioTurnoSvc, SolCambioTurnoValidator> {
    
    @Autowired
    private AuthUtil authUtil;
    
    @PostMapping("/guardarSolCambioTurno")
    public ResponseEntity<?> cambiarTurno(@RequestBody SolCambioTurno solicitud) {
        
        String usuarioAdicino = authUtil.getCurrentUsername();
        try {
            
            solicitud.setUsuarioAdiciono(usuarioAdicino);
            service.save(solicitud);
            return new ResponseEntity<>("Cambio de turno solicitado con éxito", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al solicitar el cambio de turno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/obtenerSolCambioTurno/{estado}")
    public List<obtenerSolCambioTurnoProjection> obtenerSolCambioTurno(@PathVariable String estado) {
        return service.obtenerSolCambioTurno(estado);
    }
    
    @PostMapping("/actualizarEstadoTurno/{estadoSol}/{idSolicitud}")
    @ApiOperation("Actualizar estado turno")
    public void actualizarEstadoTurno(@PathVariable String estadoSol, @PathVariable Long idSolicitud) {
        service.actualizarEstadoTurno(estadoSol, idSolicitud);
    }
}
