/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services.impl;

import com.turnos.commons.CommonSvcImpl;
import com.turnos.models.EmpleadoRol;
import com.turnos.projections.ObtenerDpiUsuarioProjection;
import com.turnos.repositories.EmpleadoRepository;
import com.turnos.repositories.EmpleadoRolRepository;
import com.turnos.services.EmpleadoRolSvc;
import com.turnos.utils.security.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yocary
 */
@Service
public class EmpleadoRolSvcImpl extends CommonSvcImpl<EmpleadoRol, EmpleadoRolRepository> implements EmpleadoRolSvc {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    private AuthUtil authUtil;

    public EmpleadoRol saveEmpleadoRol(String usuario, int rolId) {

        String usuarioAdicino = authUtil.getCurrentUsername();
        ObtenerDpiUsuarioProjection dpi = empleadoRepository.obtenerDpiUsuario(usuario);
        EmpleadoRol empleadoRol = new EmpleadoRol();
        empleadoRol.setId(new EmpleadoRol.EmpleadoRolId(dpi.getDpi(), rolId));
        empleadoRol.setUsuarioAdiciono(usuarioAdicino);
        return repository.save(empleadoRol);
    }

    public void deleteEmpleadoRol(String usuario, int rolId) {
        ObtenerDpiUsuarioProjection dpi = empleadoRepository.obtenerDpiUsuario(usuario);
        EmpleadoRol.EmpleadoRolId id = new EmpleadoRol.EmpleadoRolId(dpi.getDpi(), rolId);
        repository.deleteById(id);
    }
}
