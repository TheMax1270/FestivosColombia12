package festivos.api.aplicacion.servicios;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class GestorFechas {

    public static LocalDate obtenerInicioSemanaSanta(int anio) {
        int a = anio % 19;
        int b = anio % 4;
        int c = anio % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3; // marzo
        if (dia > 31) {
            dia -= 31;
            mes = 4; // abril
        }

        return LocalDate.of(anio, mes, dia);
    }

    public static LocalDate sumarDias(LocalDate fecha, int dias) {
        return fecha.plusDays(dias);
    }

    public static LocalDate obtenerSiguienteLunes(LocalDate fecha) {
        if (fecha.getDayOfWeek() != DayOfWeek.MONDAY) {
            int diasFaltantes = 8 - fecha.getDayOfWeek().getValue();
            return fecha.plusDays(diasFaltantes);
        }
        return fecha;
    }
}
