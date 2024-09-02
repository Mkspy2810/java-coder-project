package edu.coder.java_coder_project.service;

import edu.coder.java_coder_project.model.Invoice;
import edu.coder.java_coder_project.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

  @Autowired
  private InvoiceRepository invoiceRepository;

  public Invoice saveInvoice(Invoice invoice) {
    return invoiceRepository.save(invoice);
  }

  public Optional<Invoice> getInvoiceById(int id) {
    return invoiceRepository.findById(id);
  }

  public List<Invoice> getAllInvoices() {
    return invoiceRepository.findAll();
  }

  public void deleteInvoice(int id) {
    invoiceRepository.deleteById(id);
  }
}
