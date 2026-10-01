package co.edu.agrovalleconnect.service;

import co.edu.agrovalleconnect.dto.ProductoDTO;
import co.edu.agrovalleconnect.model.Producto;
import co.edu.agrovalleconnect.repository.ProductoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/** Lógica de consulta del catálogo agrícola. */
@Service
public class ProductoService {

  private final ProductoRepository repository;

  public ProductoService(ProductoRepository repository) {
    this.repository = repository;
  }

  /** Filtra por municipio y/o categoría (ambos opcionales). */
  public List<ProductoDTO> filtrar(String municipio, String categoria) {
    boolean hayMunicipio = municipio != null && !municipio.isBlank();
    boolean hayCategoria = categoria != null && !categoria.isBlank();
    List<Producto> productos;
    if (hayMunicipio && hayCategoria) {
      productos =
          repository.findByMunicipioIgnoreCaseAndCategoriaIgnoreCase(municipio, categoria);
    } else if (hayMunicipio) {
      productos = repository.findByMunicipioIgnoreCase(municipio);
    } else if (hayCategoria) {
      productos = repository.findByCategoriaIgnoreCase(categoria);
    } else {
      productos = repository.findAll();
    }
    return productos.stream().map(ProductoDTO::desde).toList();
  }
}
