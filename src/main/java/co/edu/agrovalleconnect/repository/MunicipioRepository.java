package co.edu.agrovalleconnect.repository;

import co.edu.agrovalleconnect.model.Municipio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Acceso a datos del catálogo de municipios. */
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

  Optional<Municipio> findByNombreIgnoreCase(String nombre);
}
