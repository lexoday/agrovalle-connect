package co.edu.agrovalleconnect.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import co.edu.agrovalleconnect.service.strategy.EstrategiaFlete;
import co.edu.agrovalleconnect.service.strategy.FleteConfig;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Pruebas del cálculo y selección de estrategias de flete. */
class CalculadorFleteServiceTest {

  @Test
  @DisplayName("Dado un municipio configurado, calcula el flete con su Strategy")
  void calculaFletePorMunicipio() {
    List<EstrategiaFlete> estrategias = new FleteConfig().estrategiasFlete();
    CalculadorFleteService service = new CalculadorFleteServiceImpl(estrategias);

    assertThat(service.calcularFlete("dagua", new BigDecimal("10")))
        .isEqualByComparingTo("500");
  }

  @Test
  @DisplayName("Dado un municipio sin tarifa, falla de forma explícita")
  void municipioSinEstrategia() {
    CalculadorFleteService service = new CalculadorFleteServiceImpl(List.of());

    assertThatThrownBy(() -> service.calcularFlete("Cali", BigDecimal.TEN))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Sin tarifa de flete para Cali");
  }

  @Test
  @DisplayName("Dada una cantidad no positiva, rechaza el cálculo del flete")
  void cantidadNoPositiva() {
    CalculadorFleteService service =
        new CalculadorFleteServiceImpl(new FleteConfig().estrategiasFlete());

    assertThatThrownBy(() -> service.calcularFlete("Dagua", BigDecimal.ZERO))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("La cantidad de kilos debe ser mayor que cero");
    assertThatThrownBy(() -> service.calcularFlete("Dagua", new BigDecimal("-1")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("La cantidad de kilos debe ser mayor que cero");
  }

  @Test
  @DisplayName("Dada una cantidad nula, rechaza el cálculo del flete")
  void cantidadNula() {
    CalculadorFleteService service =
        new CalculadorFleteServiceImpl(new FleteConfig().estrategiasFlete());

    assertThatThrownBy(() -> service.calcularFlete("Dagua", null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("La cantidad de kilos debe ser mayor que cero");
  }
}
