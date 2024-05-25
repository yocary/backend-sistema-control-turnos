/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.validator;

import com.turnos.commons.CommonValidatorSvc;
import com.turnos.models.Empleado;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author yocary
 */
@Component("EmpleadoValidator")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class EmpleadoValidator implements CommonValidatorSvc<Empleado>{  //el emplado es el modelo
    
    
    @Override
    public Empleado validate(Empleado e){ //devolver una esepcion al validador
        return e;
    }
    
}
