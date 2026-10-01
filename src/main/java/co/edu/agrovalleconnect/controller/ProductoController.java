package co.edu.agrovalleconnect.controller;

import co.edu.agrovalleconnect.dto.ProductoDTO;
import co.edu.agrovalleconnect.service.ProductoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** HU-04: filtro de productos por municipio y categoría. */
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

  private final ProductoService service;

  public ProductoController(final ProductoService service) {
    this.service = service;
  }

  /** GET /api/v1/productos?municipio=Dagua&categoria=Frutas. */
  @GetMapping
  public ResponseEntity<List<ProductoDTO>> filtrar(
      @RequestParam(required = false) final String municipio,
      @RequestParam(required = false) final String categoria) {
    return ResponseEntity.ok(service.filtrar(municipio, categoria));
  }
}
