package edu.coder.java_coder_project.service;

import edu.coder.java_coder_project.model.Product;
import edu.coder.java_coder_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> getProductById(int id) {
    return productRepository.findById(id);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public void deleteProduct(int id) {
    productRepository.deleteById(id);
  }
}
