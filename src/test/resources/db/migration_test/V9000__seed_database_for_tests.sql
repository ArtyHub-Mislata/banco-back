-- V2__insert_test_data.sql

-- Insertar clientes de prueba
INSERT INTO clientes (login, password, name, last_name1, last_name2, dni, api_token) VALUES
                                                                                         ('juan.perez', 'password123', 'Juan', 'Pérez', 'García', '12345678A', 'token_juan_123'),
                                                                                         ('maria.lopez', 'securepass456', 'María', 'López', 'Martínez', '87654321B', 'token_maria_456'),
                                                                                         ('carlos.gomez', 'testpass789', 'Carlos', 'Gómez', 'Sánchez', '11223344C', 'token_carlos_789');

-- Insertar cuentas de prueba
INSERT INTO cuentas (saldo, iban, client_id) VALUES
                                                 (1500.75, 'ES9121000418450200051332', 1),
                                                 (2800.50, 'ES8521000418450200051333', 2),
                                                 (500.25, 'ES7921000418450200051334', 2);

-- Insertar tarjetas de crédito de prueba
INSERT INTO tarjetas_credito (numero_tarjeta, fecha_caducidad, cvv, nombre_completo, cuenta_id) VALUES
                                                                                                    ('4532123456789012', '12/25', '123', 'JUAN PEREZ GARCIA', 1),
                                                                                                    ('5555666677778888', '06/26', '456', 'JUAN PEREZ GARCIA', 1),
                                                                                                    ('4111111111111111', '09/24', '789', 'MARIA LOPEZ MARTINEZ', 2);

-- Insertar movimientos bancarios de prueba
INSERT INTO movimientos_bancarios (tipo_movimiento, origen_movimiento, tarjeta_credito_id, importe, concepto, cuenta_id) VALUES
                                                                                                    ('DEBE', 'TARJETABANCARIA', 1, 100.00, 'ingreso', 1),
                                                                                                    ('HABER', 'TRANSFERENCIA', 2, 200.00, 'retirada', 2),
                                                                                                    ('DEBE', 'TARJETABANCARIA', 3, 300.00, 'transferencia', 3);
                                                                                                
-- Insertar sesiones de prueba
INSERT INTO sesions (token, client_id) VALUES
                                               ('token_juan_123', 1),
                                               ('token_maria_456', 2),
                                               ('token_carlos_789', 3);