package co.edu.agrovalleconnect.repository;

import co.edu.agrovalleconnect.model.Categoria;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Acceso a datos del catálogo de categorías. */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

  Optional<Categoria> findByNombreIgnoreCase(String nombre);
}
