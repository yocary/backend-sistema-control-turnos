/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.repositories;

import com.turnos.models.Marcaje;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yocary
 */
@Repository
public interface MarcajeRepository extends CrudRepository<Marcaje, Object> {

    Marcaje findByUsuarioAdicionoAndFecha(String usuario, LocalDate fecha);

    List<Marcaje> findByFecha(LocalDate fecha);
}
