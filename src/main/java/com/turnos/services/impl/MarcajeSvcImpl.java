/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.services.impl;

import com.turnos.commons.CommonSvcImpl;
import com.turnos.models.Marcaje;
import com.turnos.repositories.MarcajeRepository;
import com.turnos.services.MarcajeSvc;
import com.turnos.utils.security.AuthUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yocary
 */
@Service
public class MarcajeSvcImpl extends CommonSvcImpl<Marcaje, MarcajeRepository> implements MarcajeSvc {

    @Autowired
    private AuthUtil authUtil;

    @Override
    public Marcaje saveMarcaje(Marcaje marcaje) {

        marcaje.setUsuarioAdiciono(authUtil.getCurrentUsername());
        return repository.save(marcaje);
    }

    @Override
    public List<Marcaje> getMarcajesByFecha(LocalDate fecha) {
        return repository.findByFecha(fecha);
    }

    @Override
    public Marcaje marcarEntrada() {
        return marcarAccion("entrada");
    }

    @Override
    public Marcaje marcarDescanso1() {
        return marcarAccion("descanso1");
    }

    @Override
    public Marcaje marcarDescanso2() {
        return marcarAccion("descanso2");
    }

    @Override
    public Marcaje marcarSalida() {
        return marcarAccion("salida");
    }

    private Marcaje marcarAccion(String tipoAccion) {

        String usuario = authUtil.getCurrentUsername();

        ZoneId zonaHorariaCliente = ZoneId.systemDefault();

        LocalDate fechaActual = LocalDate.now(zonaHorariaCliente);

        Marcaje marcajeExistente = repository.findByUsuarioAdicionoAndFecha(usuario, fechaActual);

        if (marcajeExistente == null) {
            marcajeExistente = new Marcaje();
            marcajeExistente.setFecha(fechaActual);
        }

        switch (tipoAccion) {
            case "entrada":
                marcajeExistente.setHoraEntrada(LocalTime.now());
                marcajeExistente.setUsuarioAdiciono(authUtil.getCurrentUsername());
                break;
            case "descanso1":
                marcajeExistente.setHoraDescanso1(LocalTime.now());
                marcajeExistente.setUsuarioAdiciono(authUtil.getCurrentUsername());
                break;
            case "descanso2":
                marcajeExistente.setHoraDescanso2(LocalTime.now());
                marcajeExistente.setUsuarioAdiciono(authUtil.getCurrentUsername());
                break;
            case "salida":
                marcajeExistente.setHoraSalida(LocalTime.now());
                marcajeExistente.setUsuarioAdiciono(authUtil.getCurrentUsername());
                break;
            default:

                throw new IllegalArgumentException("Acción no válida: " + tipoAccion);
        }

        return repository.save(marcajeExistente);
    }

    @Override
    public Marcaje findByUsuarioAndFecha() {

        String usuario = authUtil.getCurrentUsername();

        ZoneId zonaHorariaCliente = ZoneId.systemDefault();

        LocalDate fechaActual = LocalDate.now(zonaHorariaCliente);

        return repository.findByUsuarioAdicionoAndFecha(usuario, fechaActual);
    }

}
