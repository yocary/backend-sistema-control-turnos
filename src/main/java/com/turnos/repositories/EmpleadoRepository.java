/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.repositories;

import com.turnos.models.Empleado;
import com.turnos.projections.ObtenerDpiUsuarioProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yocary
 */
@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Object> {

    Empleado findByUsuario(String username);  // realiza un select a la tabla de empleado enviando como parametro el usuario

    Empleado findByDpi(String dpi);

    @Modifying
    @Query(value = "UPDATE control_turnos.empleado AS emp\n"
            + "SET turno_actual = :nuevoTurno\n"
            + "WHERE emp.usuario = :usuario",
            nativeQuery = true)
    @Transactional
    int updateTurnoActual(@Param("usuario") String dpi, @Param("nuevoTurno") String nuevoTurno); // esta query se utiliza para actualizar el turno actual enviando como condicion el dpi

    @Query(value = "select e.dpi from control_turnos.empleado e where usuario = :usuario", // esto se consume en turnosvcimpl
            nativeQuery = true)
    @Transactional
    ObtenerDpiUsuarioProjection obtenerDpiUsuario(@Param("usuario") String usuario); //hace un select a la tabla empleado, enviando como parametro el usuario y obteniendo de la consulta solo el dpi
}
