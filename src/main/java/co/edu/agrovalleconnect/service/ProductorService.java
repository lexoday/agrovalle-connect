package co.edu.agrovalleconnect.service;

import co.edu.agrovalleconnect.dto.ProductorRegistroDTO;
import co.edu.agrovalleconnect.dto.ProductorRespuestaDTO;
import co.edu.agrovalleconnect.exception.CedulaDuplicadaException;
import co.edu.agrovalleconnect.exception.CorreoDuplicadoException;
import co.edu.agrovalleconnect.exception.ProductorNoEncontradoException;
import co.edu.agrovalleconnect.model.Productor;
import co.edu.agrovalleconnect.repository.ProductorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** Lógica de negocio de productores. */
@Service
public class ProductorService {

  private final ProductorRepository repository;
  private final PasswordEncoder passwordEncoder;

  public ProductorService(ProductorRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  /** Registra un productor validando duplicidad de cédula y correo. */
  public ProductorRespuestaDTO registrar(ProductorRegistroDTO dto) {
    if (repository.existsByCedula(dto.cedula())) {
      throw new CedulaDuplicadaException("La cédula ya está registrada");
    }
    if (repository.existsByCorreo(dto.correo())) {
      throw new CorreoDuplicadoException("El correo ya está registrado");
    }
    Productor p = new Productor();
    p.setNombre(dto.nombre());
    p.setCorreo(dto.correo());
    p.setCedula(dto.cedula());
    p.setMunicipio(dto.municipio());
    p.setNombreFinca(dto.nombreFinca());
    p.setPasswordHash(passwordEncoder.encode(dto.password()));
    return ProductorRespuestaDTO.desde(repository.save(p));
  }

  /** Consulta un productor por id. */
  public ProductorRespuestaDTO consultarPorId(Long id) {
    Productor p =
        repository
            .findById(id)
            .orElseThrow(
                () -> new ProductorNoEncontradoException("No existe productor con id " + id));
    return ProductorRespuestaDTO.desde(p);
  }
}
