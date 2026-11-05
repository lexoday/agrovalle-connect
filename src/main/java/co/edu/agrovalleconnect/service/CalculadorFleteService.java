package co.edu.agrovalleconnect.service;

import java.math.BigDecimal;

/** Operación de negocio para calcular el flete de un municipio. */
public interface CalculadorFleteService {

  BigDecimal calcularFlete(String municipio, BigDecimal cantidadKg);
}
