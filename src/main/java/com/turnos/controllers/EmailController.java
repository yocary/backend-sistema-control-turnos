/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.controllers;

import com.turnos.services.impl.EmailServiceImpl;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/sendHtmlEmail")
    public String sendHtmlEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String htmlContent) {
        try {
            emailService.sendHtmlEmail(to, subject, htmlContent);// 
            return "Correo enviado con éxito";
        } catch (MessagingException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }
}
