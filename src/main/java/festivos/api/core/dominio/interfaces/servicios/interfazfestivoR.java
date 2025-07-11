package festivos.api.core.dominio.interfaces.servicios;


import festivos.api.core.dominio.dtos.DiaFestivoDTO;
import java.util.List;

public interface interfazfestivoR {
    List<DiaFestivoDTO> obtenerFestivosPorAnio(int anio);
}
