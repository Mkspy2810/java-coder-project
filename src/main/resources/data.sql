-- Insertar datos en la tabla clients
INSERT INTO clients (id, name, lastname, docnumber) VALUES (1, 'John', 'Doe', '12345678901');
INSERT INTO clients (id, name, lastname, docnumber) VALUES (2, 'Jane', 'Smith', '10987654321');
INSERT INTO clients (id, name, lastname, docnumber) VALUES (3, 'Alice', 'Johnson', '11223344556');

-- Insertar datos en la tabla products
INSERT INTO products (id, description, code, stock, price) VALUES (1, 'Laptop', 'LP1001', 10, 999.99);
INSERT INTO products (id, description, code, stock, price) VALUES (2, 'Smartphone', 'SP2002', 50, 599.99);
INSERT INTO products (id, description, code, stock, price) VALUES (3, 'Tablet', 'TB3003', 30, 299.99);

-- Insertar datos en la tabla invoice
INSERT INTO invoice (id, client_id, created_at, total) VALUES (1, 1, '2024-08-17 14:30:00', 1599.98);
INSERT INTO invoice (id, client_id, created_at, total) VALUES (2, 2, '2024-08-18 10:00:00', 299.99);

-- Insertar datos en la tabla invoice_details
INSERT INTO invoice_details (invoice_id, invoice_detail_id, amount, product_id, price) VALUES (1, 1, 1, 1, 999.99);
INSERT INTO invoice_details (invoice_id, invoice_detail_id, amount, product_id, price) VALUES (1, 2, 1, 2, 599.99);
INSERT INTO invoice_details (invoice_id, invoice_detail_id, amount, product_id, price) VALUES (2, 3, 1, 3, 299.99);
