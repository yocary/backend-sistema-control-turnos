/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.controllers;

import com.turnos.exception.Excepciones;
import com.turnos.services.EmpleadoRolSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yocary
 */
@RequestMapping("/empleadoRol")
@RestController
public class EmpleadoRolController {

    @Autowired
    private EmpleadoRolSvc service;

    @PostMapping("/guardar/{usuario}/{rolId}")
    public void saveEmpleadoRol(@PathVariable String usuario, @PathVariable int rolId) {
        service.saveEmpleadoRol(usuario, rolId);
    }

    @DeleteMapping("/eliminar/{usuario}/{rolId}")
    public void deleteEmpleadoRol(@PathVariable String usuario, @PathVariable int rolId) {
        try {
            service.deleteEmpleadoRol(usuario, rolId);
        } catch (EmptyResultDataAccessException e) {
            throw new Excepciones("No se encontró el registro de EmpleadoRol con el ID proporcionado.");
        }
    }
}
