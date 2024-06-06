/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turnos.controllers;

/**
 *
 * @author yocary
 */
import com.turnos.commons.CommonController;
import com.turnos.dto.EmpleadoDTO;
import com.turnos.models.Empleado;
import com.turnos.models.Rol;
import com.turnos.repositories.EmpleadoRepository;
import com.turnos.services.EmpleadoSvc;
import com.turnos.services.impl.EmailServiceImpl;
import com.turnos.utils.security.AuthUtil;
import com.turnos.utils.security.UserDetailsServiceImpl;
import com.turnos.validator.EmpleadoValidator;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/empleado")// es como se llamara el controlador para ser utilizado
@RestController
public class EmpleadoController extends CommonController<Empleado, EmpleadoSvc, EmpleadoValidator> {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; //se utiliza para guardar la contraseña encriptada en BD

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/registrar") //indica que es un metodo post y tambien se forma la ruta de la api  
    public ResponseEntity<Object> registerEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {  // 

        String usuarioAdicino = authUtil.getCurrentUsername();
        
        if (empleadoRepository.existsById(empleadoDTO.getDpi())) { //este if valida si existe el dpi ingresado, si ya existe muestra un mensaje de error. 
            // Devuelve un JSON con un mensaje de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "El Usuario ya existe"));
        }

        Empleado empleado = new Empleado(); // se crea un objeto de tipo empleado
        empleado.setDpi(empleadoDTO.getDpi());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setArea(empleadoDTO.getArea());
        empleado.setEstado(empleadoDTO.getEstado());
        empleado.setUsuario(empleadoDTO.getUsuario());
        empleado.setCorreo(empleadoDTO.getCorreo());
        empleado.setTurnoActual(empleadoDTO.getTurno());
        empleado.setUsuarioAdiciono(usuarioAdicino);// se setean los datos ingreados en el empleadoDTO del requestbody
        empleado.setContrasenia(passwordEncoder.encode(empleadoDTO.getContrasenia())); // se forma la contraseña encriptada con el passwordEncoder

        empleadoRepository.save(empleado);// se utiliza el metodo save para guardae el objeto empleado

        try {
            String subject = "Bienvenido a la empresa";
            String htmlContent = "<p>Estimado " + empleado.getNombre() + ",</p>"
                    + "<p>Su usuario ha sido creado exitosamente.</p>"
                    + "<p><strong>Usuario:</strong> " + empleado.getUsuario() + "<br>"
                    + "<p><strong>Contraseña:</strong> " + empleadoDTO.getContrasenia() + "</p>"
                    + "<p>Saludos,</p><p>El equipo de Recursos Humanos</p>";

            emailService.sendHtmlEmail(empleado.getCorreo(), subject, htmlContent);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al enviar el correo de confirmación"));
        }

        // Devuelve un JSON con un mensaje de éxito
        return ResponseEntity.ok(Collections.singletonMap("message", "Empleado registrado con  éxito"));
    }

}
