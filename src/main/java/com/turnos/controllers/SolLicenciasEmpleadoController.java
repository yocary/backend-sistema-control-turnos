package com.turnos.controllers;

import com.turnos.commons.CommonController;
import com.turnos.models.SolLicenciasEmpleado;
import com.turnos.projections.obtenerSolLicenciasProjection;
import com.turnos.services.SolLicenciasEmpleadoSvc;
import com.turnos.validator.SolLicenciasEmpleadoValidator;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/solLicenciasEmpleado")
@RestController
public class SolLicenciasEmpleadoController extends CommonController<SolLicenciasEmpleado, SolLicenciasEmpleadoSvc, SolLicenciasEmpleadoValidator> {

    @GetMapping("/obtenerSolLicencias/{estado}")
    public List<obtenerSolLicenciasProjection> obtenerSolLicenciasPA(@PathVariable String estado) {
        return service.obtenerSolLicencias(estado);
    }

    @PostMapping("/actualizarEstadoLicencia/{estadoSol}/{idLicencia}")
    @ApiOperation("Actualizar estado solLicencia")
    public void actualizarEstadoLicencia(@PathVariable String estadoSol, @PathVariable Long idLicencia) {
        service.actualizarEstadoLicencia(estadoSol, idLicencia);
    }
}
