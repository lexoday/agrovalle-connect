# Product Backlog — AgroValle Connect

Priorización: **M** = Must Have · **S** = Should Have · **C** = Could Have · **W** = Won't Have
Estimación: Story Points (escala de Fibonacci) mediante Planning Poker.

## Tabla resumen

| ID | Historia | Módulo | Prioridad | Story Points | Sprint |
|----|----------|--------|-----------|---------------|--------|
| HU-01 | Registro de Agricultores | Autenticación / Registro | M | 5 | Sprint 1 |
| HU-02 | Publicación de Cosechas | Ofertas / Inventario | M | 5 | Sprint 1 |
| HU-03 | Visualización de Precios Promedio | Inteligencia de Mercado | S | 8 | Por refinar |
| HU-04 | Filtro por Municipio y Categoría | Catálogo / Búsqueda | M | 3 | Sprint 1 |
| HU-05 | Contacto Directo / Mensajería | Comunicación | S | 5 | Backlog |
| HU-06 | Autenticación e Inicio de Sesión (JWT) | Seguridad | M | 5 | Sprint 2 |
| HU-07 | Consulta de Perfil de Agricultor | Usuarios / Perfil | M | 2 | Sprint 1 |
| HU-08 | Edición / Cancelación de Cosecha | Ofertas | S | 3 | Sprint 2 |
| HU-09 | Registro de Comprador Urbano | Autenticación / Registro | M | 3 | Sprint 1 |
| HU-10 | Carrito y Consolidación de Pedido | Pedidos | M | 5 | Sprint 2 |
| HU-11 | Confirmación y Reserva de Stock | Pedidos / Inventario | S | 5 | Sprint 2 |
| HU-12 | Aceptación / Rechazo de Pedido | Logística | S | 3 | Backlog |
| HU-13 | Trazabilidad y Estado de Despacho | Logística | S | 5 | Backlog |
| HU-14 | Calificación y Reseña de Cosecha | Reputación | C | 3 | Backlog |
| HU-15 | Reporte de Ventas por Municipio | Analítica / Dashboard | C | 8 | Backlog |

**Total Story Points:** 68

---

## Detalle de Historias de Usuario

### HU-01: Registro de Agricultores
**Historia:** Como Agricultor, quiero registrarme en la plataforma con mis datos personales y de finca para poder publicar mis cosechas.

**Por qué entra en el Sprint 1:** Es la puerta de entrada. Enseña a crear la tabla en PostgreSQL, la `@Entity`, el `@Repository`, el `@Service` y el `@RestController`.

**Detalles técnicos:**
- Endpoint: `POST /api/v1/auth/register`
- Respuesta esperada: `201 Created`

**Criterios de aceptación:**
- [ ] El sistema valida que el correo no esté duplicado
- [ ] Se almacenan correctamente los datos en PostgreSQL
- [ ] La contraseña se guarda cifrada
- [ ] Retorna `201 Created` con los datos del agricultor registrado

**Prioridad MoSCoW:** Must Have · **Story Points:** 5 · **Módulo:** Autenticación / Registro

---

### HU-02: Publicación de Cosechas
**Historia:** Como Agricultor registrado, quiero publicar un lote de mi cosecha con cantidad, precio por kilo y categoría para que los compradores urbanos puedan encontrarlo.

**Por qué entra en el Sprint 1:** Valida la integridad referencial, que es el núcleo del Sprint Goal: una publicación no puede existir sin un agricultor dueño. Obliga a modelar la relación `@ManyToOne` en PostgreSQL.

**Detalles técnicos:**
- Endpoint: `POST /api/v1/productos`
- Respuesta esperada: `201 Created` + cabecera `Location`
- Errores: `400` (cantidad o precio inválidos), `404` (agricultor inexistente)

**Criterios de aceptación:**
- [ ] El sistema valida que `cantidadKg` y `precioKg` sean mayores a 0
- [ ] La publicación se asocia obligatoriamente a un agricultor existente
- [ ] Se almacena el lote en PostgreSQL con clave foránea `NOT NULL`
- [ ] Retorna `201 Created` con la cabecera `Location`
- [ ] Retorna `404` si el agricultor no existe

**Prioridad MoSCoW:** Must Have · **Story Points:** 5 · **Módulo:** Ofertas / Inventario

