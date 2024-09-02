package edu.coder.java_coder_project.controller;

import edu.coder.java_coder_project.model.Client;
import edu.coder.java_coder_project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @PostMapping
  public ResponseEntity<Client> createClient(@RequestBody Client client) {
    Client savedClient = clientService.saveClient(client);
    return ResponseEntity.ok(savedClient);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Client> getClientById(@PathVariable int id) {
    Optional<Client> client = clientService.getClientById(id);
    return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Client>> getAllClients() {
    List<Client> clients = clientService.getAllClients();
    return ResponseEntity.ok(clients);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable int id) {
    clientService.deleteClient(id);
    return ResponseEntity.noContent().build();
  }
}
