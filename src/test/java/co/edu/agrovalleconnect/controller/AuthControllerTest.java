package co.edu.agrovalleconnect.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.edu.agrovalleconnect.dto.ProductorRespuestaDTO;
import co.edu.agrovalleconnect.exception.CedulaDuplicadaException;
import co.edu.agrovalleconnect.service.ProductorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

  private static final String URL = "/api/v1/auth/register";
  private static final String JSON_OK =
      "{\"nombre\":\"Juan\",\"correo\":\"juan@mail.com\",\"cedula\":\"123\","
          + "\"municipio\":\"Dagua\",\"password\":\"Secreta1\",\"nombreFinca\":\"La Esperanza\"}";

  @Autowired private MockMvc mockMvc;

  @MockBean private ProductorService service;

  @Test
  @DisplayName("Dado datos válidos, cuando se registra, entonces responde 201")
  void registroExitoso() throws Exception {
    when(service.registrar(any()))
        .thenReturn(
            new ProductorRespuestaDTO(1L, "Juan", "juan@mail.com", "Dagua", "La Esperanza"));

    mockMvc
        .perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(JSON_OK))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1));
  }

  @Test
  @DisplayName("Dada una cédula repetida, cuando se registra, entonces responde 409")
  void cedulaDuplicada() throws Exception {
    when(service.registrar(any()))
        .thenThrow(new CedulaDuplicadaException("La cédula ya está registrada"));

    mockMvc
        .perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(JSON_OK))
        .andExpect(status().isConflict());
  }

  @Test
  @DisplayName("Dado un campo obligatorio vacío, cuando se registra, entonces responde 400")
  void campoVacio() throws Exception {
    mockMvc
        .perform(post(URL).contentType(MediaType.APPLICATION_JSON).content("{}"))
        .andExpect(status().isBadRequest());
  }
}
