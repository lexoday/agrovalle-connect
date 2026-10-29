-- Migracion de la estructura anterior (usuarios_productores + productos)
-- al modelo relacional v2. Ejecutar una sola vez sobre una copia de seguridad.
-- Abortara sin aplicar cambios si datos existentes no cumplen el nuevo modelo.
BEGIN;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'usuarios_productores' AND column_name = 'municipio'
    ) OR NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'productos' AND column_name = 'categoria'
    ) THEN
        RAISE EXCEPTION 'No se reconoce el esquema anterior; revise la version de la base';
    END IF;

    IF EXISTS (
        SELECT 1 FROM usuarios_productores
        WHERE length(cedula) > 10
            OR length(correo) > 120
            OR length(nombre) > 80
            OR length(municipio) > 80
    ) THEN
        RAISE EXCEPTION 'Hay datos de productor que exceden los nuevos limites de longitud';
    END IF;

    IF EXISTS (
        SELECT 1 FROM productos
        WHERE precio <= 0
            OR cantidad_kg <= 0
            OR length(nombre) > 100
            OR length(categoria) > 60
            OR length(municipio) > 80
    ) THEN
        RAISE EXCEPTION 'Hay productos con datos no validos para el nuevo esquema';
    END IF;
END
$$;

CREATE TABLE municipios (
    id_municipio BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL UNIQUE,
    departamento VARCHAR(60) NOT NULL
);

CREATE TABLE categorias (
    id_categoria BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL UNIQUE
);

INSERT INTO municipios (nombre, departamento)
VALUES
    ('Dagua', 'Valle del Cauca'),
    ('Palmira', 'Valle del Cauca'),
    ('Buga', 'Valle del Cauca'),
    ('Tuluá', 'Valle del Cauca'),
    ('Caicedonia', 'Valle del Cauca'),
    ('Jamundí', 'Valle del Cauca')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO municipios (nombre, departamento)
SELECT DISTINCT municipio, 'Valle del Cauca'
FROM (
    SELECT municipio FROM usuarios_productores
    UNION
    SELECT municipio FROM productos
) nombres_municipio
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO categorias (nombre)
SELECT DISTINCT categoria FROM productos;
INSERT INTO categorias (nombre)
VALUES ('Frutas'), ('Verduras'), ('Granos'), ('Hortalizas'), ('Tubérculos')
ON CONFLICT (nombre) DO NOTHING;

ALTER TABLE usuarios_productores
    ADD COLUMN id_municipio BIGINT;
UPDATE usuarios_productores productor
SET id_municipio = municipio.id_municipio
FROM municipios municipio
WHERE municipio.nombre = productor.municipio;
ALTER TABLE usuarios_productores
    ALTER COLUMN id_municipio SET NOT NULL,
    ADD CONSTRAINT fk_productores_municipio
        FOREIGN KEY (id_municipio) REFERENCES municipios (id_municipio)
        ON DELETE RESTRICT;
ALTER TABLE usuarios_productores RENAME COLUMN id TO id_productor;
ALTER TABLE usuarios_productores RENAME COLUMN nombre TO nombres;
ALTER TABLE usuarios_productores
    ADD COLUMN apellidos VARCHAR(80) NOT NULL DEFAULT '',
    ADD COLUMN telefono VARCHAR(15),
    ADD COLUMN fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ALTER COLUMN nombres TYPE VARCHAR(80),
    ALTER COLUMN cedula TYPE VARCHAR(10),
    ALTER COLUMN correo TYPE VARCHAR(120),
    ALTER COLUMN nombre_finca TYPE VARCHAR(120);
ALTER TABLE usuarios_productores DROP COLUMN municipio;

ALTER TABLE productos
    ADD COLUMN id_municipio BIGINT,
    ADD COLUMN id_categoria BIGINT;
UPDATE productos producto
SET id_municipio = municipio.id_municipio
FROM municipios municipio
WHERE municipio.nombre = producto.municipio;
UPDATE productos producto
SET id_categoria = categoria.id_categoria
FROM categorias categoria
WHERE categoria.nombre = producto.categoria;
ALTER TABLE productos
    ALTER COLUMN id_municipio SET NOT NULL,
    ALTER COLUMN id_categoria SET NOT NULL,
    ADD COLUMN descripcion VARCHAR(500),
    ADD COLUMN fecha_cosecha DATE NOT NULL DEFAULT CURRENT_DATE,
    ADD COLUMN estado VARCHAR(20) NOT NULL DEFAULT 'DISPONIBLE',
    ADD COLUMN fecha_publicacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ADD CONSTRAINT fk_ofertas_categoria
        FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria)
        ON DELETE RESTRICT,
    ADD CONSTRAINT fk_ofertas_municipio
        FOREIGN KEY (id_municipio) REFERENCES municipios (id_municipio)
        ON DELETE RESTRICT,
    ADD CONSTRAINT ck_ofertas_cantidad_positiva CHECK (cantidad_kg > 0),
    ADD CONSTRAINT ck_ofertas_precio_positivo CHECK (precio > 0),
    ADD CONSTRAINT ck_ofertas_estado
        CHECK (estado IN ('DISPONIBLE', 'AGOTADA', 'INACTIVA'));
ALTER TABLE productos RENAME TO ofertas_cosechas;
ALTER TABLE ofertas_cosechas
    RENAME COLUMN id TO id_oferta;
ALTER TABLE ofertas_cosechas
    RENAME COLUMN productor_id TO id_productor;
ALTER TABLE ofertas_cosechas
    RENAME COLUMN nombre TO nombre_producto;
ALTER TABLE ofertas_cosechas
    RENAME COLUMN precio TO precio_por_kg;
ALTER TABLE ofertas_cosechas
    RENAME COLUMN categoria TO categoria_legacy;
ALTER TABLE ofertas_cosechas
    RENAME COLUMN municipio TO municipio_legacy;
ALTER TABLE ofertas_cosechas
    DROP COLUMN categoria_legacy,
    DROP COLUMN municipio_legacy;
ALTER TABLE ofertas_cosechas
    DROP CONSTRAINT IF EXISTS fk_productos_productor,
    ADD CONSTRAINT fk_ofertas_productor
        FOREIGN KEY (id_productor) REFERENCES usuarios_productores (id_productor)
        ON DELETE RESTRICT;

DROP INDEX IF EXISTS idx_productos_municipio_categoria;
CREATE INDEX idx_ofertas_municipio_categoria
    ON ofertas_cosechas (id_municipio, id_categoria);
CREATE INDEX idx_ofertas_productor
    ON ofertas_cosechas (id_productor);

COMMIT;
