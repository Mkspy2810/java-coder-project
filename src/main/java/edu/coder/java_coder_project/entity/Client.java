package edu.coder.java_coder_project.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity class representing a client in the system.
 */
@Entity
@Table(name = "CLIENTS")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "NAME", length = 75, nullable = false)
  private String name;

  @Column(name = "LASTNAME", length = 75, nullable = false)
  private String lastname;

  @Column(name = "DOCNUMBER", length = 11, nullable = false, unique = true)
  private String docnumber;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Invoice> invoices = new ArrayList<>();;

  // Constructors
  public Client() {
  }

  public Client(String name, String lastname, String docnumber) {
    this.name = name;
    this.lastname = lastname;
    this.docnumber = docnumber;
    this.invoices = new ArrayList<>();
  }

  // Getters and Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getDocnumber() {
    return docnumber;
  }

  public void setDocnumber(String docnumber) {
    this.docnumber = docnumber;
  }

  public List<Invoice> getInvoices() {
    return invoices;
  }

  public void setInvoices(List<Invoice> invoices) {
    this.invoices = invoices;
  }

  // Methods

  public void addInvoice(Invoice invoice) {
    invoices.add(invoice);
    invoice.setClient(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Client client = (Client) obj;
    return id == client.id && Objects.equals(docnumber, client.docnumber) && Objects.equals(name, client.name)
        && Objects.equals(lastname, client.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname, docnumber);
  }

  @Override
  public String toString() {
    return "Client{" + "id=" + id + ", name='" + name + '\'' + ", lastname='" + lastname + '\''
        + ", docnumber='" + docnumber + '\'' + '}';
  }
}
