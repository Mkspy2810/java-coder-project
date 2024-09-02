package edu.coder.java_coder_project.controller;

import edu.coder.java_coder_project.model.Product;
import edu.coder.java_coder_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product savedProduct = productService.saveProduct(product);
    return ResponseEntity.ok(savedProduct);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable int id) {
    Optional<Product> product = productService.getProductById(id);
    return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(products);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
