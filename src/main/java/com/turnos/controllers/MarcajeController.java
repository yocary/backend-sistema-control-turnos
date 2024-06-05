/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.controllers;

import com.turnos.commons.CommonController;
import com.turnos.models.Marcaje;
import com.turnos.services.MarcajeSvc;
import com.turnos.validator.MarcajeValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yocary
 */
@RequestMapping("/marcaje")
@RestController
public class MarcajeController extends CommonController<Marcaje, MarcajeSvc, MarcajeValidator> {

    @PostMapping("/entrada")
    public Marcaje marcarEntrada() {
        return service.marcarEntrada();
    }

    @PostMapping("/descanso1")
    public Marcaje marcarDescanso1() {
        Marcaje marcaje = new Marcaje();
        return service.marcarDescanso1();
    }

    @PostMapping("/descanso2")
    public Marcaje marcarDescanso2() {
        Marcaje marcaje = new Marcaje();
        return service.marcarDescanso2();
    }

    @PostMapping("/salida")
    public Marcaje marcarSalida() {
        Marcaje marcaje = new Marcaje();
        return service.marcarSalida();
    }

    @GetMapping("/obtenerMarcajes")
    public ResponseEntity<Marcaje> getMarcajeByUsuarioAndFecha() {

        Marcaje marcaje = service.findByUsuarioAndFecha();
        return ResponseEntity.ok().body(marcaje);
    }
}
