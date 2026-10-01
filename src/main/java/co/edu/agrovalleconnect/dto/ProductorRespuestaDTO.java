package co.edu.agrovalleconnect.dto;

import co.edu.agrovalleconnect.model.Productor;

/** Datos de salida de un productor (sin contraseña). */
public record ProductorRespuestaDTO(
    Long id, String nombre, String correo, String municipio, String nombreFinca) {

  /** Construye el DTO a partir de la entidad. */
  public static ProductorRespuestaDTO desde(Productor p) {
    return new ProductorRespuestaDTO(
        p.getId(), p.getNombre(), p.getCorreo(), p.getMunicipio(), p.getNombreFinca());
  }
}
