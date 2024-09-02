package edu.coder.java_coder_project.service;

import edu.coder.java_coder_project.model.Client;
import edu.coder.java_coder_project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  public Client saveClient(Client client) {
    return clientRepository.save(client);
  }

  public Optional<Client> getClientById(int id) {
    return clientRepository.findById(id);
  }

  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }

  public void deleteClient(int id) {
    clientRepository.deleteById(id);
  }
}
