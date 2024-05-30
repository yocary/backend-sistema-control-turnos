/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.repositories;

import com.turnos.models.EmpleadoRol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yocary
 */
@Repository
public interface EmpleadoRolRepository extends CrudRepository<EmpleadoRol, Object> {

}
