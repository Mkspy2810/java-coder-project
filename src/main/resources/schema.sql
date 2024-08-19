CREATE TABLE IF NOT EXISTS clients (
    id INT PRIMARY KEY,
    name VARCHAR(75),
    lastname VARCHAR(75),
    docnumber VARCHAR(11)
);

CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY,
    description VARCHAR(150),
    code VARCHAR(50),
    stock INT,
    price DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS invoice (
    id INT PRIMARY KEY,
    client_id INT,
    created_at TIMESTAMP,
    total DECIMAL(10, 2),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE IF NOT EXISTS invoice_details (
    invoice_id INT,
    invoice_detail_id INT PRIMARY KEY,
    amount INT,
    product_id INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
