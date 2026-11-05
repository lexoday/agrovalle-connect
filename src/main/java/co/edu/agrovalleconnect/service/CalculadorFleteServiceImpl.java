package co.edu.agrovalleconnect.service;

import co.edu.agrovalleconnect.service.strategy.EstrategiaFlete;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/** Selecciona la estrategia de flete según el municipio (OCP y DIP). */
@Service
public class CalculadorFleteServiceImpl implements CalculadorFleteService {

  private final List<EstrategiaFlete> estrategias;

  public CalculadorFleteServiceImpl(final List<EstrategiaFlete> estrategias) {
    this.estrategias = estrategias;
  }

  /** Calcula el flete; lanza excepción si ninguna estrategia atiende el municipio. */
  public BigDecimal calcularFlete(final String municipio, final BigDecimal cantidadKg) {
    if (cantidadKg == null || cantidadKg.signum() <= 0) {
      throw new IllegalArgumentException("La cantidad de kilos debe ser mayor que cero");
    }
    return estrategias.stream()
        .filter(e -> e.aplicaA(municipio))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Sin tarifa de flete para " + municipio))
        .calcular(cantidadKg);
  }
}
