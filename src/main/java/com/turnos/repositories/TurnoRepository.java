/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.repositories;

import com.turnos.models.Turno;
import java.sql.Timestamp;
import java.time.LocalDate;
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
            + "(usuario, fecha_inicio, fecha_fin, hora, usuario_adiciono) "
            + "VALUES (:usuario, :fechaInicio, :fechaFin, :hora, :usuarioAdiciono)",
            nativeQuery = true)
    void insertTurno(
            @Param("usuario") String usuario,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("hora") Timestamp hora,
            @Param("usuarioAdiciono") String usuarioAdiciono);
}
