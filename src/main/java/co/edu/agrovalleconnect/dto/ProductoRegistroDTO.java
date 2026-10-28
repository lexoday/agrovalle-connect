package co.edu.agrovalleconnect.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/** Datos de entrada para publicar una cosecha (HU-02). */
public record ProductoRegistroDTO(
    @NotNull(message = "El productor es obligatorio") Long productorId,
    @NotBlank(message = "El nombre del producto es obligatorio") String nombre,
    @NotBlank(message = "La categoría es obligatoria") String categoria,
    @NotBlank(message = "El municipio es obligatorio") String municipio,
    @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
        BigDecimal precio,
    @NotNull(message = "La cantidad es obligatoria")
        @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor que cero")
        BigDecimal cantidadKg) {
}
