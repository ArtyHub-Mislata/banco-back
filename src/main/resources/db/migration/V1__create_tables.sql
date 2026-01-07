-- V1__create_tables_simple.sql
CREATE TABLE clientes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          login VARCHAR(50) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL,
                          name VARCHAR(100) NOT NULL,
                          last_name1 VARCHAR(100) NOT NULL,
                          last_name2 VARCHAR(100),
                          dni VARCHAR(20) NOT NULL UNIQUE,
                          api_token VARCHAR(500),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE cuentas (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         saldo DECIMAL(19,2) DEFAULT 0.00,
                         iban VARCHAR(34) NOT NULL UNIQUE,
                         client_id BIGINT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (client_id) REFERENCES clientes(id) ON DELETE CASCADE
);

CREATE TABLE tarjetas_credito (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  numero_tarjeta DECIMAL(16,0) NOT NULL UNIQUE,
                                  fecha_caducidad DATE NOT NULL,
                                  cvv VARCHAR(4) NOT NULL,
                                  nombre_completo VARCHAR(150) NOT NULL,
                                  cuenta_id BIGINT NOT NULL,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  FOREIGN KEY (cuenta_id) REFERENCES cuentas(id) ON DELETE CASCADE
);

CREATE TABLE movimientos_bancarios (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       tipo_movimiento VARCHAR(50) NOT NULL,
                                       origen_movimiento VARCHAR(50) NOT NULL,
                                       fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       importe DECIMAL(19,2) NOT NULL,
                                       concepto VARCHAR(255),
                                       cuenta_id BIGINT NOT NULL,
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       FOREIGN KEY (cuenta_id) REFERENCES cuentas(id) ON DELETE CASCADE
);

CREATE INDEX idx_cuentas_cliente_id ON cuentas(client_id);
CREATE INDEX idx_tarjetas_cuenta_id ON tarjetas_credito(cuenta_id);
CREATE INDEX idx_movimientos_cuenta_id ON movimientos_bancarios(cuenta_id);
CREATE INDEX idx_movimientos_fecha ON movimientos_bancarios(fecha);
CREATE INDEX idx_clientes_dni ON clientes(dni);
CREATE INDEX idx_clientes_login ON clientes(login);
CREATE INDEX idx_cuentas_iban ON cuentas(iban);