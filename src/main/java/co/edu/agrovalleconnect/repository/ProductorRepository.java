package co.edu.agrovalleconnect.repository;

import co.edu.agrovalleconnect.model.Productor;
import org.springframework.data.jpa.repository.JpaRepository;

/** Acceso a datos de productores. */
public interface ProductorRepository extends JpaRepository<Productor, Long> {

  boolean existsByCedula(String cedula);

  boolean existsByCorreo(String correo);
}
