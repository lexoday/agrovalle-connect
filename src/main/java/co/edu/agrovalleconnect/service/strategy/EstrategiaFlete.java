package co.edu.agrovalleconnect.service.strategy;

import java.math.BigDecimal;

/** Estrategia de cálculo de flete por municipio (patrón Strategy, comportamiento). */
public interface EstrategiaFlete {

  /** Indica si esta estrategia aplica al municipio indicado. */
  boolean aplicaA(String municipio);

  /** Calcula el valor del flete para la cantidad de kilos indicada. */
  BigDecimal calcular(BigDecimal cantidadKg);
}
