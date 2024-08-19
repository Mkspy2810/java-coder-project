package edu.coder.java_coder_project.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Entity class representing a product in the system.
 */
@Entity
@Table(name = "PRODUCTS")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "DESCRIPTION", length = 150, nullable = false)
  private String description;

  @Column(name = "CODE", length = 50, nullable = false, unique = true)
  private String code;

  @Column(name = "STOCK", nullable = false)
  private int stock;

  @Column(name = "PRICE", nullable = false)
  private double price;

  @OneToMany(mappedBy = "product")
  private List<InvoiceDetail> invoiceDetails;

  // Constructors
  public Product() {
  }

  public Product(String description, String code, int stock, double price) {
    this.description = description;
    this.code = code;
    this.stock = stock;
    this.price = price;
  }

  // Getters and Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public List<InvoiceDetail> getInvoiceDetails() {
    return invoiceDetails;
  }

  public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
    this.invoiceDetails = invoiceDetails;
  }

  // Methods

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Product product = (Product) obj;
    return id == product.id && stock == product.stock && Double.compare(product.price, price) == 0
        && Objects.equals(description, product.description) && Objects.equals(code, product.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, code, stock, price);
  }

  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", description='" + description + '\'' + ", code='" + code + '\''
        + ", stock=" + stock + ", price=" + price + '}';
  }
}
