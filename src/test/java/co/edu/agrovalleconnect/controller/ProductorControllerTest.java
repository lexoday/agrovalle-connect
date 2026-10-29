package co.edu.agrovalleconnect.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.edu.agrovalleconnect.dto.ProductorRespuestaDTO;
import co.edu.agrovalleconnect.exception.ProductorNoEncontradoException;
import co.edu.agrovalleconnect.service.ProductorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/** Pruebas MockMvc de consulta del perfil de productor (HU-07). */
@WebMvcTest(ProductorController.class)
class ProductorControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ProductorService service;

  @Test
  @DisplayName("Dado un productor existente, cuando consulta su perfil, responde 200")
  void consultaProductorExistente() throws Exception {
    when(service.consultarPorId(7L))
        .thenReturn(new ProductorRespuestaDTO(7L, "Juan", "juan@mail.com", "Dagua", "Finca"));

    mockMvc
        .perform(get("/api/v1/productores/7"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre").value("Juan"))
        .andExpect(jsonPath("$.passwordHash").doesNotExist());
  }

  @Test
  @DisplayName("Dado un productor inexistente, cuando consulta su perfil, responde 404")
  void consultaProductorInexistente() throws Exception {
    when(service.consultarPorId(99L))
        .thenThrow(new ProductorNoEncontradoException("No existe productor con id 99"));

    mockMvc.perform(get("/api/v1/productores/99")).andExpect(status().isNotFound());
  }
}
