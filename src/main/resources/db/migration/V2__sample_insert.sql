-- V2__insert_test_data.sql

-- Insertar clientes de prueba
INSERT INTO clientes (login, password, name, last_name1, last_name2, dni, api_token) VALUES
                                                                                         ('juan.perez', 'password123', 'Juan', 'Pérez', 'García', '12345678A', 'token_juan_123'),
                                                                                         ('maria.lopez', 'securepass456', 'María', 'López', 'Martínez', '87654321B', 'token_maria_456'),
                                                                                         ('carlos.gomez', 'testpass789', 'Carlos', 'Gómez', 'Sánchez', '11223344C', 'token_carlos_789'),
                                                                                         ('ana.rodriguez', 'anapass101', 'Ana', 'Rodríguez', 'Fernández', '55667788D', 'token_ana_101'),
                                                                                         ('luis.martin', 'luispass202', 'Luis', 'Martín', 'Ruiz', '99887766E', 'token_luis_202');

-- Insertar cuentas de prueba
INSERT INTO cuentas (saldo, iban, client_id) VALUES
                                                 (1500.75, 'ES9121000418450200051332', 1),
                                                 (2800.50, 'ES8521000418450200051333', 2),
                                                 (500.25, 'ES7921000418450200051334', 2),
                                                 (10000.00, 'ES7321000418450200051335', 3),
                                                 (750.00, 'ES6721000418450200051336', 4),
                                                 (3200.00, 'ES6121000418450200051337', 5),
                                                 (125.50, 'ES5521000418450200051338', 1);

-- Insertar tarjetas de crédito de prueba
INSERT INTO tarjetas_credito (numero_tarjeta, fecha_caducidad, cvv, nombre_completo, cuenta_id) VALUES
                                                                                                    ('4532123456789012', '12/25', '123', 'JUAN PEREZ GARCIA', 1),
                                                                                                    ('5555666677778888', '06/26', '456', 'JUAN PEREZ GARCIA', 1),
                                                                                                    ('4111111111111111', '09/24', '789', 'MARIA LOPEZ MARTINEZ', 2),
                                                                                                    ('378282246310005', '03/27', '321', 'CARLOS GOMEZ SANCHEZ', 4),
                                                                                                    ('6011111111111117', '11/25', '654', 'ANA RODRIGUEZ FERNANDEZ', 5),
                                                                                                    ('3530111333300000', '08/26', '987', 'LUIS MARTIN RUIZ', 6),
                                                                                                    ('2223000048400011', '05/27', '147', 'MARIA LOPEZ MARTINEZ', 3);