package edu.coder.java_coder_project.controller;

import edu.coder.java_coder_project.model.Client;
import edu.coder.java_coder_project.service.ClientService;
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
@RequestMapping("/clients")
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @Operation(summary = "Crear un nuevo cliente", description = "Permite crear un cliente en el sistema")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cliente creado exitosamente"),
      @ApiResponse(responseCode = "400", description = "Solicitud inválida")
  })
  @PostMapping
  public ResponseEntity<?> createClient(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cliente a crear", required = true, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\n"
          +
          "  \"name\": \"John\",\n" +
          "  \"lastname\": \"Doe\",\n" +
          "  \"docnumber\": \"12345678901\"\n" +
          "}"))) @RequestBody Client client) {

    if (client.getName() == null || client.getName().isEmpty()) {
      return ResponseEntity.badRequest().body("El nombre no puede estar vacío.");
    }
    if (client.getLastname() == null || client.getLastname().isEmpty()) {
      return ResponseEntity.badRequest().body("El apellido no puede estar vacío.");
    }
    if (client.getDocnumber() == null || client.getDocnumber().isEmpty() || client.getDocnumber().length() != 11) {
      return ResponseEntity.badRequest().body("El número de documento debe tener 11 caracteres.");
    }

    Client savedClient = clientService.saveClient(client);
    return ResponseEntity.ok(savedClient);
  }

  @Operation(summary = "Obtener un cliente por ID", description = "Permite obtener los detalles de un cliente usando su ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
      @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
  })
  @GetMapping("/{id}")
  public ResponseEntity<Client> getClientById(@PathVariable int id) {
    Optional<Client> client = clientService.getClientById(id);
    return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Listar todos los clientes", description = "Permite obtener la lista completa de clientes")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente")
  })
  @GetMapping
  public ResponseEntity<List<Client>> getAllClients() {
    List<Client> clients = clientService.getAllClients();
    return ResponseEntity.ok(clients);
  }

  @Operation(summary = "Eliminar un cliente", description = "Permite eliminar un cliente por su ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
      @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable int id) {
    clientService.deleteClient(id);
    return ResponseEntity.noContent().build();
  }
}
