# Product Backlog — AgroValle Connect

Priorización: **M** = Must Have · **S** = Should Have · **C** = Could Have · **W** = Won't Have
Estimación: Story Points (escala de Fibonacci) mediante Planning Poker.

## Tabla resumen

| ID | Historia | Prioridad | Story Points |
|----|----------|-----------|---------------|
| HU-01 | Registro de Agricultores | M | 3 |
| HU-02 | Publicación de Productos | M | 5 |
| HU-03 | Visualización de Precios Regionales | S | 3 |
| HU-04 | Filtro de Categorías | M | 2 |
| HU-05 | Contacto Directo | S | 3 |
| HU-06 | Registro de Comerciantes | M | 3 |
| HU-07 | Carrito de Compras | M | 5 |
| HU-08 | Realización de Pedidos | M | 5 |
| HU-09 | Métodos de Pago | M | 8 |
| HU-10 | Confirmación de Pago | M | 5 |
| HU-11 | Solicitud de Transporte | S | 5 |
| HU-12 | Programación de Entrega | S | 3 |
| HU-13 | Seguimiento del Pedido | S | 8 |
| HU-14 | Calificación del Agricultor | C | 3 |
| HU-15 | Historial de Pedidos | C | 3 |

**Total Story Points:** 65

---

## Detalle de Historias de Usuario

### HU-01: Registro de Agricultores
**Historia:** Como agricultor, quiero registrarme en la plataforma para poder publicar mis productos agrícolas.

**Escenario BDD:**
- **Given** que el agricultor se encuentra en la pantalla de registro.
- **When** ingresa sus datos y completa el formulario.
- **Then** el sistema crea su cuenta y le permite acceder a la plataforma.

**Prioridad MoSCoW:** Must Have · **Story Points:** 3

---

### HU-02: Publicación de Productos
**Historia:** Como agricultor, quiero publicar mis productos agrícolas indicando su cantidad, precio y disponibilidad para que los comerciantes puedan conocerlos y comprarlos.

**Escenario BDD:**
- **Given** que el agricultor tiene una cuenta registrada.
- **When** ingresa la información del producto y lo publica.
- **Then** el sistema muestra el producto disponible en el catálogo.

**Prioridad MoSCoW:** Must Have · **Story Points:** 5

---

### HU-03: Visualización de Precios Regionales
**Historia:** Como usuario, quiero consultar los precios de los productos agrícolas para poder conocer su valor antes de realizar una compra.

**Escenario BDD:**
- **Given** que existen productos publicados en la plataforma.
- **When** el usuario consulta un producto.
- **Then** el sistema muestra su precio y la información disponible.

**Prioridad MoSCoW:** Should Have · **Story Points:** 3

---

### HU-04: Filtro de Categorías
**Historia:** Como usuario, quiero filtrar los productos agrícolas por categoría para encontrar de manera más rápida los productos que sean de mi interés.

**Escenario BDD:**
- **Given** que existen diferentes productos disponibles.
- **When** el usuario selecciona una categoría.
- **Then** el sistema muestra únicamente los productos pertenecientes a la categoría seleccionada.

**Prioridad MoSCoW:** Must Have · **Story Points:** 2

---

### HU-05: Contacto Directo
**Historia:** Como usuario, quiero contactar directamente al agricultor que ofrece un producto para consultar información sobre su precio, cantidad y disponibilidad antes de realizar una compra.

**Escenario BDD:**
- **Given** que el usuario está consultando un producto publicado por un agricultor.
- **When** selecciona la opción de contacto directo.
- **Then** el sistema permite establecer comunicación con el agricultor.

**Prioridad MoSCoW:** Should Have · **Story Points:** 3

---

### HU-06: Registro de Comerciantes
**Historia:** Como usuario comerciante, quiero registrarme en el sistema para poder realizar compras de productos agrícolas.

**Escenario BDD:**
- **Given** que el usuario se encuentra en la pantalla de registro.
- **When** ingresa sus datos y completa el registro.
- **Then** el sistema crea su cuenta y le permite acceder como comerciante.

**Prioridad MoSCoW:** Must Have · **Story Points:** 3

---

### HU-07: Carrito de Compras
**Historia:** Como usuario comerciante, quiero agregar productos al carrito para poder organizar los productos que deseo comprar.

