package co.edu.agrovalleconnect.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.edu.agrovalleconnect.dto.ProductoDTO;
import co.edu.agrovalleconnect.dto.ProductoRegistroDTO;
import co.edu.agrovalleconnect.exception.ProductorNoEncontradoException;
import co.edu.agrovalleconnect.model.Producto;
import co.edu.agrovalleconnect.model.Productor;
import co.edu.agrovalleconnect.repository.ProductoRepository;
import co.edu.agrovalleconnect.repository.ProductorRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Pruebas unitarias de publicación (HU-02) y filtro de catálogo (HU-04). */
@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

  @Mock private ProductoRepository repository;
  @Mock private ProductorRepository productorRepository;
  @InjectMocks private ProductoService service;

  private Producto producto(String nombre) {
    Producto p = new Producto();
    p.setNombre(nombre);
    p.setCategoria("Frutas");
    p.setMunicipio("Dagua");
    p.setPrecio(new BigDecimal("2500.00"));
    p.setCantidadKg(new BigDecimal("100.00"));
    return p;
  }

  private ProductoRegistroDTO datosCosecha() {
    return new ProductoRegistroDTO(
        1L, "Mango", "Frutas", "Dagua", new BigDecimal("2500.00"), new BigDecimal("100.00"));
  }

  @Test
  @DisplayName("Dado un productor existente, cuando publica una cosecha, entonces la guarda")
  void publicaCosechaDeProductorExistente() {
    Productor productor = new Productor();
    productor.setId(1L);
    when(productorRepository.findById(1L)).thenReturn(Optional.of(productor));
    when(repository.save(any(Producto.class))).thenAnswer(inv -> inv.getArgument(0));

    ProductoDTO resultado = service.publicar(datosCosecha());

    assertThat(resultado.nombre()).isEqualTo("Mango");
    assertThat(resultado.municipio()).isEqualTo("Dagua");
    verify(repository).save(any(Producto.class));
  }

  @Test
  @DisplayName("Dado un productor inexistente, cuando publica, entonces lanza excepción sin guardar")
  void rechazaCosechaDeProductorInexistente() {
    when(productorRepository.findById(1L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> service.publicar(datosCosecha()))
        .isInstanceOf(ProductorNoEncontradoException.class);
    verify(repository, never()).save(any(Producto.class));
  }

  @Test
  @DisplayName("Dados municipio y categoría, cuando se filtra, entonces usa la consulta combinada")
  void filtraPorMunicipioYCategoria() {
    when(repository.findByMunicipioIgnoreCaseAndCategoriaIgnoreCase("Dagua", "Frutas"))
        .thenReturn(List.of(producto("Mango")));

    List<ProductoDTO> resultado = service.filtrar("Dagua", "Frutas");

    assertThat(resultado).hasSize(1);
    assertThat(resultado.get(0).nombre()).isEqualTo("Mango");
  }

  @Test
  @DisplayName("Dado solo municipio, cuando se filtra, entonces consulta por municipio")
  void filtraSoloMunicipio() {
    when(repository.findByMunicipioIgnoreCase("Dagua")).thenReturn(List.of(producto("Mango")));

    assertThat(service.filtrar("Dagua", null)).hasSize(1);
  }

  @Test
  @DisplayName("Dado solo categoría, cuando se filtra, entonces consulta por categoría")
  void filtraSoloCategoria() {
    when(repository.findByCategoriaIgnoreCase("Frutas")).thenReturn(List.of(producto("Mango")));

    assertThat(service.filtrar(null, "Frutas")).hasSize(1);
  }

  @Test
  @DisplayName("Dados valores en blanco, cuando se filtra, entonces equivalen a sin filtro")
  void valoresEnBlancoEquivalenASinFiltro() {
    when(repository.findAll()).thenReturn(List.of(producto("Mango"), producto("Piña")));

    assertThat(service.filtrar("   ", "")).hasSize(2);
  }

  @Test
  @DisplayName("Dado un municipio sin productos, cuando se filtra, entonces devuelve lista vacía")
  void sinResultados() {
    when(repository.findByMunicipioIgnoreCase(anyString())).thenReturn(List.of());

    assertThat(service.filtrar("Buga", null)).isEmpty();
  }
}
