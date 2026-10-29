package co.edu.agrovalleconnect.repository;

import co.edu.agrovalleconnect.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** Acceso a datos de productos. */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  List<Producto> findByMunicipioNombreIgnoreCaseAndCategoriaNombreIgnoreCase(
      String municipio, String categoria);

  List<Producto> findByMunicipioNombreIgnoreCase(String municipio);

  List<Producto> findByCategoriaNombreIgnoreCase(String categoria);
}
