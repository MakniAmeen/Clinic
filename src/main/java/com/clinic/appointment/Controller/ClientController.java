package com.clinic.appointment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.clinic.appointment.Model.*;
import com.clinic.appointment.Dao.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;


    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }


    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable Long id) {
        return clientRepository.findById(id);
    }


    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(clientDetails.getName());
                    client.setEmail(clientDetails.getEmail());
                    client.setPhone(clientDetails.getPhone());
                    client.setAge(clientDetails.getAge());
                    client.setGender(clientDetails.getGender());
                    return clientRepository.save(client);
                })
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }


    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "Client with ID " + id + " has been deleted.";
    }
}