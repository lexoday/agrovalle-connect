package co.edu.agrovalleconnect.service;

import co.edu.agrovalleconnect.dto.ProductorRegistroDTO;
import co.edu.agrovalleconnect.dto.ProductorRespuestaDTO;

/** Operaciones de negocio disponibles para productores. */
public interface ProductorService {

  ProductorRespuestaDTO registrar(ProductorRegistroDTO dto);

  ProductorRespuestaDTO consultarPorId(Long id);
}
