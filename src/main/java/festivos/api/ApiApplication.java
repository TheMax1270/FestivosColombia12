package festivos.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

/*
 * Uso de la API de festivos:
 * 
 * - Verificar si una fecha es festiva:
 *   GET /festivos/verificar/{año}/{mes}/{día}
 *   Ejemplo: /festivos/verificar/2025/7/20
 * 
 * - Obtener todos los festivos de un año:
 *   GET /festivos/{año}
 *   Ejemplo: /festivos/2025
 * 
 * Las fechas deben ingresarse en formato numérico (YYYY/M/D).
 * La respuesta es JSON.
 */
