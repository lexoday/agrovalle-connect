package co.edu.agrovalleconnect.controller;

import co.edu.agrovalleconnect.dto.ProductorRegistroDTO;
import co.edu.agrovalleconnect.dto.ProductorRespuestaDTO;
import co.edu.agrovalleconnect.service.ProductorService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** HU-01: registro de productores. */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final ProductorService service;

  public AuthController(ProductorService service) {
    this.service = service;
  }

  /** POST /api/v1/auth/register devuelve 201 Created. */
  @PostMapping("/register")
  public ResponseEntity<ProductorRespuestaDTO> registrar(
      @Valid @RequestBody ProductorRegistroDTO dto) {
    ProductorRespuestaDTO respuesta = service.registrar(dto);
    URI location = URI.create("/api/v1/productores/" + respuesta.id());
    return ResponseEntity.created(location).body(respuesta);
  }
}
