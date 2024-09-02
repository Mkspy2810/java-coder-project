package edu.coder.java_coder_project.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

/**
 * Entity class representing the details of an invoice in the system.
 */
@Entity
@Table(name = "INVOICE_DETAILS")
public class InvoiceDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int invoiceDetailId;

  @JsonBackReference("invoice-invoiceDetails")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "INVOICE_ID", nullable = false)
  private Invoice invoice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRODUCT_ID", nullable = false)
  private Product product;

  @Column(name = "AMOUNT", nullable = false)
  private int amount;

  @Column(name = "PRICE", nullable = false)
  private double price;

  // Constructors
  public InvoiceDetail() {
  }

  public InvoiceDetail(Invoice invoice, Product product, int amount, double price) {
    this.invoice = invoice;
    this.product = product;
    this.amount = amount;
    this.price = price;
  }

  // Getters and Setters
  public int getInvoiceDetailId() {
    return invoiceDetailId;
  }

  public void setInvoiceDetailId(int invoiceDetailId) {
    this.invoiceDetailId = invoiceDetailId;
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
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
    InvoiceDetail that = (InvoiceDetail) obj;
    return invoiceDetailId == that.invoiceDetailId &&
        amount == that.amount &&
        Double.compare(that.price, price) == 0 &&
        Objects.equals(invoice, that.invoice) &&
        Objects.equals(product, that.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoiceDetailId, invoice, product, amount, price);
  }

  @Override
  public String toString() {
    return "InvoiceDetail{" +
        "invoiceDetailId=" + invoiceDetailId +
        ", invoice=" + invoice +
        ", product=" + product +
        ", amount=" + amount +
        ", price=" + price +
        '}';
  }
}
