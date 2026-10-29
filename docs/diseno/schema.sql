-- AgroValle Connect - Esquema PostgreSQL 15/16 (base agrovalle_db)
-- Modelo normalizado hasta Tercera Forma Normal (3FN).
-- Claves primarias BIGINT autoincrementables (GENERATED ALWAYS AS IDENTITY).
-- Ejecutar antes de iniciar la aplicación (spring.jpa.hibernate.ddl-auto=validate).

CREATE TABLE IF NOT EXISTS usuarios_productores (
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre        VARCHAR(100) NOT NULL,
    correo        VARCHAR(150) NOT NULL,
    cedula        VARCHAR(20)  NOT NULL,
    municipio     VARCHAR(80)  NOT NULL,
    password_hash VARCHAR(100) NOT NULL,
    nombre_finca  VARCHAR(120),
    CONSTRAINT uq_productores_correo UNIQUE (correo),
    CONSTRAINT uq_productores_cedula UNIQUE (cedula)
);

CREATE TABLE IF NOT EXISTS productos (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    productor_id BIGINT        NOT NULL,
    nombre       VARCHAR(100)  NOT NULL,
    categoria    VARCHAR(50)   NOT NULL,
    municipio    VARCHAR(80)   NOT NULL,
    precio       NUMERIC(12,2) NOT NULL CHECK (precio >= 0),
    cantidad_kg  NUMERIC(10,2) NOT NULL CHECK (cantidad_kg >= 0),
    CONSTRAINT fk_productos_productor
        FOREIGN KEY (productor_id) REFERENCES usuarios_productores (id)
        ON DELETE RESTRICT
);

CREATE INDEX IF NOT EXISTS idx_productos_municipio_categoria
    ON productos (municipio, categoria);
