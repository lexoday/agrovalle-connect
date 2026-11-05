# Arquitectura MVC en 4 capas y patrones GoF/Enterprise — AgroValle Connect

## Capas

| Capa | Paquete | Componentes | Responsabilidad |
|---|---|---|---|
| 1. Presentación / API REST | `controller`, `dto` | `@RestController`, DTOs con `@Valid` | Mapear rutas `/api/v1/...`, validar entrada y responder 201, 200, 400, 404, 409 |
| 2. Lógica de negocio | `service`, `exception` | `@Service`, `@Transactional`, excepciones de dominio | Cédula y correo únicos, cálculos y reglas agrícolas |
| 3. Acceso a datos | `repository` | `@Repository`, `JpaRepository` | CRUD y consultas derivadas (`findByMunicipioIgnoreCaseAndCategoriaIgnoreCase`) |
| 4. Persistencia | PostgreSQL 15/16 | `schema.sql` | Almacenamiento permanente, integridad referencial y ACID |

Regla de dependencias: cada capa solo llama a la inmediatamente inferior. Ningún controlador ejecuta SQL ni procesa reglas de negocio.

## Patrones GoF y Enterprise

- **Repository:** `ProductorRepository` y `ProductoRepository` encapsulan el acceso a datos tras interfaces.
- **Singleton / IoC (Inyección de dependencias):** Spring crea los beans como singleton; las dependencias se inyectan por constructor (`ProductorService`, `ProductoService`, `CalculadorFleteService`).
- **DTO:** `ProductorRegistroDTO`, `ProductorRespuestaDTO` y `ProductoDTO` aíslan las entidades de los contratos JSON. `ProductorRespuestaDTO` no expone la contraseña.
- **Data Mapper / Factory:** `ProductorRespuestaDTO.desde(...)` y `ProductoDTO.desde(...)` convierten entidades en DTO.
- **Strategy (comportamiento, Sprint 2):** `EstrategiaFlete` con una implementación por municipio del Valle del Cauca. Se pueden agregar estrategias sin modificar `CalculadorFleteServiceImpl` (OCP).

## Principios SOLID visibles en el código

| Principio | Evidencia |
|---|---|
| SRP | Los controladores traducen HTTP, los servicios aplican reglas y los repositorios consultan datos. |
| OCP | `CalculadorFleteServiceImpl` delega el cálculo en estrategias `EstrategiaFlete`; nuevas tarifas no requieren modificar el selector. |
| LSP | Las implementaciones de `ProductorService`, `ProductoService` y `CalculadorFleteService` pueden sustituirse por sus contratos. |
| ISP | Los contratos de servicio exponen solo las operaciones necesarias por cada capacidad; los repositorios declaran consultas específicas. |
| DIP | Los controladores dependen de interfaces de servicio y las implementaciones reciben repositorios, codificador y estrategias por constructor. |

Las pruebas unitarias usan Mockito para aislar las reglas de negocio y las pruebas web usan MockMvc para validar el contrato HTTP.

## Diagrama de despliegue

```mermaid
flowchart LR
    subgraph N1[Nodo 1: Cliente]
        A[Navegador / Web App]
    end
    subgraph N2[Nodo 2: Servidor de aplicaciones]
        B[Spring Boot 3.3 en JVM Java 17 LTS<br/>Tomcat embebido: 8080 / 443]
    end
    subgraph N3[Nodo 3: Servidor de base de datos]
        C[(PostgreSQL 15/16<br/>agrovalle_db: 5432)]
    end
    A -- "HTTPS / JSON" --> B
    B -- "JDBC (TCP 5432)" --> C
```

Exportar a PNG para `docs/diseno/diagrama-despliegue.png`.

![Diagrama de despliegue de AgroValle Connect](./diagrama-despliegue.png)
