package com.turnos.utils.security;

import com.turnos.models.Empleado;
import com.turnos.models.Rol;
import com.turnos.repositories.EmpleadoRepository;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByUsuario(username);
        if (empleado == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        Set<String> roleNames = empleado.getRoles().stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());

        System.out.println("Nombres de los roles del usuario: " + roleNames);

        return new User(empleado.getUsuario(), empleado.getContrasenia(), getAuthorities(empleado.getRoles()));
    }

    public Empleado getEmpleadoByUsername(String username) {
        Empleado empleado = empleadoRepository.findByUsuario(username);
        if (empleado == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return empleado;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Rol> roles) {
        Collection<? extends GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .collect(Collectors.toList());

        System.out.println("Nombres de los roles del usuario: " + authorities);

        return authorities;
    }

}
