package co.edu.agrovalleconnect.controller;

import co.edu.agrovalleconnect.dto.ProductoDTO;
import co.edu.agrovalleconnect.dto.ProductoRegistroDTO;
import co.edu.agrovalleconnect.service.ProductoService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** HU-02 (publicar cosecha) y HU-04 (filtro de catálogo). */
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

  private final ProductoService service;

  public ProductoController(final ProductoService service) {
    this.service = service;
  }

  /** POST /api/v1/productos devuelve 201 Created con encabezado Location. */
  @PostMapping
  public ResponseEntity<ProductoDTO> publicar(@Valid @RequestBody final ProductoRegistroDTO dto) {
    ProductoDTO creado = service.publicar(dto);
    URI location = URI.create("/api/v1/productos/" + creado.id());
    return ResponseEntity.created(location).body(creado);
  }

  /** GET /api/v1/productos?municipio=Dagua&categoria=Frutas. */
  @GetMapping
  public ResponseEntity<List<ProductoDTO>> filtrar(
      @RequestParam(required = false) final String municipio,
      @RequestParam(required = false) final String categoria) {
    return ResponseEntity.ok(service.filtrar(municipio, categoria));
  }
}
