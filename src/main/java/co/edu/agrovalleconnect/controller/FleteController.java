package co.edu.agrovalleconnect.controller;

import co.edu.agrovalleconnect.dto.FleteRespuestaDTO;
import co.edu.agrovalleconnect.service.CalculadorFleteService;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Cálculo de flete por municipio usando el patrón Strategy (Sprint 2). */
@RestController
@RequestMapping("/api/v1/fletes")
public class FleteController {

  private final CalculadorFleteService calculador;

  public FleteController(final CalculadorFleteService calculador) {
    this.calculador = calculador;
  }

  /** GET /api/v1/fletes?municipio=Dagua&kilos=10. */
  @GetMapping
  public ResponseEntity<FleteRespuestaDTO> calcular(
      @RequestParam final String municipio, @RequestParam final BigDecimal kilos) {
    BigDecimal flete = calculador.calcularFlete(municipio, kilos);
    return ResponseEntity.ok(new FleteRespuestaDTO(municipio, kilos, flete));
  }
}
