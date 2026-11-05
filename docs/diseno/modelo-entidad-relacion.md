# Modelo Entidad-Relación (MER) — AgroValle Connect

Modelo conceptual en notación Chen. Los rectángulos representan entidades, los rombos relaciones y los óvalos atributos; las cardinalidades se muestran junto a cada relación.

```mermaid
flowchart LR
    M["MUNICIPIO"]
    P["PRODUCTOR"]
    O["OFERTA_COSECHA"]
    C["CATEGORIA"]

    R1{"alberga"}
    R2{"publica"}
    R3{"origina"}
    R4{"clasifica"}

    M ---|1| R1
    R1 ---|N| P
    P ---|1| R2
    R2 ---|N| O
    M ---|1| R3
    R3 ---|N| O
    O ---|N| R4
    R4 ---|1| C

    M --- M1(["id_municipio · PK"])
    M --- M2(["nombre"])
    M --- M3(["departamento"])

    P --- P1(["id_productor · PK"])
    P --- P2(["cedula · único"])
    P --- P3(["nombres"])
    P --- P4(["apellidos"])
    P --- P5(["telefono"])
    P --- P6(["correo · único"])
    P --- P7(["fecha_registro"])

    O --- O1(["id_oferta · PK"])
    O --- O2(["nombre_producto"])
    O --- O3(["descripcion"])
    O --- O4(["cantidad_kg"])
    O --- O5(["precio_por_kg"])
    O --- O6(["fecha_cosecha"])
    O --- O7(["estado"])
    O --- O8(["fecha_publicacion"])

    C --- C1(["id_categoria · PK"])
    C --- C2(["nombre · único"])
```

## Entidades y relaciones

- **MUNICIPIO** alberga productores y es el municipio de origen de las ofertas.
- **PRODUCTOR** publica ofertas de cosecha.
- **OFERTA_COSECHA** identifica el producto publicado, sus cantidades, precios, fechas y estado.
- **CATEGORIA** clasifica las ofertas de cosecha.
- `PK` indica la clave primaria; los atributos identificadores de los catálogos se mantienen únicos según el modelo lógico.

## Reglas de negocio del modelo

1. Un municipio puede albergar varios productores; cada productor registra un municipio.
2. Un productor puede publicar varias ofertas; cada oferta tiene un productor responsable.
3. Un municipio puede tener varias ofertas de origen; cada oferta identifica su municipio.
4. Una categoría clasifica varias ofertas; cada oferta se asigna a una categoría.
5. La cédula y el correo identifican de forma única a cada productor.

## Alcance respecto a la implementación actual

Este MER es la representación conceptual de la imagen de referencia y corresponde al DER ampliado de [modelo-datos-der.md](./modelo-datos-der.md). El sistema implementado actualmente utiliza nombres de producto y categoría como atributos de `productos` y no tiene todavía las entidades/catálogos Municipio y Categoría. El modelo conceptual no implica que esas tablas ya existan en `schema.sql` o en JPA.
