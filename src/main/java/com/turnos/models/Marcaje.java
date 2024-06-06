/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.models;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author yocary
 */
@Entity
@Table(name = "marcaje", schema = "control_turnos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marcaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaDescanso1;
    private LocalTime horaDescanso2;
    private LocalTime horaSalida;
    private String usuarioAdiciono;
}
