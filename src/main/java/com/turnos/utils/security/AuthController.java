package com.turnos.utils.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.turnos.models.Empleado;
import com.turnos.models.Rol;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Usuario o contraseña incorrecta", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Empleado empleado = userDetailsService.getEmpleadoByUsername(authenticationRequest.getUsername());

        System.out.println("usurio del empleado: " + empleado.getUsuario());

        final String jwt = jwtUtil.generateTokenWithUserDetails(userDetails, empleado.getUsuario(), obtenerRoles(empleado), empleado.getCorreo());

        AuthResponse authResponse = new AuthResponse(jwt, empleado.getUsuario(), obtenerRoles(empleado));

        return ResponseEntity.ok(authResponse);
    }

    private Set<String> obtenerRoles(Empleado empleado) {
        return empleado.getRoles().stream().map(Rol::getNombre).collect(Collectors.toSet());
    }
}