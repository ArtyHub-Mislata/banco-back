-- V1__create_tables.sql

-- Tabla de clientes
CREATE TABLE clientes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    last_name1 VARCHAR(255),
    last_name2 VARCHAR(255),
    dni VARCHAR(20) UNIQUE NOT NULL,
    api_token VARCHAR(255)
);

-- Tabla de cuentas bancarias
CREATE TABLE cuentas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    saldo DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    iban VARCHAR(34) UNIQUE NOT NULL,
    client_id BIGINT NOT NULL,
    CONSTRAINT fk_cuenta_cliente FOREIGN KEY (client_id)
        REFERENCES clientes(id) ON DELETE CASCADE
);

-- Tabla de tarjetas de crédito
CREATE TABLE tarjetas_credito (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero_tarjeta VARCHAR(19) UNIQUE NOT NULL,
    fecha_caducidad DATE NOT NULL,
    cvv VARCHAR(4) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    cuenta_id BIGINT NOT NULL,
    CONSTRAINT fk_tarjeta_cuenta FOREIGN KEY (cuenta_id)
        REFERENCES cuentas(id) ON DELETE CASCADE
);

-- Tabla de movimientos bancarios
CREATE TABLE movimientos_bancarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo_movimiento VARCHAR(50) NOT NULL,
    origen_movimiento VARCHAR(50),
    n_tarjeta VARCHAR(19),
    fecha DATETIME NOT NULL,
    importe DECIMAL(15,2) NOT NULL,
    concepto TEXT,
    cuenta_id BIGINT NOT NULL,
    CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id)
        REFERENCES cuentas(id) ON DELETE CASCADE
);

-- Tabla de sesiones
CREATE TABLE sesions (
    token VARCHAR(255) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    date_create DATETIME NOT NULL,
    CONSTRAINT fk_sesion_cliente FOREIGN KEY (user_id)
        REFERENCES clientes(id) ON DELETE CASCADE
);

-- Índices para mejorar el rendimiento
CREATE INDEX idx_clientes_dni ON clientes(dni);
CREATE INDEX idx_clientes_login ON clientes(login);
CREATE INDEX idx_cuentas_iban ON cuentas(iban);
CREATE INDEX idx_cuentas_client_id ON cuentas(client_id);
CREATE INDEX idx_tarjetas_numero ON tarjetas_credito(numero_tarjeta);
CREATE INDEX idx_tarjetas_cuenta_id ON tarjetas_credito(cuenta_id);
CREATE INDEX idx_movimientos_cuenta_id ON movimientos_bancarios(cuenta_id);
CREATE INDEX idx_movimientos_fecha ON movimientos_bancarios(fecha);
CREATE INDEX idx_sesions_user_id ON sesions(user_id);
CREATE INDEX idx_sesions_date_create ON sesions(date_create);