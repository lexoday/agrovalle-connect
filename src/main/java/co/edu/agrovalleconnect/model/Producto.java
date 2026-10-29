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
import java.time.LocalDate;
import java.time.LocalDateTime;

/** Producto agrícola publicado por un productor. */
@Entity
@Table(name = "ofertas_cosechas")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_oferta")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_productor", nullable = false)
  private Productor productor;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_categoria", nullable = false)
  private Categoria categoria;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_municipio", nullable = false)
  private Municipio municipio;

  @Column(name = "nombre_producto", nullable = false, length = 100)
  private String nombre;

  @Column(length = 500)
  private String descripcion;

  @Column(name = "precio_por_kg", nullable = false, precision = 12, scale = 2)
  private BigDecimal precio;

  @Column(name = "cantidad_kg", nullable = false, precision = 10, scale = 2)
  private BigDecimal cantidadKg;

  @Column(name = "fecha_cosecha", nullable = false)
  private LocalDate fechaCosecha = LocalDate.now();

  @Column(nullable = false, length = 20)
  private String estado = "DISPONIBLE";

  @Column(name = "fecha_publicacion", nullable = false)
  private LocalDateTime fechaPublicacion = LocalDateTime.now();

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

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(final Categoria categoria) {
    this.categoria = categoria;
  }

  public Municipio getMunicipio() {
    return municipio;
  }

  public void setMunicipio(final Municipio municipio) {
    this.municipio = municipio;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(final String descripcion) {
    this.descripcion = descripcion;
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

  public LocalDate getFechaCosecha() {
    return fechaCosecha;
  }

  public void setFechaCosecha(final LocalDate fechaCosecha) {
    this.fechaCosecha = fechaCosecha;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(final String estado) {
    this.estado = estado;
  }

  public LocalDateTime getFechaPublicacion() {
    return fechaPublicacion;
  }

  public void setFechaPublicacion(final LocalDateTime fechaPublicacion) {
    this.fechaPublicacion = fechaPublicacion;
  }
}
