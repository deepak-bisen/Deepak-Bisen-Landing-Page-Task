package com.project.backend.controller;

import com.project.backend.entity.Client;
import com.project.backend.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients") // Base path for all client-related APIs
public class ClientControllerImpl implements ClientController { // Fixed class name

    @Autowired
    private ClientServiceImpl clientService;

    /**
     * POST /api/clients : Create a new client.
     */
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.createClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    /**
     * GET /api/clients : Get all clients.
     */
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    /**
     * GET /api/clients/{id} : Get a single client by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    /**
     * PUT /api/clients/{id} : Update an existing client.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody Client clientDetails) {
        Client updatedClient = clientService.updateClient(id, clientDetails);
        return ResponseEntity.ok(updatedClient);
    }

    /**
     * DELETE /api/clients/{id} : Delete a client by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build(); // Return 200 OK with no body
    }
}