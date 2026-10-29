# Diagramas UML de comportamiento y componentes

## Diagrama de secuencia — HU-01 (registro de productor)

```mermaid
sequenceDiagram
    actor C as Cliente HTTP
    participant AC as AuthController
    participant PS as ProductorService
    participant PSI as ProductorServiceImpl
    participant PR as ProductorRepository
    participant DB as PostgreSQL
    C->>AC: POST /api/v1/auth/register (JSON)
    AC->>AC: @Valid (400 si falla)
    AC->>PS: registrar(dto)
    PS->>PSI: registrar(dto)
    PSI->>PR: existsByCedula(cedula)
    PR->>DB: SELECT ... WHERE cedula = ?
    DB-->>PR: false
    PSI->>PR: existsByCorreo(correo)
    PR->>DB: SELECT ... WHERE correo = ?
    DB-->>PR: false
    PSI->>PR: save(productor)
    PR->>DB: INSERT usuarios_productores
    DB-->>PR: id generado
    PSI-->>PS: ProductorRespuestaDTO
    PS-->>AC: ProductorRespuestaDTO
    AC-->>C: 201 Created + Location
```

## Diagrama de secuencia — HU-07 (consulta de perfil)

```mermaid
sequenceDiagram
    actor C as Cliente HTTP
    participant PC as ProductorController
    participant PS as ProductorService
    participant PSI as ProductorServiceImpl
    participant PR as ProductorRepository
    C->>PC: GET /api/v1/productores/{id}
    PC->>PS: consultarPorId(id)
    PS->>PSI: consultarPorId(id)
    PSI->>PR: findById(id)
    alt existe
        PR-->>PSI: Productor
        PSI-->>PS: ProductorRespuestaDTO
        PS-->>PC: ProductorRespuestaDTO
        PC-->>C: 200 OK
    else no existe
        PR-->>PSI: Optional.empty
        PSI-->>PS: ProductorNoEncontradoException
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
        PS[ProductorService interface]
        PSI[ProductorServiceImpl]
        PRS[ProductoService interface]
        PRSI[ProductoServiceImpl]
        CF[CalculadorFleteService interface]
        CFI[CalculadorFleteServiceImpl]
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
    PS -.implemented by.-> PSI
    PRS -.implemented by.-> PRSI
    CF -.implemented by.-> CFI
    PSI --> R1
    PRSI --> R2
    R1 --> M1
    R2 --> M2
    AC -.-> DTO
    PC -.-> DTO
    PRC -.-> DTO
    EX -.-> controller
```

Exportar cada diagrama a PNG en `docs/diseno/`: `diagrama-secuencia.png` y `diagrama-componentes.png`.

![Diagrama de secuencia HU-02](./diagrama-secuencia.png)

![Diagrama de componentes](./diagrama-componentes.png)

## Wireframes y mockups

Los mockups de registro, perfil y catálogo con filtros están disponibles en [mockups-ui.pdf](./mockups-ui.pdf); su fuente editable es [mockups-ui.html](./mockups-ui.html). La validación con el Product Owner para la característica de usabilidad (ISO/IEC 25010) queda pendiente.
