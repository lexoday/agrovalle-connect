package co.edu.agrovalleconnect.exception;

/** Se lanza cuando el correo ya está registrado. */
public class CorreoDuplicadoException extends RuntimeException {

  public CorreoDuplicadoException(final String mensaje) {
    super(mensaje);
  }
}
