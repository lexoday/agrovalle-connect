package co.edu.agrovalleconnect.service.strategy;

import java.math.BigDecimal;

/**
 * Flete basado en una tarifa por kilo y un municipio de origen.
 * Las tarifas son valores de ejemplo a validar con el Product Owner.
 */
public class FletePorMunicipio implements EstrategiaFlete {

  private final String municipio;
  private final BigDecimal tarifaPorKg;

  /** Crea la estrategia para un municipio con su tarifa por kilo. */
  public FletePorMunicipio(final String municipio, final BigDecimal tarifaPorKg) {
    this.municipio = municipio;
    this.tarifaPorKg = tarifaPorKg;
  }

  @Override
  public boolean aplicaA(final String municipioConsultado) {
    return municipio.equalsIgnoreCase(municipioConsultado);
  }

  @Override
  public BigDecimal calcular(final BigDecimal cantidadKg) {
    return tarifaPorKg.multiply(cantidadKg);
  }
}
