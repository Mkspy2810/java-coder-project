package edu.coder.java_coder_project.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Entity class representing an invoice in the system.
 */
@Entity
@Table(name = "INVOICE")
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JsonBackReference("client-invoices")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CLIENT_ID", nullable = false)
  private Client client;

  @Column(name = "CREATED_AT", nullable = false)
  private Date createdAt;

  @Column(name = "TOTAL", nullable = false)
  private double total;

  @JsonManagedReference("invoice-invoiceDetails")
  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<InvoiceDetail> invoiceDetails = new ArrayList<>();

  // Constructors
  public Invoice() {
  }

  public Invoice(Client client, Date createdAt, double total) {
    this.client = client;
    this.createdAt = createdAt;
    this.total = total;
  }

  // Getters and Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public List<InvoiceDetail> getInvoiceDetails() {
    return invoiceDetails;
  }

  public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
    this.invoiceDetails = invoiceDetails;
  }

  // Methods
  public void addInvoiceDetail(InvoiceDetail invoiceDetail) {
    invoiceDetails.add(invoiceDetail);
    invoiceDetail.setInvoice(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Invoice invoice = (Invoice) obj;
    return id == invoice.id && Double.compare(invoice.total, total) == 0 && Objects.equals(client, invoice.client)
        && Objects.equals(createdAt, invoice.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, client, createdAt, total);
  }

  @Override
  public String toString() {
    return "Invoice{" +
        "id=" + id +
        ", client=" + client +
        ", createdAt=" + createdAt +
        ", total=" + total +
        '}';
  }
}
