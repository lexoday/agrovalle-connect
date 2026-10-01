package co.edu.agrovalleconnect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Productor (agricultor) del Valle del Cauca. */
@Entity
@Table(name = "usuarios_productores")
public class Productor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(nullable = false, unique = true, length = 150)
  private String correo;

  @Column(nullable = false, unique = true, length = 20)
  private String cedula;

  @Column(nullable = false, length = 80)
  private String municipio;

  @Column(name = "password_hash", nullable = false, length = 100)
  private String passwordHash;

  @Column(name = "nombre_finca", length = 120)
  private String nombreFinca;

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

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(final String correo) {
    this.correo = correo;
  }

  public String getCedula() {
    return cedula;
  }

  public void setCedula(final String cedula) {
    this.cedula = cedula;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(final String municipio) {
    this.municipio = municipio;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(final String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public String getNombreFinca() {
    return nombreFinca;
  }

  public void setNombreFinca(final String nombreFinca) {
    this.nombreFinca = nombreFinca;
  }
}
