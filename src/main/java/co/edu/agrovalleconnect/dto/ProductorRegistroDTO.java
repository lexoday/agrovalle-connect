package co.edu.agrovalleconnect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/** Datos de entrada para registrar un productor. */
public record ProductorRegistroDTO(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "El correo es obligatorio") @Email(message = "El correo no es válido") String correo,
        @NotBlank(message = "La cédula es obligatoria") String cedula,
        @NotBlank(message = "El municipio es obligatorio") String municipio,
        @NotBlank(message = "La contraseña es obligatoria") String password,
        String nombreFinca) {
}
