package festivos.api.core.dominio.dtos;

import java.time.LocalDate;

public class DiaFestivoDTO {

    private String nombre;
    private LocalDate fecha;
    private String tipo;

    public DiaFestivoDTO(String nombre, LocalDate fecha, String tipo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }
}
