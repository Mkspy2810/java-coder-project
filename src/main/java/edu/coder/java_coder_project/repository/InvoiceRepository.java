package edu.coder.java_coder_project.repository;

import edu.coder.java_coder_project.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
