package edu.coder.java_coder_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.coder.java_coder_project.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
