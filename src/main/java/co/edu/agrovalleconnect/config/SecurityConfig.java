package co.edu.agrovalleconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Configuración del codificador de contraseñas. */
@Configuration
public class SecurityConfig {

  /** Bean BCrypt para cifrar contraseñas. */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
