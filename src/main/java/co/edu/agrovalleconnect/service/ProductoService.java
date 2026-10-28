package co.edu.agrovalleconnect.service;

import co.edu.agrovalleconnect.dto.ProductoDTO;
import co.edu.agrovalleconnect.dto.ProductoRegistroDTO;
import co.edu.agrovalleconnect.exception.ProductorNoEncontradoException;
import co.edu.agrovalleconnect.model.Producto;
import co.edu.agrovalleconnect.model.Productor;
import co.edu.agrovalleconnect.repository.ProductoRepository;
import co.edu.agrovalleconnect.repository.ProductorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/** Lógica del catálogo agrícola: publicación de cosechas (HU-02) y filtros (HU-04). */
@Service
public class ProductoService {

  private final ProductoRepository repository;
  private final ProductorRepository productorRepository;

  public ProductoService(
      final ProductoRepository repository, final ProductorRepository productorRepository) {
    this.repository = repository;
    this.productorRepository = productorRepository;
  }

  /** Publica una cosecha asociada a un productor existente. */
  public ProductoDTO publicar(final ProductoRegistroDTO dto) {
    Productor productor =
        productorRepository
            .findById(dto.productorId())
            .orElseThrow(
                () ->
                    new ProductorNoEncontradoException(
                        "No existe productor con id " + dto.productorId()));
    Producto p = new Producto();
    p.setProductor(productor);
    p.setNombre(dto.nombre());
    p.setCategoria(dto.categoria());
    p.setMunicipio(dto.municipio());
    p.setPrecio(dto.precio());
    p.setCantidadKg(dto.cantidadKg());
    return ProductoDTO.desde(repository.save(p));
  }

  /** Filtra por municipio y/o categoría (ambos opcionales). */
  public List<ProductoDTO> filtrar(final String municipio, final String categoria) {
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
