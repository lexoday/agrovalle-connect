package co.edu.agrovalleconnect.repository;

import co.edu.agrovalleconnect.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** Acceso a datos de productos. */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  List<Producto> findByMunicipioIgnoreCaseAndCategoriaIgnoreCase(
      String municipio, String categoria);

  List<Producto> findByMunicipioIgnoreCase(String municipio);

  List<Producto> findByCategoriaIgnoreCase(String categoria);
}