---

### HU-03: Visualización de Precios Promedio
**Historia:** Como Comprador Urbano, quiero ver el precio promedio de un producto por municipio y categoría para negociar precios justos con los agricultores.

**Por qué NO entra en el Sprint 1:** Con 8 SP ocupa casi la mitad de la capacidad del Sprint y agrupa varias reglas de cálculo, lo que viola el criterio INVEST de Small. Debe dividirse en el próximo Backlog Refinement antes del Sprint 2.

**Detalles técnicos:**
- Endpoint: `GET /api/v1/precios/promedio?municipio=&categoria=&dias=`
- Respuesta esperada: `200 OK` con el precio promedio por kilo

**Criterios de aceptación:**
- [ ] El promedio se calcula por municipio y categoría
- [ ] La ventana temporal es configurable
- [ ] Si no hay datos en la ventana, responde `200 OK` sin error
- [ ] La historia se divide en historias más pequeñas en el refinamiento

**Prioridad MoSCoW:** Should Have · **Story Points:** 8 · **Módulo:** Inteligencia de Mercado

---

### HU-04: Filtro por Municipio y Categoría
**Historia:** Como Comprador, quiero filtrar el catálogo de cosechas por municipio y categoría para encontrar productos específicos de mi interés.

**Por qué entra en el Sprint 1:** Enseña a realizar consultas derivadas en Spring Data JPA (`findByUbicacionValleAndCategoria`) mediante parámetros `@RequestParam`.

**Detalles técnicos:**
- Endpoint: `GET /api/v1/productos`
- Parámetros: `municipio`, `categoria` (query params)
- Respuesta esperada: `200 OK` con lista filtrada

**Criterios de aceptación:**
- [ ] Filtra correctamente por municipio y categoría combinados
- [ ] Retorna el catálogo completo si no se envían parámetros
- [ ] Retorna lista vacía (`200 OK`) si no hay coincidencias

**Prioridad MoSCoW:** Must Have · **Story Points:** 3 · **Módulo:** Catálogo / Búsqueda

---

### HU-05: Contacto Directo / Mensajería
**Historia:** Como Comprador Urbano, quiero contactar directamente al agricultor que publicó un lote para acordar detalles de la compra.

**Por qué NO entra en el Sprint 1:** Es Should Have y no aporta a validar la arquitectura base del Sprint Goal.

**Detalles técnicos:**
- Endpoint: `POST /api/v1/mensajes`
- Respuesta esperada: `201 Created`

**Criterios de aceptación:**
- [ ] El mensaje queda asociado a un agricultor y a un producto
- [ ] Se almacena correctamente en PostgreSQL
- [ ] Retorna `404` si el agricultor o el producto no existen
- [ ] Retorna `201 Created` con los datos del mensaje

**Prioridad MoSCoW:** Should Have · **Story Points:** 5 · **Módulo:** Comunicación

---

### HU-06: Autenticación e Inicio de Sesión (JWT)
**Historia:** Como usuario registrado (Agricultor o Comprador), quiero iniciar sesión para acceder de forma segura a las funciones de la plataforma.

**Por qué NO entra en el Sprint 1:** Incluirla llevaría el compromiso a 23 SP, fuera de capacidad. La seguridad se implementa mejor sobre un módulo de usuarios ya estable; en el Sprint 1 ya se persiste el hash BCrypt. Es la primera historia del Sprint 2.

**Detalles técnicos:**
- Endpoint: `POST /api/v1/auth/login`
- Respuesta esperada: `200 OK` con token JWT
- Errores: `401` (credenciales inválidas)

**Criterios de aceptación:**
- [ ] Valida las credenciales contra el hash BCrypt almacenado
- [ ] Retorna un token JWT firmado y con expiración
- [ ] Credenciales inválidas retornan `401 Unauthorized`
- [ ] Los endpoints protegidos rechazan peticiones sin token válido

**Prioridad MoSCoW:** Must Have · **Story Points:** 5 · **Módulo:** Seguridad

---

### HU-07: Consulta de Perfil de Agricultor
**Historia:** Como Agricultor, quiero visualizar la información de mi finca y datos de contacto registrados.

**Por qué entra en el Sprint 1:** Es un GET muy sencillo por ID. Completa el ciclo de lectura/escritura básico y refuerza cómo retornar un DTO en Spring Boot.

