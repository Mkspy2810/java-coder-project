# Java Coder Project API

Esta API permite la gestión de clientes, productos y facturas para una aplicación de ventas. A través de ella se pueden realizar operaciones CRUD sobre clientes, productos y facturas, además de verificar la consistencia de los datos mediante validaciones simples.

## Endpoints Principales:

### Clientes:

- `POST /clients`: Crear un nuevo cliente.

  - Ejemplo de request:
    ```json
    {
      "name": "John",
      "lastname": "Doe",
      "docnumber": "12345678901"
    }
    ```
  - Validaciones:
    - El campo `name` no puede estar vacío.
    - El campo `lastname` no puede estar vacío.
    - El campo `docnumber` debe tener 11 caracteres y ser único.

- `GET /clients/{id}`: Obtener los detalles de un cliente por su ID.

  - Respuesta en caso de éxito:
    ```json
    {
      "id": 1,
      "name": "John",
      "lastname": "Doe",
      "docnumber": "12345678901"
    }
    ```

- `GET /clients`: Obtener la lista de todos los clientes.

  - Respuesta en caso de éxito:
    ```json
    [
      {
        "id": 1,
        "name": "John",
        "lastname": "Doe",
        "docnumber": "12345678901"
      },
      {
        "id": 2,
        "name": "Jane",
        "lastname": "Smith",
        "docnumber": "98765432109"
      }
    ]
    ```

- `DELETE /clients/{id}`: Eliminar un cliente por su ID.
  - Respuesta en caso de éxito: Código HTTP 204 (No Content).

### Productos:

- `POST /products`: Crear un nuevo producto.

  - Ejemplo de request:
    ```json
    {
      "description": "Product 1",
      "code": "P001",
      "stock": 10,
      "price": 50.0
    }
    ```
  - Validaciones:
    - El campo `description` no puede estar vacío.
    - El campo `stock` debe ser mayor o igual a 0.
    - El campo `price` debe ser mayor que 0.

- `GET /products/{id}`: Obtener un producto por su ID.

  - Respuesta en caso de éxito:
    ```json
    {
      "id": 1,
      "description": "Product 1",
      "code": "P001",
      "stock": 10,
      "price": 50.0
    }
    ```

- `GET /products`: Obtener la lista de todos los productos.

  - Respuesta en caso de éxito:
    ```json
    [
      {
        "id": 1,
        "description": "Product 1",
        "code": "P001",
        "stock": 10,
        "price": 50.0
      },
      {
        "id": 2,
        "description": "Product 2",
        "code": "P002",
        "stock": 20,
        "price": 75.0
      }
    ]
    ```

- `DELETE /products/{id}`: Eliminar un producto por su ID.
  - Respuesta en caso de éxito: Código HTTP 204 (No Content).

### Facturas:

- `POST /invoices`: Crear una nueva factura.

  - Ejemplo de request:
    ```json
    {
      "client": {
        "id": 1
      },
      "createdAt": "2024-09-01T12:00:00Z",
      "total": 150.0,
      "invoiceDetails": [
        {
          "product": {
            "id": 1
          },
          "amount": 2,
          "price": 50.0
        }
      ]
    }
    ```
  - Validaciones:
    - El campo `total` debe ser mayor que 0.
    - La factura debe contener al menos un detalle.
    - Cada detalle de la factura debe tener:
      - Producto válido.
      - Cantidad (`amount`) mayor que 0.

- `GET /invoices/{id}`: Obtener una factura por su ID.

  - Respuesta en caso de éxito:
    ```json
    {
      "id": 1,
      "client": {
        "id": 1,
        "name": "John",
        "lastname": "Doe",
        "docnumber": "12345678901"
      },
      "createdAt": "2024-09-01T12:00:00Z",
      "total": 150.0,
      "invoiceDetails": [
        {
          "product": {
            "id": 1,
            "description": "Product 1"
          },
          "amount": 2,
          "price": 50.0
        }
      ]
    }
    ```

- `GET /invoices`: Obtener la lista de todas las facturas.

  - Respuesta en caso de éxito:
    ```json
    [
      {
        "id": 1,
        "client": {
          "id": 1,
          "name": "John",
          "lastname": "Doe",
          "docnumber": "12345678901"
        },
        "createdAt": "2024-09-01T12:00:00Z",
        "total": 150.0,
        "invoiceDetails": [
          {
            "product": {
              "id": 1,
              "description": "Product 1"
            },
            "amount": 2,
            "price": 50.0
          }
        ]
      }
    ]
    ```

- `DELETE /invoices/{id}`: Eliminar una factura por su ID.
  - Respuesta en caso de éxito: Código HTTP 204 (No Content).

## Validaciones:

- **Clientes**: El número de documento (`docnumber`) es único y debe tener 11 caracteres. Los campos `name` y `lastname` no pueden estar vacíos.
- **Productos**: El `stock` no puede ser negativo, y el `price` debe ser mayor que 0.
- **Facturas**: Las facturas deben tener al menos un detalle válido, con un `total` mayor que 0.

## Documentación Swagger:

La API incluye integración con Swagger para facilitar la documentación interactiva. Puedes acceder a la interfaz Swagger UI a través de la siguiente URL:

http://localhost:8080/swagger-ui.html

En Swagger podrás probar los endpoints y visualizar la documentación completa de la API, incluyendo los ejemplos de solicitudes y respuestas.
