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

    public Empleado getCurrentEmpleado() {
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

    public String getCurrentUsername() {
        Empleado empleado = getCurrentEmpleado();
        return (empleado != null) ? empleado.getUsuario() : null;
    }

    public String getCurrentUserEmail() {
        Empleado empleado = getCurrentEmpleado();
        return (empleado != null) ? empleado.getCorreo() : null;
    }

    public String getCurrentUserNombre() {
        Empleado empleado = getCurrentEmpleado();
        return (empleado != null) ? empleado.getNombre(): null;
    }

    public Set<String> getCurrentUserRoles() {
        Empleado empleado = getCurrentEmpleado();
        return (empleado != null) ? empleado.getRoles().stream().map(Rol::getNombre).collect(Collectors.toSet()) : null;
    }
}