**Detalles técnicos:**
- Endpoint: `GET /api/v1/agricultores/{id}`
- Respuesta esperada: `200 OK` con DTO del perfil

**Criterios de aceptación:**
- [ ] Retorna los datos del agricultor si el ID existe
- [ ] Retorna `404` si el ID no existe
- [ ] No expone la contraseña ni datos sensibles en la respuesta

**Prioridad MoSCoW:** Must Have · **Story Points:** 2 · **Módulo:** Usuarios / Perfil

---

### HU-08: Edición / Cancelación de Cosecha
**Historia:** Como Agricultor, quiero editar o cancelar un lote que publiqué para mantener actualizada mi oferta.

**Por qué NO entra en el Sprint 1:** Exige que la publicación (HU-02) esté estabilizada y validada. Entra en el Sprint 2.

**Detalles técnicos:**
- Endpoints: `PUT /api/v1/productos/{id}` y `DELETE /api/v1/productos/{id}`
- Respuesta esperada: `200 OK` (edición) y `204 No Content` (cancelación)

**Criterios de aceptación:**
- [ ] Permite modificar cantidad y precio del lote
- [ ] Valida que cantidad y precio sigan siendo mayores a 0
- [ ] Permite cancelar un lote publicado
- [ ] Retorna `404` si el lote no existe

**Prioridad MoSCoW:** Should Have · **Story Points:** 3 · **Módulo:** Ofertas

---

### HU-09: Registro de Comprador Urbano
**Historia:** Como Comprador Urbano (restaurante o negocio), quiero registrarme en la plataforma para poder explorar y comprar cosechas de agricultores del Valle del Cauca.

**Por qué entra en el Sprint 1:** Es la entrada del lado de la demanda. Reutiliza el patrón de HU-01 y es la historia de ajuste de alcance: si al día 6 el avance es menor al 50%, sale del Sprint sin afectar el Sprint Goal.

**Detalles técnicos:**
- Endpoint: `POST /api/v1/compradores`
- Respuesta esperada: `201 Created` + cabecera `Location`
- Errores: `400` (validación), `409` (NIT duplicado)

**Criterios de aceptación:**
- [ ] El NIT identifica de forma única al comprador
- [ ] Se almacenan razón social, NIT, dirección y ciudad en PostgreSQL
- [ ] Un NIT duplicado retorna `409 Conflict`
- [ ] Retorna `201 Created` con los datos del comprador registrado

**Prioridad MoSCoW:** Must Have · **Story Points:** 3 · **Módulo:** Autenticación / Registro

---

### HU-10: Carrito y Consolidación de Pedido
**Historia:** Como Comprador Urbano, quiero agregar lotes de distintos agricultores a un carrito y consolidarlos en un solo pedido para simplificar mi compra.

**Por qué NO entra en el Sprint 1:** Depende de que existan ofertas publicadas y compradores consolidados. Incluirla ahora violaría el criterio INVEST de independencia.

**Detalles técnicos:**
- Endpoint: `POST /api/v1/pedidos`
- Respuesta esperada: `201 Created` con el detalle del pedido

**Criterios de aceptación:**
- [ ] Permite agregar lotes de varios agricultores al mismo pedido
- [ ] Valida que la cantidad solicitada no supere la disponible
- [ ] Calcula el total del pedido
- [ ] Retorna `201 Created` con el pedido consolidado

**Prioridad MoSCoW:** Must Have · **Story Points:** 5 · **Módulo:** Pedidos

---

### HU-11: Confirmación y Reserva de Stock
**Historia:** Como Comprador Urbano, quiero confirmar mi pedido y que se reserve el stock de los lotes para garantizar su disponibilidad.

**Por qué NO entra en el Sprint 1:** Depende de HU-10 y de que existan ofertas publicadas. Está en el plan tentativo del Sprint 2.

**Detalles técnicos:**
- Endpoint: `POST /api/v1/pedidos/{id}/confirmar`
- Respuesta esperada: `200 OK` con estado `CONFIRMADO`
- Errores: `404` (pedido no existe), `409` (stock insuficiente)

