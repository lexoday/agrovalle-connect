package co.edu.agrovalleconnect.service;

import co.edu.agrovalleconnect.dto.ProductoDTO;
import co.edu.agrovalleconnect.dto.ProductoRegistroDTO;
import java.util.List;

/** Operaciones de negocio disponibles para el catálogo de productos. */
public interface ProductoService {

  ProductoDTO publicar(ProductoRegistroDTO dto);

  List<ProductoDTO> filtrar(String municipio, String categoria);
}
