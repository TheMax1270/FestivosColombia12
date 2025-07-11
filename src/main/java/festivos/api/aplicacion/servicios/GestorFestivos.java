package festivos.api.aplicacion.servicios;

import festivos.api.core.dominio.dtos.DiaFestivoDTO;
import festivos.api.core.dominio.entidades.Festivos;
import festivos.api.core.dominio.interfaces.servicios.interfazfestivoR;
import festivos.api.infraestructura.repositorios.FestivoRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GestorFestivos implements interfazfestivoR {

    private final FestivoRepositorio repositorio;

    public GestorFestivos(FestivoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<DiaFestivoDTO> obtenerFestivosPorAnio(int anio) {
        List<Festivos> listaFestivos = repositorio.findAll();
        List<DiaFestivoDTO> resultado = new ArrayList<>();

        // Domingo de Pascua base
        LocalDate pascua = GestorFechas.obtenerInicioSemanaSanta(anio).plusDays(7);

        for (Festivos festivo : listaFestivos) {
            LocalDate fecha;

            try {
                switch (festivo.getTipo().getId()) {
                    case 1: // Fijo
                        fecha = LocalDate.of(anio, festivo.getMes(), festivo.getDia());
                        break;

                    case 2: // Ley Puente Festivo
                        fecha = GestorFechas.obtenerSiguienteLunes(LocalDate.of(anio, festivo.getMes(), festivo.getDia()));
                        break;

                    case 3: // Basado en Pascua
                        fecha = GestorFechas.sumarDias(pascua, festivo.getDiasPascua());
                        break;

                    case 4: // Basado en Pascua + Ley de lunes
                        fecha = GestorFechas.obtenerSiguienteLunes(
                                GestorFechas.sumarDias(pascua, festivo.getDiasPascua()));
                        break;

                    default:
                        continue;
                }

                resultado.add(new DiaFestivoDTO(
                        festivo.getNombre(),
                        fecha,
                        festivo.getTipo().getDescripcion()
                ));

            } catch (Exception e) {
                System.err.println("Error al procesar festivo: " + festivo.getNombre() + " -> " + e.getMessage());
            }
        }

        return resultado;
    }
}
