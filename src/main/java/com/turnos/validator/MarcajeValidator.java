/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.validator;

import com.turnos.commons.CommonValidatorSvc;
import com.turnos.models.Marcaje;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author yocary
 */
@Component("MarcajeValidator")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MarcajeValidator implements CommonValidatorSvc<Marcaje>{
    
    
    @Override
    public Marcaje validate(Marcaje e){
        return e;
    }
    
}
