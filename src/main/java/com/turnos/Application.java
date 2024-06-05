package com.turnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.turnos")
public class Application {
    public static void main(String[] args) {
        // Establecer la zona horaria del servidor a Guatemala
        System.setProperty("user.timezone", "America/Guatemala");

        SpringApplication.run(Application.class, args);

        LocalDateTime horaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaFormateada = horaActual.format(formato);

        System.out.println("La hora actual en Guatemala es: " + horaFormateada);
    }
}
