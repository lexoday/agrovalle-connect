# Planificación del Sprint 2

## Objetivo

Permitir que un productor publique una cosecha asociada a su perfil y que el equipo entregue la funcionalidad con validaciones, persistencia, pruebas automatizadas y una arquitectura mantenible. La planificación incorpora además el patrón Strategy para el cálculo de fletes por municipio y los criterios técnicos del Sprint 2.

## Historia de usuario

| ID | Historia | Referencia | Estimación |
|---|---|---|---|
| HU-02 | Como productor, quiero publicar una cosecha con sus datos y municipio para ofrecerla en el catálogo. | Issue [#14](https://github.com/lexoday/agrovalle-connect/issues/14) | Acordar durante la sesión de Planning |

La capacidad, las fechas exactas y la asignación de responsables se acuerdan con el equipo en la sesión de Planning; no se infieren del repositorio.

## Alcance técnico propuesto

| Resultado | Criterio de aceptación |
|---|---|
| Publicación REST | `POST /api/v1/productos` valida los datos, asocia un productor existente y responde `201 Created` con `Location`. |
| Validación y errores | Los campos obligatorios y valores positivos se validan; si el productor no existe, la API responde `404`. |
| Catálogo | `GET /api/v1/productos` permite filtrar por municipio, categoría, ambos o ninguno y devuelve una lista vacía si no hay coincidencias. |
| Diseño desacoplado | Servicios consumidos por interfaces, inyección por constructor y repositorios limitados a acceso a datos. |
| Patrón comportamental GoF | Strategy selecciona una tarifa por municipio sin acoplar el cálculo a una tabla de decisiones dentro del controlador. |
| Calidad | Pruebas unitarias con Mockito y pruebas de endpoints con MockMvc; `mvn clean verify` cumple Checkstyle y el umbral JaCoCo de 60% en instrucciones y ramas. |
| Integración continua | El job `build` valida pushes a `main`/`develop` y pull requests dirigidos a ambas ramas. |

## Descomposición inicial del trabajo

1. Acordar capacidad, responsables y criterios de aceptación con el Product Owner.
2. Completar la publicación de cosechas y la validación de sus datos.
3. Implementar y probar el cálculo de flete mediante estrategias por municipio.
4. Mantener las dependencias entre controladores, servicios y repositorios desacopladas por interfaces.
5. Cubrir caminos exitosos y de error con Mockito y MockMvc.
6. Ejecutar `mvn clean verify`, corregir hallazgos y revisar el reporte JaCoCo.
7. Integrar mediante pull request y verificar el check `build` en GitHub Actions.

## Definition of Done

- Los criterios de aceptación acordados para HU-02 están implementados.
- Los errores de validación y negocio tienen respuestas HTTP explícitas.
- Las pruebas y Checkstyle pasan; JaCoCo alcanza al menos 60% en instrucciones y ramas.
- El pull request a `develop` tiene el check `build` verde y revisión del equipo.
- La documentación técnica y las evidencias del Sprint Review y la retrospectiva reflejan lo ocurrido realmente.

## Flujo de trabajo

Se limita el trabajo simultáneo a tres tarjetas en `In Progress` y dos en `Code Review`, de acuerdo con los límites WIP definidos para el tablero del Sprint.
