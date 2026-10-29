package co.edu.agrovalleconnect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Datos de entrada para registrar un productor. */
public record ProductorRegistroDTO(
        @NotBlank(message = "El nombre es obligatorio")
                @Size(max = 80, message = "El nombre no puede superar 80 caracteres")
                String nombre,
        @NotBlank(message = "El correo es obligatorio")
                @Email(message = "El correo no es válido")
                @Size(max = 120, message = "El correo no puede superar 120 caracteres")
                String correo,
        @NotBlank(message = "La cédula es obligatoria")
                @Size(max = 10, message = "La cédula no puede superar 10 caracteres")
                String cedula,
        @NotBlank(message = "El municipio es obligatorio")
                @Size(max = 80, message = "El municipio no puede superar 80 caracteres")
                String municipio,
        @NotBlank(message = "La contraseña es obligatoria") String password,
        @Size(max = 120, message = "El nombre de finca no puede superar 120 caracteres")
        String nombreFinca) {
}
