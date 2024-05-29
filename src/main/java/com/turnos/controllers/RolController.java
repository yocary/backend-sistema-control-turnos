/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.controllers;

import com.turnos.models.Rol;
import com.turnos.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yocary
 */
@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody Rol rol) {
        Rol existingRole = rolRepository.findByNombre(rol.getNombre());
        if (existingRole != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Rol ya existe");
        }
        rolRepository.save(rol);
        return ResponseEntity.ok("Rol agregado con éxito");
    }
}
