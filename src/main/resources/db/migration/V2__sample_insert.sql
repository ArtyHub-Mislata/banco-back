-- Insertar datos de ejemplo en clientes
INSERT INTO clientes (id, login, password, name, last_name1, last_name2, dni, api_token) VALUES
                                                                                             (1, 'cliente1', 'password1', 'Juan', 'García', 'López', '12345678A', 'apitoken_1'),
                                                                                             (2, 'cliente2', 'password2', 'María', 'Rodríguez', 'Sánchez', '87654321B', 'apitoken_2'),
                                                                                             (3, 'cliente3', 'password3', 'Carlos', 'Martínez', 'Fernández', '11223344C', 'apitoken_3'),
                                                                                             (4, 'cliente4', 'password4', 'Ana', 'Pérez', 'Gómez', '44332211D', 'apitoken_4'),
                                                                                             (5, 'cliente5', 'password5', 'Luis', 'González', 'Ruiz', '55667788E', 'apitoken_5');

-- Insertar datos de ejemplo en cuentas
INSERT INTO cuentas (id, saldo, iban, client_id) VALUES
                                                     (1, 1500.50, 'ES9121000418450200051332', 1),
                                                     (2, 2750.00, 'ES6621000418401234567891', 1),
                                                     (3, 850.75, 'ES7921000813610123456789', 2),
                                                     (4, 3200.25, 'ES1421000418400200054321', 3),
                                                     (5, 125.00, 'ES8521000418400300067890', 4),
                                                     (6, 4500.00, 'ES9121000418450200098765', 5);

-- Insertar datos de ejemplo en tarjetas_credito
INSERT INTO tarjetas_credito (id, numero_tarjeta, fecha_caducidad, cvv, nombre_completo, cuenta_id) VALUES
                                                                                                        (1, '4532123456789012', '2025-12-31', '123', 'JUAN GARCÍA LÓPEZ', 1),
                                                                                                        (2, '5555123456789010', '2024-11-30', '456', 'JUAN GARCÍA LÓPEZ', 2),
                                                                                                        (3, '4111111111111111', '2026-03-31', '789', 'MARÍA RODRÍGUEZ SÁNCHEZ', 3),
                                                                                                        (4, '378282246310005', '2025-08-31', '234', 'CARLOS MARTÍNEZ FERNÁNDEZ', 4),
                                                                                                        (5, '6011111111111117', '2027-05-31', '567', 'ANA PÉREZ GÓMEZ', 5),
                                                                                                        (6, '3530111333300000', '2024-10-31', '890', 'LUIS GONZÁLEZ RUIZ', 6);

-- NOTA: Las tablas sesions y movimientos_bancarios se dejan vacías como se solicitó