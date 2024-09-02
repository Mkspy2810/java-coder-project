package edu.coder.java_coder_project.controller;

import edu.coder.java_coder_project.model.Invoice;
import edu.coder.java_coder_project.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

  @Autowired
  private InvoiceService invoiceService;

  @PostMapping
  public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
    Invoice savedInvoice = invoiceService.saveInvoice(invoice);
    return ResponseEntity.ok(savedInvoice);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Invoice> getInvoiceById(@PathVariable int id) {
    Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
    return invoice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Invoice>> getAllInvoices() {
    List<Invoice> invoices = invoiceService.getAllInvoices();
    return ResponseEntity.ok(invoices);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteInvoice(@PathVariable int id) {
    invoiceService.deleteInvoice(id);
    return ResponseEntity.noContent().build();
  }
}
