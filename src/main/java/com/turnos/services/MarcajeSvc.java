/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services;

import com.turnos.commons.CommonSvc;
import com.turnos.models.Marcaje;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author yocary
 */
public interface MarcajeSvc extends CommonSvc<Marcaje> {

    Marcaje saveMarcaje(Marcaje marcaje);

    List<Marcaje> getMarcajesByFecha(LocalDate fecha);

    public Marcaje marcarEntrada();

    public Marcaje marcarDescanso1();

    public Marcaje marcarDescanso2();

    public Marcaje marcarSalida();

    public Marcaje findByUsuarioAndFecha();
}
