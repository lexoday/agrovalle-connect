package co.edu.agrovalleconnect.service.strategy;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Registra las estrategias de flete por municipio como beans de Spring. */
@Configuration
public class FleteConfig {

  /** Tarifas por kilo de ejemplo; validar con el Product Owner. */
  @Bean
  public List<EstrategiaFlete> estrategiasFlete() {
    return List.of(
        new FletePorMunicipio("Dagua", new BigDecimal("50")),
        new FletePorMunicipio("Palmira", new BigDecimal("30")),
        new FletePorMunicipio("Buga", new BigDecimal("40")));
  }
}
