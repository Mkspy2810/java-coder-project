package edu.coder.java_coder_project.controller;

import edu.coder.java_coder_project.model.Product;
import edu.coder.java_coder_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Operation(summary = "Crear un nuevo producto", description = "Permite crear un producto en el sistema")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Producto creado exitosamente"),
      @ApiResponse(responseCode = "400", description = "Solicitud inválida")
  })
  @PostMapping
  public ResponseEntity<?> createProduct(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Producto a crear", required = true, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\n"
          +
          "  \"description\": \"Product 1\",\n" +
          "  \"code\": \"P001\",\n" +
          "  \"stock\": 10,\n" +
          "  \"price\": 50.00\n" +
          "}"))) @RequestBody Product product) {
    if (product.getDescription() == null || product.getDescription().isEmpty()) {
      return ResponseEntity.badRequest().body("La descripción del producto no puede estar vacía.");
    }
    if (product.getStock() < 0) {
      return ResponseEntity.badRequest().body("El stock no puede ser negativo.");
    }
    if (product.getPrice() <= 0) {
      return ResponseEntity.badRequest().body("El precio debe ser mayor que 0.");
    }
    Product savedProduct = productService.saveProduct(product);
    return ResponseEntity.ok(savedProduct);
  }

  @Operation(summary = "Obtener un producto por ID", description = "Permite obtener los detalles de un producto usando su ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Producto encontrado"),
      @ApiResponse(responseCode = "404", description = "Producto no encontrado")
  })
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable int id) {
    Optional<Product> product = productService.getProductById(id);
    return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Listar todos los productos", description = "Permite obtener la lista completa de productos")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
  })
  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @Operation(summary = "Eliminar un producto", description = "Permite eliminar un producto por su ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
      @ApiResponse(responseCode = "404", description = "Producto no encontrado")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
