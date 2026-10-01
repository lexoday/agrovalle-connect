package co.edu.agrovalleconnect.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Manejo centralizado de errores de la API. */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /** Correo repetido: 409. */
  @ExceptionHandler(CorreoDuplicadoException.class)
  public ResponseEntity<Map<String, String>> handleCorreo(CorreoDuplicadoException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
  }

  /** Cédula repetida: 409. */
  @ExceptionHandler(CedulaDuplicadaException.class)
  public ResponseEntity<Map<String, String>> handleCedula(CedulaDuplicadaException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
  }

  /** Productor inexistente: 404. */
  @ExceptionHandler(ProductorNoEncontradoException.class)
  public ResponseEntity<Map<String, String>> handleNoEncontrado(
      ProductorNoEncontradoException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
  }

  /** Validación de campos: 400. */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidacion(
      MethodArgumentNotValidException ex) {
    String mensaje = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
    return ResponseEntity.badRequest().body(Map.of("error", mensaje));
  }
}
