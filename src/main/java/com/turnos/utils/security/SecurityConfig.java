package com.turnos.utils.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Agrega esta línea para imprimir la configuración de seguridad aplicada
        System.out.println("Configuración de seguridad aplicada: " + http);

        http.csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/solCambioTurno").hasAuthority("RolEmpleado")
                .antMatchers("/solLicenciasEmpleado/guardar").hasAuthority("RolEmpleado")
                .antMatchers("/solLicenciasEmpleado/**").hasAuthority("RolAdminArea")
                .antMatchers("/solLicenciasEmpleado").hasAuthority("RolAdminArea")
                .antMatchers("/empleado").hasAuthority("RolAdminArea")
                .antMatchers("/empleado/**").hasAuthority("RolAdminRRHH")
                .antMatchers("/empleado").hasAuthority("RolAdminRRHH")
                .antMatchers("/solLicenciasEmpleado/**").hasAuthority("RolAdminRRHH")
                .antMatchers("/solLicenciasEmpleado").hasAuthority("RolAdminRRHH")
                .antMatchers("/empleadoRol/**").hasAuthority("RolAdminRRHH")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Verifica si el objeto devuelto por getSharedObject es null antes de acceder a sus métodos
        FilterSecurityInterceptor filterSecurityInterceptor = http.getSharedObject(FilterSecurityInterceptor.class);
        if (filterSecurityInterceptor != null) {
            System.out.println("Configuración de autorización aplicada: " + filterSecurityInterceptor.getSecurityMetadataSource());
        }

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/v3/api-docs/**",
                "/swagger-ui/**"
        );
    }
}
