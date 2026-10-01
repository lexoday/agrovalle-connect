package co.edu.agrovalleconnect.exception;

/** Se lanza cuando la cédula ya está registrada. */
public class CedulaDuplicadaException extends RuntimeException {

  public CedulaDuplicadaException(String mensaje) {
    super(mensaje);
  }
}
