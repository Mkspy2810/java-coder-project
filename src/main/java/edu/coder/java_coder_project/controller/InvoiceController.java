package edu.coder.java_coder_project.controller;

import edu.coder.java_coder_project.model.Invoice;
import edu.coder.java_coder_project.model.InvoiceDetail;
import edu.coder.java_coder_project.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
@Tag(name = "Facturas", description = "Operaciones relacionadas con las facturas")
public class InvoiceController {

  @Autowired
  private InvoiceService invoiceService;

  @Operation(summary = "Crear una nueva factura", description = "Permite crear una factura en el sistema")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Factura creada exitosamente"),
      @ApiResponse(responseCode = "400", description = "Solicitud inválida")
  })
  @PostMapping
  public ResponseEntity<?> createInvoice(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Factura a crear", required = true, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\n"
          +
          "  \"client\": {\n" +
          "    \"id\": 1\n" +
          "  },\n" +
          "  \"createdAt\": \"2024-09-01T12:00:00Z\",\n" +
          "  \"total\": 150.00,\n" +
          "  \"invoiceDetails\": [\n" +
          "    {\n" +
          "      \"product\": {\n" +
          "        \"id\": 1\n" +
          "      },\n" +
          "      \"amount\": 2,\n" +
          "      \"price\": 50.00\n" +
          "    }\n" +
          "  ]\n" +
          "}"))) @RequestBody Invoice invoice) {
    if (invoice.getTotal() <= 0) {
      return ResponseEntity.badRequest().body("El total de la factura debe ser mayor que 0.");
    }
    if (invoice.getInvoiceDetails() == null || invoice.getInvoiceDetails().isEmpty()) {
      return ResponseEntity.badRequest().body("La factura debe tener al menos un detalle.");
    }
    for (InvoiceDetail detail : invoice.getInvoiceDetails()) {
      if (detail.getProduct() == null || detail.getProduct().getId() <= 0) {
        return ResponseEntity.badRequest().body("Cada detalle debe tener un producto válido.");
      }
      if (detail.getAmount() <= 0) {
        return ResponseEntity.badRequest().body("La cantidad de cada detalle debe ser mayor que 0.");
      }
    }
    Invoice savedInvoice = invoiceService.saveInvoice(invoice);
    return ResponseEntity.ok(savedInvoice);
  }

  @Operation(summary = "Obtener una factura por ID", description = "Permite obtener los detalles de una factura usando su ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Factura encontrada"),
      @ApiResponse(responseCode = "404", description = "Factura no encontrada")
  })
  @GetMapping("/{id}")
  public ResponseEntity<Invoice> getInvoiceById(@PathVariable int id) {
    Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
    return invoice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Listar todas las facturas", description = "Permite obtener la lista completa de facturas")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida exitosamente")
  })
  @GetMapping
  public ResponseEntity<List<Invoice>> getAllInvoices() {
    List<Invoice> invoices = invoiceService.getAllInvoices();
    return ResponseEntity.ok(invoices);
  }

  @Operation(summary = "Eliminar una factura", description = "Permite eliminar una factura por su ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Factura eliminada exitosamente"),
      @ApiResponse(responseCode = "404", description = "Factura no encontrada")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteInvoice(@PathVariable int id) {
    invoiceService.deleteInvoice(id);
    return ResponseEntity.noContent().build();
  }
}
