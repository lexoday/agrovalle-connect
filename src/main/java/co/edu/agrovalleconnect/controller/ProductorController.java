package co.edu.agrovalleconnect.controller;

import co.edu.agrovalleconnect.dto.ProductorRespuestaDTO;
import co.edu.agrovalleconnect.service.ProductorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** HU-07: consulta de perfil de productor. */
@RestController
@RequestMapping("/api/v1/productores")
public class ProductorController {

  private final ProductorService service;

  public ProductorController(final ProductorService service) {
    this.service = service;
  }

  /** GET /api/v1/productores/{id}. */
  @GetMapping("/{id}")
  public ResponseEntity<ProductorRespuestaDTO> consultar(@PathVariable final Long id) {
    return ResponseEntity.ok(service.consultarPorId(id));
  }
}
