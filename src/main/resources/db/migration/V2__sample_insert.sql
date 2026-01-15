-- V2__sample_data.sql

-- Insertar clientes de ejemplo
INSERT INTO clientes (login, password, name, last_name1, last_name2, dni, api_token) VALUES
('juan.perez', 'password123', 'Juan', 'Pérez', 'García', '12345678A', 'token_juan_123'),
('maria.lopez', 'securepass456', 'María', 'López', 'Fernández', '87654321B', 'token_maria_456'),
('carlos.rodriguez', 'carlospass789', 'Carlos', 'Rodríguez', 'Martínez', '11223344C', 'token_carlos_789'),
('ana.gomez', 'anapass101', 'Ana', 'Gómez', 'Sánchez', '55667788D', 'token_ana_101'),
('luis.fernandez', 'luispass202', 'Luis', 'Fernández', 'Díaz', '99887766E', 'token_luis_202');

-- Insertar cuentas bancarias de ejemplo
INSERT INTO cuentas (saldo, iban, client_id) VALUES
(2500.50, 'ES9121000418450200051332', 1),
(15000.75, 'ES2100812345678901234567', 1),
(500.00, 'ES7620770024003102575766', 2),
(3200.25, 'ES1000492352082414205416', 2),
(7500.00, 'ES7100302053091234567890', 3),
(12500.40, 'ES9000246912501234567891', 4),
(300.75, 'ES8521006742081234567892', 5);

-- Insertar tarjetas de crédito de ejemplo
INSERT INTO tarjetas_credito (numero_tarjeta, fecha_caducidad, cvv, nombre_completo, cuenta_id) VALUES
('4532123456789012', '2026-05-31', '123', 'JUAN PEREZ GARCIA', 1),
('5500123456789010', '2025-12-31', '456', 'JUAN PEREZ GARCIA', 1),
('4111111111111111', '2027-08-31', '789', 'MARIA LOPEZ FERNANDEZ', 3),
('378282246310005', '2024-11-30', '321', 'CARLOS RODRIGUEZ MARTINEZ', 5),
('371449635398431', '2028-02-28', '654', 'ANA GOMEZ SANCHEZ', 6),
('30569309025904', '2025-06-30', '987', 'LUIS FERNANDEZ DIAZ', 7),
('6011111111111117', '2026-09-30', '246', 'MARIA LOPEZ FERNANDEZ', 3),
('3530111333300000', '2027-03-31', '135', 'ANA GOMEZ SANCHEZ', 6);

