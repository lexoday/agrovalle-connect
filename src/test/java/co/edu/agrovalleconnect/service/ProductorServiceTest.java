package co.edu.agrovalleconnect.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.edu.agrovalleconnect.dto.ProductorRegistroDTO;
import co.edu.agrovalleconnect.dto.ProductorRespuestaDTO;
import co.edu.agrovalleconnect.exception.CedulaDuplicadaException;
import co.edu.agrovalleconnect.exception.CorreoDuplicadoException;
import co.edu.agrovalleconnect.exception.ProductorNoEncontradoException;
import co.edu.agrovalleconnect.model.Municipio;
import co.edu.agrovalleconnect.model.Productor;
import co.edu.agrovalleconnect.repository.MunicipioRepository;
import co.edu.agrovalleconnect.repository.ProductorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Pruebas unitarias de las reglas de negocio de productores. */
@ExtendWith(MockitoExtension.class)
class ProductorServiceTest {

  @Mock private ProductorRepository repository;
  @Mock private MunicipioRepository municipioRepository;
  @Mock private PasswordEncoder passwordEncoder;
  @InjectMocks private ProductorServiceImpl service;

  private ProductorRegistroDTO datosRegistro() {
    return new ProductorRegistroDTO(
        "Juan", "juan@mail.com", "123", "Dagua", "Secreta1", "La Esperanza");
  }

  @Test
  @DisplayName("Dado un registro válido, cuando registra, cifra la contraseña y guarda")
  void registraYCifraContrasena() {
    when(repository.existsByCedula("123")).thenReturn(false);
    when(repository.existsByCorreo("juan@mail.com")).thenReturn(false);
    Municipio municipio = new Municipio();
    municipio.setNombre("Dagua");
    when(municipioRepository.findByNombreIgnoreCase("Dagua"))
        .thenReturn(java.util.Optional.of(municipio));
    when(passwordEncoder.encode("Secreta1")).thenReturn("hash-seguro");
    when(repository.save(any(Productor.class)))
        .thenAnswer(
            invocation -> {
              Productor productor = invocation.getArgument(0);
              productor.setId(7L);
              return productor;
            });

    ProductorRespuestaDTO resultado = service.registrar(datosRegistro());

    assertThat(resultado.id()).isEqualTo(7L);
    assertThat(resultado.nombre()).isEqualTo("Juan");
    verify(passwordEncoder).encode("Secreta1");
    verify(repository).save(
        org.mockito.ArgumentMatchers.argThat(
            productor ->
                productor.getPasswordHash().equals("hash-seguro")
                    && productor.getCedula().equals("123")));
  }

  @Test
  @DisplayName("Dada una cédula existente, cuando registra, rechaza sin guardar")
  void rechazaCedulaDuplicada() {
    when(repository.existsByCedula("123")).thenReturn(true);

    assertThatThrownBy(() -> service.registrar(datosRegistro()))
        .isInstanceOf(CedulaDuplicadaException.class);

    verify(repository, never()).existsByCorreo("juan@mail.com");
    verify(repository, never()).save(any(Productor.class));
  }

  @Test
  @DisplayName("Dado un correo existente, cuando registra, rechaza sin guardar")
  void rechazaCorreoDuplicado() {
    when(repository.existsByCedula("123")).thenReturn(false);
    when(repository.existsByCorreo("juan@mail.com")).thenReturn(true);

    assertThatThrownBy(() -> service.registrar(datosRegistro()))
        .isInstanceOf(CorreoDuplicadoException.class);

    verify(repository, never()).save(any(Productor.class));
  }

  @Test
  @DisplayName("Dado un municipio no registrado, cuando registra, rechaza el productor")
  void rechazaMunicipioInexistente() {
    when(repository.existsByCedula("123")).thenReturn(false);
    when(repository.existsByCorreo("juan@mail.com")).thenReturn(false);
    when(municipioRepository.findByNombreIgnoreCase("Dagua"))
        .thenReturn(java.util.Optional.empty());

    assertThatThrownBy(() -> service.registrar(datosRegistro()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("No existe el municipio Dagua");

    verify(repository, never()).save(any(Productor.class));
  }

  @Test
  @DisplayName("Dado un productor existente, cuando consulta, devuelve sus datos públicos")
  void consultaProductorExistente() {
    Productor productor = new Productor();
    productor.setId(7L);
    productor.setNombre("Juan");
    productor.setCorreo("juan@mail.com");
    Municipio municipio = new Municipio();
    municipio.setNombre("Dagua");
    productor.setMunicipio(municipio);
    productor.setNombreFinca("La Esperanza");
    when(repository.findById(7L)).thenReturn(java.util.Optional.of(productor));

    ProductorRespuestaDTO resultado = service.consultarPorId(7L);

    assertThat(resultado.nombre()).isEqualTo("Juan");
    assertThat(resultado.nombreFinca()).isEqualTo("La Esperanza");
  }

  @Test
  @DisplayName("Dado un ID inexistente, cuando consulta, lanza error de dominio")
  void consultaProductorInexistente() {
    when(repository.findById(99L)).thenReturn(java.util.Optional.empty());

    assertThatThrownBy(() -> service.consultarPorId(99L))
        .isInstanceOf(ProductorNoEncontradoException.class)
        .hasMessage("No existe productor con id 99");
  }
}
