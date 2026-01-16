-- Tabla clientes
CREATE TABLE clientes (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          login VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          last_name1 VARCHAR(255) NOT NULL,
                          last_name2 VARCHAR(255),
                          dni VARCHAR(20) NOT NULL,
                          api_token VARCHAR(255)
);

-- Tabla cuentas
CREATE TABLE cuentas (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         saldo DECIMAL(15,2) NOT NULL DEFAULT 0.00,
                         iban VARCHAR(34) NOT NULL UNIQUE,
                         client_id BIGINT NOT NULL,
                         FOREIGN KEY (client_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- Tabla tarjetas_credito
CREATE TABLE tarjetas_credito (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  numero_tarjeta VARCHAR(19) NOT NULL UNIQUE,
                                  fecha_caducidad VARCHAR(40) NOT NULL,
                                  cvv VARCHAR(3) NOT NULL,
                                  nombre_completo VARCHAR(255) NOT NULL,
                                  cuenta_id BIGINT NOT NULL,
                                  FOREIGN KEY (cuenta_id) REFERENCES cuentas(id) ON DELETE CASCADE
);

-- Tabla movimientos_bancarios
CREATE TABLE movimientos_bancarios (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       tipo_movimiento VARCHAR(50) NOT NULL,
                                       origen_movimiento VARCHAR(50) NOT NULL,
                                       n_tarjeta VARCHAR(19),
                                       fecha DATETIME NOT NULL,
                                       importe DECIMAL(15,2) NOT NULL,
                                       concepto VARCHAR(255),
                                       cuenta_id BIGINT NOT NULL,
                                       FOREIGN KEY (cuenta_id) REFERENCES cuentas(id) ON DELETE CASCADE
);

-- Tabla sesions
CREATE TABLE sesions (
                         token VARCHAR(255) PRIMARY KEY,
                         client_id BIGINT NOT NULL,
                         date_create DATETIME NOT NULL,
                         FOREIGN KEY (client_id) REFERENCES clientes(id) ON DELETE CASCADE
);