**Criterios de aceptación:**
- [ ] Reserva la cantidad en kg de cada lote del pedido
- [ ] Si el stock es insuficiente, retorna `409 Conflict` sin reservar nada
- [ ] La operación es transaccional
- [ ] El pedido pasa a estado `CONFIRMADO`

**Prioridad MoSCoW:** Should Have · **Story Points:** 5 · **Módulo:** Pedidos / Inventario

---

### HU-12: Aceptación / Rechazo de Pedido
**Historia:** Como Agricultor, quiero aceptar o rechazar los pedidos que recibo para gestionar mi capacidad real de entrega.

**Por qué NO entra en el Sprint 1:** Pertenece a logística y depende de que existan pedidos confirmados.

**Detalles técnicos:**
- Endpoint: `PATCH /api/v1/pedidos/{id}/respuesta`
- Respuesta esperada: `200 OK` con el nuevo estado del pedido

**Criterios de aceptación:**
- [ ] El agricultor puede aceptar un pedido pendiente
- [ ] El agricultor puede rechazar un pedido pendiente
- [ ] Al rechazar, se libera el stock reservado
- [ ] Un pedido ya respondido retorna `409 Conflict`

**Prioridad MoSCoW:** Should Have · **Story Points:** 3 · **Módulo:** Logística

---

### HU-13: Trazabilidad y Estado de Despacho
**Historia:** Como Comprador Urbano, quiero consultar el estado de despacho de mi pedido para saber cuándo llegará mi compra.

**Por qué NO entra en el Sprint 1:** Depende de que exista el flujo completo de pedidos.

**Detalles técnicos:**
- Endpoint: `GET /api/v1/pedidos/{id}/estado`
- Respuesta esperada: `200 OK` con el estado actual del despacho
- Errores: `404` (pedido no existe)

**Criterios de aceptación:**
- [ ] Muestra el estado actual (aceptado, en preparación, en tránsito, entregado)
- [ ] Registra el historial de cambios de estado
- [ ] Retorna `404 Not Found` si el pedido no existe

**Prioridad MoSCoW:** Should Have · **Story Points:** 5 · **Módulo:** Logística

---

### HU-14: Calificación y Reseña de Cosecha
**Historia:** Como Comprador Urbano, quiero calificar y dejar una reseña de una cosecha recibida para ayudar a otros compradores y reconocer a los buenos productores.

**Por qué NO entra en el Sprint 1:** Es Could Have y no aporta a validar la arquitectura base.

**Detalles técnicos:**
- Endpoint: `POST /api/v1/productos/{id}/resenas`
- Respuesta esperada: `201 Created`

**Criterios de aceptación:**
- [ ] La calificación es un valor entre 1 y 5
- [ ] Solo puede reseñar un comprador con un pedido entregado
- [ ] Una calificación fuera de rango retorna `400 Bad Request`
- [ ] Se almacena la reseña asociada al producto y al comprador

**Prioridad MoSCoW:** Could Have · **Story Points:** 3 · **Módulo:** Reputación

---

### HU-15: Reporte de Ventas por Municipio
**Historia:** Como Administrador de la plataforma, quiero ver un reporte de ventas por municipio para identificar las zonas de mayor actividad comercial.

**Por qué NO entra en el Sprint 1:** Es Could Have para esta etapa y no aporta al Sprint Goal.

**Detalles técnicos:**
- Endpoint: `GET /api/v1/reportes/ventas?municipio=`
- Respuesta esperada: `200 OK` con el total de ventas por municipio

**Criterios de aceptación:**
- [ ] Agrupa las ventas por municipio
- [ ] Permite filtrar por un municipio específico
- [ ] Sin ventas registradas, responde `200 OK` con datos en cero

**Prioridad MoSCoW:** Could Have · **Story Points:** 8 · **Módulo:** Analítica / Dashboard

---

## Verificación INVEST

Cada historia cumple con:
- **I**ndependiente: no depende de que otra HU esté implementada para poder estimarse.
- **N**egociable: el detalle de implementación (UI, validaciones exactas) queda abierto al equipo.
- **V**aliosa: aporta valor directo al agricultor o al comprador.
- **E**stimable: tiene alcance claro para asignar Story Points.
- **P**equeña: puede completarse dentro de un sprint (excepto HU-03 y HU-15, candidatas a dividirse en refinamiento).
- **E**valuable (Testable): los criterios de aceptación definen un resultado verificable.
