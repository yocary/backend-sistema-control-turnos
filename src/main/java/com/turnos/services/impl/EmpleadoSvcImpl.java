/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services.impl;

import com.turnos.commons.CommonSvcImpl;
import com.turnos.models.Empleado;
import com.turnos.models.Rol;
import com.turnos.repositories.EmpleadoRepository;
import com.turnos.services.EmpleadoSvc;
import com.turnos.utils.security.UserDetailsServiceImpl;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author yocary
 */
@Service
public class EmpleadoSvcImpl extends CommonSvcImpl<Empleado, EmpleadoRepository> implements EmpleadoSvc {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

//    @GetMapping("/roles/{dpi}")
//    public ResponseEntity<?> getRolesByDpi(@PathVariable String dpi) {
//        Empleado empleado = userDetailsService.getEmpleadoByDpi(dpi);
//        if (empleado == null) {
//            return ResponseEntity.notFound().build();
//        }
//        Set<String> roles = empleado.getRoles().stream().map(Rol::getNombre).collect(Collectors.toSet());
//        return ResponseEntity.ok(roles);
//    }
}
