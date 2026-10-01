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
import java.math.BigDecimal;

/** Producto agrícola publicado por un productor. */
@Entity
@Table(name = "productos")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "productor_id", nullable = false)
  private Productor productor;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(nullable = false, length = 50)
  private String categoria;

  @Column(nullable = false, length = 80)
  private String municipio;

  @Column(nullable = false, precision = 12, scale = 2)
  private BigDecimal precio;

  @Column(name = "cantidad_kg", nullable = false, precision = 10, scale = 2)
  private BigDecimal cantidadKg;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Productor getProductor() {
    return productor;
  }

  public void setProductor(final Productor productor) {
    this.productor = productor;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(final String categoria) {
    this.categoria = categoria;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(final String municipio) {
    this.municipio = municipio;
  }

  public BigDecimal getPrecio() {
    return precio;
  }

  public void setPrecio(final BigDecimal precio) {
    this.precio = precio;
  }

  public BigDecimal getCantidadKg() {
    return cantidadKg;
  }

  public void setCantidadKg(final BigDecimal cantidadKg) {
    this.cantidadKg = cantidadKg;
  }
}
