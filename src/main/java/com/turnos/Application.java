import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.turnos")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public void setDefaultTimeZone() {
        // Establecer la zona horaria por defecto para la aplicación
        TimeZone.setDefault(TimeZone.getTimeZone("America/Guatemala"));

        // Imprimir la hora actual en la zona horaria de Guatemala
        imprimirHoraActual();
    }

    private void imprimirHoraActual() {
        // Obtener la hora actual en la zona horaria de Guatemala
        LocalDateTime horaActual = LocalDateTime.now(ZoneId.of("America/Guatemala"));
        // Formatear la hora actual
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaFormateada = horaActual.format(formato);
        // Imprimir la hora actual en la consola
        System.out.println("La hora actual en Guatemala es: " + horaFormateada);
    }
}
