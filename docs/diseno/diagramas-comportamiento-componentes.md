# Diagramas UML de comportamiento y componentes

## Diagrama de secuencia — HU-01 (registro de productor)

```mermaid
sequenceDiagram
    actor C as Cliente HTTP
    participant AC as AuthController
    participant PS as ProductorService
    participant PR as ProductorRepository
    participant DB as PostgreSQL
    C->>AC: POST /api/v1/auth/register (JSON)
    AC->>AC: @Valid (400 si falla)
    AC->>PS: registrar(dto)
    PS->>PR: existsByCedula(cedula)
    PR->>DB: SELECT ... WHERE cedula = ?
    DB-->>PR: false
    PS->>PR: existsByCorreo(correo)
    PR->>DB: SELECT ... WHERE correo = ?
    DB-->>PR: false
    PS->>PR: save(productor)
    PR->>DB: INSERT usuarios_productores
    DB-->>PR: id generado
    PS-->>AC: ProductorRespuestaDTO
    AC-->>C: 201 Created + Location
```

## Diagrama de secuencia — HU-07 (consulta de perfil)

```mermaid
sequenceDiagram
    actor C as Cliente HTTP
    participant PC as ProductorController
    participant PS as ProductorService
    participant PR as ProductorRepository
    C->>PC: GET /api/v1/productores/{id}
    PC->>PS: consultarPorId(id)
    PS->>PR: findById(id)
    alt existe
        PR-->>PS: Productor
        PS-->>PC: ProductorRespuestaDTO
        PC-->>C: 200 OK
    else no existe
        PR-->>PS: Optional.empty
        PS-->>PC: ProductorNoEncontradoException
        PC-->>C: 404 Not Found
    end
```

## Diagrama de componentes — paquetes Java

```mermaid
flowchart TB
    subgraph controller
        AC[AuthController]
        PC[ProductorController]
        PRC[ProductoController]
    end
    subgraph dto
        DTO[ProductorRegistroDTO / ProductorRespuestaDTO / ProductoDTO]
    end
    subgraph service
        PS[ProductorService]
        PRS[ProductoService]
        CF[CalculadorFleteService]
    end
    subgraph repository
        R1[ProductorRepository]
        R2[ProductoRepository]
    end
    subgraph model
        M1[Productor]
        M2[Producto]
    end
    subgraph exception
        EX[GlobalExceptionHandler]
    end
    AC --> PS
    PC --> PS
    PRC --> PRS
    PS --> R1
    PRS --> R2
    R1 --> M1
    R2 --> M2
    AC -.-> DTO
    PC -.-> DTO
    PRC -.-> DTO
    EX -.-> controller
```

Exportar cada diagrama a PNG en `docs/diseno/`: `diagrama-secuencia.png` y `diagrama-componentes.png`.

## Wireframes y mockups

Crear las pantallas principales (registro, perfil y catálogo con filtros) en Figma o similar, exportarlas como PDF a `docs/diseno/mockups-ui.pdf` y validarlas con el Product Owner para la característica de Usabilidad (ISO/IEC 25010).
