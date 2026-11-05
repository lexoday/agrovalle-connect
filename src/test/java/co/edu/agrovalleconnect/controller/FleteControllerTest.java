package co.edu.agrovalleconnect.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.edu.agrovalleconnect.service.CalculadorFleteService;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/** Pruebas MockMvc del endpoint de fletes (patrón Strategy). */
@WebMvcTest(FleteController.class)
class FleteControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private CalculadorFleteService calculador;

  @Test
  @DisplayName("Dado un municipio con tarifa, cuando se calcula, entonces responde 200 con el flete")
  void calculaResponde200() throws Exception {
    when(calculador.calcularFlete("Dagua", new BigDecimal("10"))).thenReturn(new BigDecimal("500"));

    mockMvc
        .perform(get("/api/v1/fletes").param("municipio", "Dagua").param("kilos", "10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.flete").value(500));
  }

  @Test
  @DisplayName("Dado un municipio sin tarifa, cuando se calcula, entonces responde 400")
  void municipioSinTarifaResponde400() throws Exception {
    when(calculador.calcularFlete("Cali", new BigDecimal("10")))
        .thenThrow(new IllegalArgumentException("Sin tarifa de flete para Cali"));

    mockMvc
        .perform(get("/api/v1/fletes").param("municipio", "Cali").param("kilos", "10"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Dado que falta el parámetro kilos, cuando se calcula, entonces responde 400")
  void faltaParametroResponde400() throws Exception {
    mockMvc
        .perform(get("/api/v1/fletes").param("municipio", "Dagua"))
        .andExpect(status().isBadRequest());
  }
}
