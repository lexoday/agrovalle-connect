# Sprint 1 Planning

# 1. Información del Sprint

* **Sprint:** Sprint 1
* **Duración:** 24/09/2026 – 30/09/2026
* **Capacidad:** 10 Story Points
* **Equipo:** Scrum Team AgroValle Connect

# 2. Sprint Goal

Permitir que los agricultores del Valle del Cauca puedan registrarse por primera vez en la plataforma y que cualquier persona pueda buscar productos en el catálogo usando filtros, asegurando que los datos queden bien guardados en la base de datos (PostgreSQL) y que la aplicación se comunique correctamente a través de Internet (arquitectura REST).

# 3. Historias de Usuario seleccionadas

| ID        | Historia de Usuario                           | Prioridad | Story Points |
| --------- | --------------------------------------------- | --------- | -----------: |
| HU-01     | Registro de agricultores                      | Must Have |            5 |
| HU-07     | Consulta de perfil de agricultor              | Must Have |            2 |
| HU-04     | Filtro por municipio y categoría              | Must Have |            3 |
| **Total** |                                               |           |    **10 SP** |

# Justificación de selección

Las tres historias fueron seleccionadas para cumplir el objetivo del Sprint y se encuentran dentro de la capacidad establecida de 10 Story Points.

* **HU-01:** permite habilitar el registro inicial de agricultores.
* **HU-07:** permite consultar la información de un agricultor mediante su identificador.
* **HU-04:** permite consultar el catálogo agrícola aplicando filtros por municipio y categoría.

# HU-01 — Registro de agricultores (5 SP)

| Sub-issue | Tarea técnica                                                      | Tecnología                                           | ISO/IEC 25010                     | Estimación |
| --------- | ------------------------------------------------------------------ | ---------------------------------------------------- | --------------------------------- | ---------: |
| #26       | Crear formulario de registro del agricultor                        | Java 17, Spring Boot, Spring Web, Bean Validation    | Adecuación funcional              |      ≤ 8 h |
| #27       | Validar correo no duplicado                                        | Java 17, Spring Data JPA, PostgreSQL                 | Adecuación funcional              |      ≤ 8 h |
| #28       | Almacenar datos en PostgreSQL                                      | Java 17, Spring Boot, Spring Data JPA, PostgreSQL    | Mantenibilidad                    |      ≤ 8 h |
| #29       | Hashear contraseña                                                 | Java 17, Spring Boot, Spring Security                | Seguridad                         |      ≤ 8 h |
| #30       | Implementar respuesta 201 Created                                  | Java 17, Spring Boot, Spring Web, REST               | Adecuación funcional              |      ≤ 8 h |
| #31       | Traducción BDD a pruebas automatizadas del registro del agricultor | Java 17, JUnit 5, Spring Boot Test, MockMvc, Mockito | Fiabilidad y adecuación funcional |      ≤ 8 h |
| #40       | Revisar código con Checkstyle                                      | Java 17, Maven, Checkstyle                           | Mantenibilidad                    |      ≤ 8 h |


# HU-07 — Consulta del perfil del agricultor (2 SP)

| Sub-issue | Tarea técnica                                             | Tecnología                                           | ISO/IEC 25010                     | Estimación |
| --------- | --------------------------------------------------------- | ---------------------------------------------------- | --------------------------------- | ---------: |
| #32       | Implementar consulta de agricultor por ID                 | Java 17, Spring Boot, Spring Data JPA, PostgreSQL    | Adecuación funcional              |      ≤ 8 h |
| #33       | Gestionar agricultor inexistente                          | Java 17, Spring Boot, Spring Data JPA                | Fiabilidad                        |      ≤ 8 h |
| #34       | Proteger datos sensibles                                  | Java 17, Spring Boot, DTO                            | Seguridad                         |      ≤ 8 h |
| #43       | Implementar endpoint REST de consulta                     | Java 17, Spring Boot, Spring Web, REST               | Adecuación funcional              |      ≤ 8 h |
| #44       | Implementar pruebas automatizadas de consulta con JUnit 5 | Java 17, JUnit 5, Spring Boot Test, MockMvc, Mockito | Fiabilidad y adecuación funcional |      ≤ 8 h |


