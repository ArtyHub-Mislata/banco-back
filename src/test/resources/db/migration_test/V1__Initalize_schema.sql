-- V1__create_tables.sql

-- Tabla clientes
CREATE TABLE clientes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          login VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          last_name1 VARCHAR(255),
                          last_name2 VARCHAR(255),
                          dni VARCHAR(255) NOT NULL,
                          api_token VARCHAR(255)
);

-- Tabla cuentas
CREATE TABLE cuentas (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         saldo DECIMAL(19,2) NOT NULL,
                         iban VARCHAR(255) NOT NULL,
                         client_id BIGINT NOT NULL,
                         FOREIGN KEY (client_id) REFERENCES clientes(id)
);

-- Tabla tarjetas_credito
CREATE TABLE tarjetas_credito (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  numero_tarjeta VARCHAR(255) NOT NULL,
                                  fecha_caducidad VARCHAR(255) NOT NULL,
                                  cvv VARCHAR(255) NOT NULL,
                                  nombre_completo VARCHAR(255) NOT NULL,
                                  cuenta_id BIGINT,
                                  FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

-- Tabla movimientos_bancarios
CREATE TABLE movimientos_bancarios (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       tipo_movimiento VARCHAR(255) NOT NULL,
                                       origen_movimiento VARCHAR(255) NOT NULL,
                                       tarjeta_credito_id BIGINT,
                                       fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
                                       importe DECIMAL(19,2) NOT NULL,
                                       concepto VARCHAR(255),
                                       cuenta_id BIGINT NOT NULL,
                                       FOREIGN KEY (tarjeta_credito_id) REFERENCES tarjetas_credito(id),
                                       FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

-- Tabla sesions
CREATE TABLE sesions (
                         token VARCHAR(255) PRIMARY KEY,
                         client_id BIGINT NOT NULL,
                         date_create DATETIME DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (client_id) REFERENCES clientes(id)
);

-- Índices para mejorar el rendimiento de las consultas
CREATE INDEX idx_clientes_login ON clientes(login);
CREATE INDEX idx_clientes_dni ON clientes(dni);
CREATE INDEX idx_cuentas_iban ON cuentas(iban);
CREATE INDEX idx_cuentas_client_id ON cuentas(client_id);
CREATE INDEX idx_tarjetas_numero ON tarjetas_credito(numero_tarjeta);
CREATE INDEX idx_movimientos_fecha ON movimientos_bancarios(fecha);
CREATE INDEX idx_movimientos_cuenta_id ON movimientos_bancarios(cuenta_id);
CREATE INDEX idx_sesions_client_id ON sesions(client_id);
CREATE INDEX idx_sesions_date_create ON sesions(date_create);