/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.repositories;

import com.turnos.models.SolCambioTurno;
import com.turnos.projections.obtenerInfoSolTurnoProjection;
import com.turnos.projections.obtenerSolCambioTurnoProjection;
import java.util.List;
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
public interface SolCambioTurnoRepository extends CrudRepository<SolCambioTurno, Object> {

    @Query(value = "select\n"
            + "	id_solicitud as idSolicitud,\n"
            + "	turno_inicial as turnoActual,\n"
            + "	turno_nuevo as turnoNuevo,\n"
            + "	usuario as usuario,\n"
            + "	TO_CHAR(fecha_solicitud, 'DD/MM/YYYY') fecha,\n"
            + "	justificacion as justificacion\n"
            + "from\n"
            + "	sol_cambio_turno sct\n"
            + "where\n"
            + "	estado = :estado",
            nativeQuery = true)
    @Transactional
    public List<obtenerSolCambioTurnoProjection> obtenerSolCambioTurno(@Param("estado") String estado);

    @Modifying
    @Query(value = "UPDATE sol_cambio_turno \n"
            + "SET estado = :estadoSol, usuario_adiciono = :usuarioAdiciono \n"
            + "WHERE id_solicitud = :idSolicitud",
            nativeQuery = true)
    public void actualizarEstadoTurno(@Param("estadoSol") String estadoSol, @Param("idSolicitud") Long idSolicitud, @Param("usuarioAdiciono") String usuarioAdiciono);

    @Modifying
    @Query(value = "UPDATE empleado \n"
            + "SET turno_actual = :turno, usuario_adiciono = :usuarioAdiciono \n"
            + "WHERE usuario = :usuario",
            nativeQuery = true)
    public void actualizarTurnoEmpleado(@Param("turno") String turno, @Param("usuario") String usuario, @Param("usuarioAdiciono") String usuarioAdiciono);

    @Query(value = "select\n"
            + "	id_solicitud as idSolicitud,\n"
            + "	turno_inicial as turnoActual,\n"
            + "	turno_nuevo as turnoNuevo,\n"
            + "	usuario as usuario,\n"
            + "	fecha_solicitud fecha,\n"
            + "	justificacion as justificacion\n"
            + "from\n"
            + "	sol_cambio_turno sct\n"
            + "where\n"
            + "	id_solicitud = :idSolicitud",
            nativeQuery = true)
    @Transactional
    public List<obtenerSolCambioTurnoProjection> obtenerSolCambioTurnoId(@Param("idSolicitud") Long idSolicitud);

    @Query(value = "select\n"
            + "	e.usuario,\n"
            + "	e.nombre,\n"
            + "	e.correo,"
            + " sct.turno_nuevo as turnoNuevo \n"
            + "from\n"
            + "	sol_cambio_turno sct\n"
            + "inner join empleado e on\n"
            + "	sct.usuario = e.usuario\n"
            + "where\n"
            + "	sct.id_solicitud = :idSolicitud",
            nativeQuery = true)
    @Transactional
    public obtenerInfoSolTurnoProjection obtenerInfoSolTurno(@Param("idSolicitud") Long idSolicitud);
}
