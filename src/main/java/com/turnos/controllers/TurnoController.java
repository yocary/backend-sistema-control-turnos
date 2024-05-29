/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.controllers;

import com.turnos.commons.CommonController;
import com.turnos.dto.CambioTurnoDTO;
import com.turnos.models.Turno;
import com.turnos.services.TurnoSvc;
import com.turnos.validator.TurnoValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yocary
 */
@RequestMapping("/turno")
@RestController
public class TurnoController extends CommonController<Turno, TurnoSvc, TurnoValidator> {

    @PostMapping("/guardarCambioTurno")
    public void guardarCambioTurno(@RequestBody CambioTurnoDTO request) {
        service.guardarCambioTurno(request.getUsuario(), request.getFechaInicio(), request.getFechaFin(), request.getTurno());
    }
}
