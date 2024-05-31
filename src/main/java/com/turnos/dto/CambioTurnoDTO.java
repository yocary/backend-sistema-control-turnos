/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author yocary
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CambioTurnoDTO {

    private String usuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String turno;

}
