package co.edu.agrovalleconnect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Municipio del Valle del Cauca asociado a productores y ofertas. */
@Entity
@Table(name = "municipios")
public class Municipio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_municipio")
  private Long id;

  @Column(nullable = false, unique = true, length = 80)
  private String nombre;

  @Column(nullable = false, length = 60)
  private String departamento;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(final String departamento) {
    this.departamento = departamento;
  }
}
