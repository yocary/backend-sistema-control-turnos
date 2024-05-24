/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.controllers;

/**
 *
 * @author pdmelend
 */
import com.turnos.commons.CommonController;
import com.turnos.dto.EmpleadoDTO;
import com.turnos.models.Empleado;
import com.turnos.repositories.EmpleadoRepository;
import com.turnos.services.EmpleadoSvc;
import com.turnos.validator.EmpleadoValidator;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/empleado")
@RestController
public class EmpleadoController extends CommonController<Empleado, EmpleadoSvc, EmpleadoValidator>{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/publico/register")
    public ResponseEntity<Object> registerEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        if (empleadoRepository.existsById(empleadoDTO.getDpi())) {
            // Devuelve un JSON con un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Usuario already exists"));
        }

        Empleado empleado = new Empleado();
        empleado.setDpi(empleadoDTO.getDpi());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setArea(empleadoDTO.getArea());
        empleado.setEstado(empleadoDTO.getEstado());
        empleado.setUsuario(empleadoDTO.getUsuario());
        empleado.setTurnoActual(empleadoDTO.getTurno());
        empleado.setContrasenia(passwordEncoder.encode(empleadoDTO.getContrasenia()));

        empleadoRepository.save(empleado);

        // Devuelve un JSON con un mensaje de éxito
        return ResponseEntity.ok(Collections.singletonMap("message", "Empleado registered successfully"));
    }
}
