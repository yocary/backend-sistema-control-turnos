package com.turnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.turnos")
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        // Establecer la zona horaria por defecto
        TimeZone.setDefault(TimeZone.getTimeZone("America/Guatemala"));
        logger.info("La zona horaria por defecto se ha establecido a: America/Guatemala");
        imprimirHoraActual();
    }

    private void imprimirHoraActual() {
        // Obtener la hora actual en la zona horaria de Guatemala
        long horaActual = System.currentTimeMillis();
        // Imprimir la hora actual en la consola
        logger.info("La hora actual en Guatemala (en milisegundos) es: " + horaActual);
    }
}