# HU-04 — Filtro del catálogo por municipio y categoría (3 SP)

| Sub-issue | Tarea técnica                                          | Tecnología                                           | ISO/IEC 25010                     | Estimación |
| --------- | ------------------------------------------------------ | ---------------------------------------------------- | --------------------------------- | ---------: |
| #35       | Filtrar cosechas por municipio y categoría             | Java 17, Spring Boot, Spring Data JPA, PostgreSQL    | Adecuación funcional              |      ≤ 8 h |
| #36       | Consultar catálogo sin filtros                         | Java 17, Spring Boot, Spring Data JPA                | Adecuación funcional              |      ≤ 8 h |
| #37       | Gestionar búsquedas sin coincidencias                  | Java 17, Spring Boot, Spring Data JPA                | Fiabilidad                        |      ≤ 8 h |
| #41       | Implementar pruebas automatizadas de HU-04 con JUnit 5 | Java 17, JUnit 5, Spring Boot Test, MockMvc, Mockito | Fiabilidad y adecuación funcional |      ≤ 8 h |
| #42       | Verificar código de HU-04 con Checkstyle               | Java 17, Maven, Checkstyle                           | Mantenibilidad                    |      ≤ 8 h |


# 5. Escenarios BDD y criterios de calidad

## HU-01 — Registro de agricultores

**Escenario 1 — Registro exitoso**

- **Given:** un agricultor proporciona sus datos personales y de finca válidos.
- **When:** realiza el registro.
- **Then:** el sistema almacena la información y confirma el registro correctamente.

**Escenario 2 — Correo duplicado**

- **Given:** el correo electrónico ya está registrado.
- **When:** el agricultor intenta registrarse nuevamente con ese correo.
- **Then:** el sistema rechaza el registro e informa que el correo ya existe.

**Escenario 3 — Contraseña protegida**

- **Given:** el agricultor proporciona una contraseña.
- **When:** se almacena su información.
- **Then:** la contraseña se almacena utilizando un mecanismo seguro de hash.

## HU-07 — Consulta de perfil de agricultor

**Escenario 1 — Agricultor existente**

- **Given:** existe un agricultor con el ID solicitado.
- **When:** se consulta su información.
- **Then:** el sistema devuelve los datos permitidos del agricultor.

**Escenario 2 — Agricultor inexistente**

- **Given:** no existe un agricultor con el ID solicitado.
- **When:** se realiza la consulta.
- **Then:** el sistema informa que el agricultor no existe.

**Escenario 3 — Protección de datos**

- **Given:** existe un agricultor.
- **When:** se consulta su información.
- **Then:** la contraseña y otros datos sensibles no se incluyen en la respuesta.

## HU-04 — Filtro por municipio y categoría

**Escenario 1 — Filtro aplicado**

- **Given:** existen cosechas registradas en diferentes municipios y categorías.
- **When:** el usuario filtra por municipio y categoría.
- **Then:** el sistema muestra únicamente las cosechas que coinciden con ambos criterios.

**Escenario 2 — Sin filtros**

- **Given:** existen cosechas registradas.
- **When:** el usuario consulta el catálogo sin filtros.
- **Then:** el sistema muestra el catálogo completo.

**Escenario 3 — Sin coincidencias**

- **Given:** no existen cosechas que coincidan con los filtros seleccionados.
- **When:** el usuario realiza la búsqueda.
- **Then:** el sistema devuelve un catálogo vacío sin generar un error.

## Criterios de calidad

Las pruebas automatizadas del Sprint validarán principalmente:

- **Adecuación funcional:** las funcionalidades cumplen los comportamientos definidos.
- **Fiabilidad:** el sistema maneja correctamente casos válidos y situaciones sin resultados.
- **Seguridad:** la información sensible, especialmente las contraseñas, no se expone.
- **Mantenibilidad:** el código cumple las reglas definidas mediante Checkstyle.


