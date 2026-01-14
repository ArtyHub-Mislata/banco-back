-- V2__insert_datos_iniciales.sql

-- =========================
-- CLIENTES
-- =========================
INSERT INTO clientes (login, password, name, last_name1, last_name2, dni, api_token)
VALUES
('jlopez', '$2a$10$hashpassword1', 'Juan', 'López', 'Martínez', '12345678A', 'token_jlopez_123'),
('mgarcia', '$2a$10$hashpassword2', 'María', 'García', 'Sánchez', '87654321B', 'token_mgarcia_456'),
('cfernandez', '$2a$10$hashpassword3', 'Carlos', 'Fernández', NULL, '11223344C', 'token_cfernandez_789');

-- =========================
-- CUENTAS
-- =========================
INSERT INTO cuentas (saldo, iban, client_id)
VALUES
(1500.75, 'ES9121000418450200051332', 1),
(3200.00, 'ES7921000813610123456789', 2),
(500.50,  'ES6621000418401234567891', 3);

-- =========================
-- TARJETAS DE CRÉDITO
-- =========================
INSERT INTO tarjetas_credito (numero_tarjeta, fecha_caducidad, cvv, nombre_completo, cuenta_id)
VALUES
(4539123412341234, '2027-06-30', '123', 'Juan López Martínez', 1),
(4716123412345678, '2026-11-30', '456', 'María García Sánchez', 2),
(4556123498765432, '2028-03-31', '789', 'Carlos Fernández', 3);

-- No se insertan movimientos bancarios intencionadamente
