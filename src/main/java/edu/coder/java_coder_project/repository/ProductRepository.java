package edu.coder.java_coder_project.repository;

import edu.coder.java_coder_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