**Escenario BDD:**
- **Given** que el usuario está consultando productos disponibles.
- **When** selecciona un producto y lo agrega al carrito.
- **Then** el sistema agrega el producto al carrito y muestra la cantidad seleccionada.

**Prioridad MoSCoW:** Must Have · **Story Points:** 5

---

### HU-08: Realización de Pedidos
**Historia:** Como usuario comerciante, quiero realizar un pedido con los productos seleccionados para poder comprar directamente a los agricultores.

**Escenario BDD:**
- **Given** que el usuario tiene productos disponibles en su carrito.
- **When** confirma el pedido.
- **Then** el sistema registra el pedido y muestra la información correspondiente.

**Prioridad MoSCoW:** Must Have · **Story Points:** 5

---

### HU-09: Métodos de Pago
**Historia:** Como usuario comerciante, quiero seleccionar un método de pago para poder pagar los productos de mi pedido.

**Escenario BDD:**
- **Given** que el usuario está realizando un pedido.
- **When** selecciona uno de los métodos de pago disponibles.
- **Then** el sistema registra el método de pago seleccionado para el pedido.

**Prioridad MoSCoW:** Must Have · **Story Points:** 8

---

### HU-10: Confirmación de Pago
**Historia:** Como usuario comerciante, quiero recibir una confirmación del pago para saber que mi pedido fue procesado correctamente.

**Escenario BDD:**
- **Given** que el usuario ha seleccionado un método de pago.
- **When** el pago es procesado correctamente.
- **Then** el sistema muestra una confirmación del pago y actualiza el estado del pedido.

**Prioridad MoSCoW:** Must Have · **Story Points:** 5

---

### HU-11: Solicitud de Transporte
**Historia:** Como usuario comerciante, quiero solicitar el transporte de mi pedido para poder recibir los productos agrícolas en el lugar indicado.

**Escenario BDD:**
- **Given** que el usuario tiene un pedido confirmado.
- **When** solicita el servicio de transporte.
- **Then** el sistema registra la solicitud de transporte asociada al pedido.

**Prioridad MoSCoW:** Should Have · **Story Points:** 5

---

### HU-12: Programación de Entrega
**Historia:** Como usuario comerciante, quiero conocer la fecha estimada de entrega de mi pedido para poder organizar la recepción de los productos.

**Escenario BDD:**
- **Given** que el pedido cuenta con una solicitud de transporte.
- **When** se programa la entrega.
- **Then** el sistema muestra al usuario la fecha estimada de entrega.

**Prioridad MoSCoW:** Should Have · **Story Points:** 3

---

### HU-13: Seguimiento del Pedido
**Historia:** Como usuario comerciante, quiero consultar el estado de mi pedido para poder conocer el avance de la entrega.

**Escenario BDD:**
- **Given** que el usuario tiene un pedido en proceso.
- **When** consulta el estado del pedido.
- **Then** el sistema muestra el estado actual de la entrega.

**Prioridad MoSCoW:** Should Have · **Story Points:** 8

---

### HU-14: Calificación del Agricultor
**Historia:** Como usuario comerciante, quiero calificar al agricultor después de recibir mi pedido para poder expresar mi experiencia con la compra.

**Escenario BDD:**
- **Given** que el usuario recibió correctamente su pedido.
- **When** realiza una calificación al agricultor.
- **Then** el sistema registra la calificación asociada al agricultor.

**Prioridad MoSCoW:** Could Have · **Story Points:** 3

---

### HU-15: Historial de Pedidos
**Historia:** Como usuario comerciante, quiero consultar mi historial de pedidos para poder revisar las compras que he realizado anteriormente.

**Escenario BDD:**
- **Given** que el usuario ha realizado pedidos anteriormente.
- **When** consulta su historial de pedidos.
- **Then** el sistema muestra los pedidos realizados y su información correspondiente.

**Prioridad MoSCoW:** Could Have · **Story Points:** 3

---

## Verificación INVEST

Cada historia cumple con:
- **I**ndependiente: no depende de que otra HU esté implementada para poder estimarse.
- **N**egociable: el detalle de implementación (UI, validaciones exactas) queda abierto al equipo.
- **V**aliosa: aporta valor directo al agricultor o al comerciante.
- **E**stimable: tiene alcance claro para asignar Story Points.
- **P**equeña: puede completarse dentro de un sprint.
- **E**valuable (Testable): el escenario Given-When-Then define un criterio de aceptación verificable.
