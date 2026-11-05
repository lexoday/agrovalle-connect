package co.edu.agrovalleconnect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/** Productor (agricultor) del Valle del Cauca. */
@Entity
@Table(name = "usuarios_productores")
public class Productor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_productor")
  private Long id;

  @Column(nullable = false, length = 80)
  private String nombres;

  @Column(nullable = false, length = 80)
  private String apellidos = "";

  @Column(length = 15)
  private String telefono;

  @Column(nullable = false, unique = true, length = 120)
  private String correo;

  @Column(nullable = false, unique = true, length = 10)
  private String cedula;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_municipio", nullable = false)
  private Municipio municipio;

  @Column(name = "password_hash", nullable = false, length = 100)
  private String passwordHash;

  @Column(name = "nombre_finca", length = 120)
  private String nombreFinca;

  @Column(name = "fecha_registro", nullable = false)
  private LocalDateTime fechaRegistro = LocalDateTime.now();

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombres + (apellidos.isBlank() ? "" : " " + apellidos);
  }

  public void setNombre(final String nombre) {
    this.nombres = nombre;
    this.apellidos = "";
  }

  public String getNombres() {
    return nombres;
  }

  public void setNombres(final String nombres) {
    this.nombres = nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(final String apellidos) {
    this.apellidos = apellidos;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(final String telefono) {
    this.telefono = telefono;
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

  public Municipio getMunicipio() {
    return municipio;
  }

  public void setMunicipio(final Municipio municipio) {
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

  public LocalDateTime getFechaRegistro() {
    return fechaRegistro;
  }

  public void setFechaRegistro(final LocalDateTime fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }
}
