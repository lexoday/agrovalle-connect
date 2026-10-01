package co.edu.agrovalleconnect.exception;

/** Se lanza cuando no existe el productor consultado. */
public class ProductorNoEncontradoException extends RuntimeException {

  public ProductorNoEncontradoException(String mensaje) {
    super(mensaje);
  }
}
