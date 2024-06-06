/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.repositories;

import com.turnos.models.SolLicenciasEmpleado;
import com.turnos.projections.obtenerLicenciaProjection;
import com.turnos.projections.obtenerSolLicenciasProjection;
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
public interface SolLicenciasEmpleadoRepository extends CrudRepository<SolLicenciasEmpleado, Object> {

    @Query(value = "SELECT \n"
            + "  sle.id_licencia as idLicencia,\n"
            + "  cl.nombre_estado AS tiposol,\n"
            + "  sle.usuario AS usuario,\n"
            + "  sle.motivo_solicitud AS justificacion,\n"
            + " COALESCE(NULLIF(sle.admin_aprobo, ''), '-') as adminAprobo,\n"
            + "  TO_CHAR(sle.fecha_creacion, 'DD/MM/YYYY') AS fechaCreacion\n"
            + "FROM \n"
            + "  sol_licencias_empleado sle\n"
            + "INNER JOIN \n"
            + "  catalogo_licencias cl \n"
            + "ON \n"
            + "  sle.cod_licencia = cl.cod_licencia \n"
            + "\n"
            + "WHERE \n"
            + "  estado_solicitud = :estado",
            nativeQuery = true)
    @Transactional
    public List<obtenerSolLicenciasProjection> obtenerSolLicencias(@Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sol_licencias_empleado\n"
            + "SET estado_solicitud = :estadoSol, admin_aprobo = :adminAprobo, usuario_adiciono = :usuarioAdiciono \n"
            + "WHERE id_licencia = :idLicencia",
            nativeQuery = true)
    void actualizarEstadoLicencia(@Param("estadoSol") String estadoSol, @Param("adminAprobo") String adminAprobo,
            @Param("idLicencia") Long idLicencia, @Param("usuarioAdiciono") String usuarioAdiciono);

    @Modifying
    @Query(value = "UPDATE control_turnos.empleado \n"
            + "SET estado = :estado, usuario_adiciono = :usuarioAdiciono \n"
            + "WHERE usuario = :usuario",
            nativeQuery = true)
    public void actualizarEstadoUsuario(@Param("estado") String estado, @Param("usuario") String usuario, @Param("usuarioAdiciono") String usuarioAdiciono);

    @Query(value = "select\n"
            + "	cl.nombre_estado as licencia,\n"
            + "	e.usuario,\n"
            + "	e.nombre,\n"
            + "	e.correo \n"
            + "from\n"
            + "	sol_licencias_empleado sle\n"
            + "inner join catalogo_licencias cl on\n"
            + "	sle.cod_licencia = cl.cod_licencia\n"
            + "inner join empleado e on\n"
            + "	e.usuario = sle.usuario\n"
            + "where\n"
            + "	id_licencia = :idLicencia",
            nativeQuery = true)
    @Transactional
    public obtenerLicenciaProjection obtenerLicencia(@Param("idLicencia") Long idLicencia);
}
