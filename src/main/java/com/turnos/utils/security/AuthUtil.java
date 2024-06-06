package com.turnos.utils.security;

import com.turnos.models.Empleado;
import com.turnos.models.Rol;
import com.turnos.utils.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@Component
public class AuthUtil {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = Logger.getLogger(AuthUtil.class.getName());

    public Empleado getCurrentEmpleado() { // se obtiene la info del usuario logueado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            logger.info("Authenticated username: " + userDetails.getUsername());
            return userDetailsService.getEmpleadoByUsername(userDetails.getUsername());
        } else {
            logger.warning("No authenticated user found.");
        }
        return null;
    }

    public String getCurrentUsername() { //se obtiene el usuario
        Empleado empleado = getCurrentEmpleado();
        return (empleado != null) ? empleado.getUsuario() : null;
    }

    public String getCurrentUserEmail() { // se obtiene el correo del usuario logueado
        Empleado empleado = getCurrentEmpleado();
        return (empleado != null) ? empleado.getCorreo() : null;
    }

    public String getCurrentUserNombre() { // nombre del usuario logueado
        Empleado empleado = getCurrentEmpleado();
        return (empleado != null) ? empleado.getNombre(): null;
    }

    public Set<String> getCurrentUserRoles() { //se obtiene el rol de usuario logueado
        Empleado empleado = getCurrentEmpleado();
        return (empleado != null) ? empleado.getRoles().stream().map(Rol::getNombre).collect(Collectors.toSet()) : null;
    }
}
