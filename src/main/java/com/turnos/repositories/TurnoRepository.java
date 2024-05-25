/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.repositories;

import com.turnos.models.Turno;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yocary
 */
@Repository
public interface TurnoRepository extends CrudRepository<Turno, Object> {

    @Modifying
    @Query(value = "INSERT INTO control_turnos.turno " //se insertan los campos en la tabla turno
            + "(dpi, fecha_inicio, fecha_fin, hora) "
            + "VALUES (:dpi, :fechaInicio, :fechaFin, :hora)",
            nativeQuery = true)
    void insertTurno(
            @Param("dpi") String dpi,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin,
            @Param("hora") Timestamp hora);
}
