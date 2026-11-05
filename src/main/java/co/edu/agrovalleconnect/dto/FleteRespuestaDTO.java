package co.edu.agrovalleconnect.dto;

import java.math.BigDecimal;

/** Respuesta del cálculo de flete por municipio (Strategy). */
public record FleteRespuestaDTO(String municipio, BigDecimal cantidadKg, BigDecimal flete) {
}
