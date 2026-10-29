package co.edu.agrovalleconnect.service;

import co.edu.agrovalleconnect.dto.ProductoDTO;
import co.edu.agrovalleconnect.dto.ProductoRegistroDTO;
import co.edu.agrovalleconnect.exception.ProductorNoEncontradoException;
import co.edu.agrovalleconnect.model.Categoria;
import co.edu.agrovalleconnect.model.Municipio;
import co.edu.agrovalleconnect.model.Producto;
import co.edu.agrovalleconnect.model.Productor;
import co.edu.agrovalleconnect.repository.CategoriaRepository;
import co.edu.agrovalleconnect.repository.MunicipioRepository;
import co.edu.agrovalleconnect.repository.ProductoRepository;
import co.edu.agrovalleconnect.repository.ProductorRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Lógica del catálogo agrícola: publicación de cosechas (HU-02) y filtros (HU-04). */
@Service
@Transactional(readOnly = true)
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository repository;
  private final ProductorRepository productorRepository;
  private final CategoriaRepository categoriaRepository;
  private final MunicipioRepository municipioRepository;

  public ProductoServiceImpl(
      final ProductoRepository repository,
      final ProductorRepository productorRepository,
      final CategoriaRepository categoriaRepository,
      final MunicipioRepository municipioRepository) {
    this.repository = repository;
    this.productorRepository = productorRepository;
    this.categoriaRepository = categoriaRepository;
    this.municipioRepository = municipioRepository;
  }

  /** Publica una cosecha asociada a un productor existente. */
  @Transactional
  public ProductoDTO publicar(final ProductoRegistroDTO dto) {
    Productor productor =
        productorRepository
            .findById(dto.productorId())
            .orElseThrow(
                () ->
                    new ProductorNoEncontradoException(
                        "No existe productor con id " + dto.productorId()));
    Categoria categoria =
        categoriaRepository
            .findByNombreIgnoreCase(dto.categoria())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "No existe la categoría " + dto.categoria()));
    Municipio municipio =
        municipioRepository
            .findByNombreIgnoreCase(dto.municipio())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "No existe el municipio " + dto.municipio()));
    Producto p = new Producto();
    p.setProductor(productor);
    p.setNombre(dto.nombre());
    p.setCategoria(categoria);
    p.setMunicipio(municipio);
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
          repository.findByMunicipioNombreIgnoreCaseAndCategoriaNombreIgnoreCase(
              municipio, categoria);
    } else if (hayMunicipio) {
      productos = repository.findByMunicipioNombreIgnoreCase(municipio);
    } else if (hayCategoria) {
      productos = repository.findByCategoriaNombreIgnoreCase(categoria);
    } else {
      productos = repository.findAll();
    }
    return productos.stream().map(ProductoDTO::desde).toList();
  }
}
