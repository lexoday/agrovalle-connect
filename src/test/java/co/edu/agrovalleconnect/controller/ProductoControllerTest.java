package co.edu.agrovalleconnect.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.edu.agrovalleconnect.dto.ProductoDTO;
import co.edu.agrovalleconnect.dto.ProductoRegistroDTO;
import co.edu.agrovalleconnect.exception.ProductorNoEncontradoException;
import co.edu.agrovalleconnect.service.ProductoService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/** Pruebas MockMvc de publicación (HU-02) y filtro de catálogo (HU-04). */
@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

  private static final String JSON_COSECHA =
      "{\"productorId\":1,\"nombre\":\"Mango\",\"categoria\":\"Frutas\","
          + "\"municipio\":\"Dagua\",\"precio\":2500,\"cantidadKg\":100}";

  @Autowired private MockMvc mockMvc;

  @MockBean private ProductoService service;

  @Test
  @DisplayName("Dada una cosecha válida, cuando se publica, entonces responde 201 con Location")
  void publicaResponde201() throws Exception {
    ProductoDTO creado =
        new ProductoDTO(1L, "Mango", "Frutas", "Dagua", new BigDecimal("2500"), BigDecimal.TEN);
    when(service.publicar(any(ProductoRegistroDTO.class))).thenReturn(creado);

    mockMvc
        .perform(post("/api/v1/productos").contentType(MediaType.APPLICATION_JSON).content(JSON_COSECHA))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/api/v1/productos/1"))
        .andExpect(jsonPath("$.nombre").value("Mango"));
  }

  @Test
  @DisplayName("Dada una cosecha sin campos obligatorios, cuando se publica, entonces responde 400")
  void cosechaInvalidaResponde400() throws Exception {
    mockMvc
        .perform(post("/api/v1/productos").contentType(MediaType.APPLICATION_JSON).content("{}"))
        .andExpect(status().isBadRequest());
    verify(service, never()).publicar(any(ProductoRegistroDTO.class));
  }

  @Test
  @DisplayName("Dado un productor inexistente, cuando publica, entonces responde 404")
  void productorInexistenteResponde404() throws Exception {
    when(service.publicar(any(ProductoRegistroDTO.class)))
        .thenThrow(new ProductorNoEncontradoException("No existe productor con id 1"));

    mockMvc
        .perform(post("/api/v1/productos").contentType(MediaType.APPLICATION_JSON).content(JSON_COSECHA))
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Dado municipio y categoría, cuando se filtra, entonces responde 200 con la lista")
  void filtraResponde200() throws Exception {
    ProductoDTO mango =
        new ProductoDTO(1L, "Mango", "Frutas", "Dagua", new BigDecimal("2500.00"), BigDecimal.TEN);
    when(service.filtrar("Dagua", "Frutas")).thenReturn(List.of(mango));

    mockMvc
        .perform(get("/api/v1/productos").param("municipio", "Dagua").param("categoria", "Frutas"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].municipio").value("Dagua"));
  }

  @Test
  @DisplayName("Dado que no hay parámetros, cuando se consulta, entonces filtra con valores nulos")
  void sinParametrosNoFiltra() throws Exception {
    when(service.filtrar(isNull(), isNull())).thenReturn(List.of());

    mockMvc.perform(get("/api/v1/productos")).andExpect(status().isOk());
    verify(service).filtrar(isNull(), isNull());
  }
}
