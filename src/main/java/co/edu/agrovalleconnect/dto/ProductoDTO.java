package co.edu.agrovalleconnect.dto;

import co.edu.agrovalleconnect.model.Producto;
import java.math.BigDecimal;

/** Datos de salida de un producto. */
public record ProductoDTO(
    Long id,
    String nombre,
    String categoria,
    String municipio,
    BigDecimal precio,
    BigDecimal cantidadKg) {

  /** Construye el DTO a partir de la entidad. */
  public static ProductoDTO desde(final Producto p) {
    return new ProductoDTO(
        p.getId(), p.getNombre(), p.getCategoria(), p.getMunicipio(),
        p.getPrecio(), p.getCantidadKg());
  }
}
