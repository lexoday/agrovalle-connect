-- AgroValle Connect - modelo relacional PostgreSQL 15/16 (instalacion nueva).
-- Modelo en 3FN: municipios, productores, categorias y ofertas de cosecha.
-- Para una base existente con el esquema anterior, usar schema-migration-v2.sql.

CREATE TABLE municipios (
    id_municipio BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL UNIQUE,
    departamento VARCHAR(60) NOT NULL
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

CREATE TABLE categorias (
    id_categoria BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL UNIQUE
);

INSERT INTO categorias (nombre)
VALUES ('Frutas'), ('Verduras'), ('Granos'), ('Hortalizas'), ('Tubérculos')
ON CONFLICT (nombre) DO NOTHING;

CREATE TABLE usuarios_productores (
    id_productor BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cedula VARCHAR(10) NOT NULL UNIQUE,
    nombres VARCHAR(80) NOT NULL,
    apellidos VARCHAR(80) NOT NULL DEFAULT '',
    telefono VARCHAR(15),
    correo VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(100) NOT NULL,
    nombre_finca VARCHAR(120),
    id_municipio BIGINT NOT NULL,
    fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_productores_municipio
        FOREIGN KEY (id_municipio) REFERENCES municipios (id_municipio)
        ON DELETE RESTRICT
);

CREATE TABLE ofertas_cosechas (
    id_oferta BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_productor BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    id_municipio BIGINT NOT NULL,
    nombre_producto VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    cantidad_kg NUMERIC(10,2) NOT NULL CHECK (cantidad_kg > 0),
    precio_por_kg NUMERIC(12,2) NOT NULL CHECK (precio_por_kg > 0),
    fecha_cosecha DATE NOT NULL DEFAULT CURRENT_DATE,
    estado VARCHAR(20) NOT NULL DEFAULT 'DISPONIBLE'
        CHECK (estado IN ('DISPONIBLE', 'AGOTADA', 'INACTIVA')),
    fecha_publicacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ofertas_productor
        FOREIGN KEY (id_productor) REFERENCES usuarios_productores (id_productor)
        ON DELETE RESTRICT,
    CONSTRAINT fk_ofertas_categoria
        FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria)
        ON DELETE RESTRICT,
    CONSTRAINT fk_ofertas_municipio
        FOREIGN KEY (id_municipio) REFERENCES municipios (id_municipio)
        ON DELETE RESTRICT
);

CREATE INDEX idx_ofertas_municipio_categoria
    ON ofertas_cosechas (id_municipio, id_categoria);
CREATE INDEX idx_ofertas_productor
    ON ofertas_cosechas (id_productor);
