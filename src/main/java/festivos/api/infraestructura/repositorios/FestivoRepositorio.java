package festivos.api.infraestructura.repositorios;

import festivos.api.core.dominio.entidades.Festivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FestivoRepositorio extends JpaRepository<Festivos, Integer> {

    @Query("SELECT f FROM Festivos f WHERE LOWER(f.nombre) LIKE LOWER(CONCAT('%', :dato, '%'))")
    List<Festivos> buscar(String dato);
}
