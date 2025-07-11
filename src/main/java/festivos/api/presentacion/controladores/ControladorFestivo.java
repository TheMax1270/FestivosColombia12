package festivos.api.presentacion.controladores;

import festivos.api.core.dominio.dtos.DiaFestivoDTO;
import festivos.api.core.dominio.interfaces.servicios.interfazfestivoR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/festivos")
public class ControladorFestivo {

    private final interfazfestivoR servicio;

    public ControladorFestivo(interfazfestivoR servicio) {
        this.servicio = servicio;
    }

    // Obtener todos los festivos de un año
    @GetMapping("/{anio}")
    public List<DiaFestivoDTO> obtenerFestivos(@PathVariable int anio) {
        return servicio.obtenerFestivosPorAnio(anio);
    }

    // Verificar si una fecha específica es festivo
    @GetMapping("/verificar/{anio}/{mes}/{dia}")
    public ResponseEntity<?> verificarFecha(
            @PathVariable int anio,
            @PathVariable int mes,
            @PathVariable int dia) {

        try {
            LocalDate fecha = LocalDate.of(anio, mes, dia);
            List<DiaFestivoDTO> festivos = servicio.obtenerFestivosPorAnio(anio);

            return festivos.stream()
                    .filter(f -> f.getFecha().equals(fecha))
                    .findFirst()
                    .<ResponseEntity<?>>map(f -> ResponseEntity.ok(
                        Map.of(
                            "mensaje", "Es festivo",
                            "fecha", f.getFecha().toString(),
                            "nombre", f.getNombre(),
                            "tipo", f.getTipo()
                        )))
                    .orElseGet(() -> ResponseEntity.ok(
                        Map.of("mensaje", "La fecha " + fecha + " no es festivo")));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Fecha no válida: " + e.getMessage()));
        }
    }
}
