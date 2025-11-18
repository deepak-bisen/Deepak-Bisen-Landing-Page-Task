package com.project.backend.controller;

import com.project.backend.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClientController {
    /**
     * POST /api/clients : Create a new client.
     */
    public ResponseEntity<Client> createClient( Client client);

    /**
     * GET /api/clients : Get all clients.
     */
    public ResponseEntity<List<Client>> getAllClients();

    /**
     * GET /api/clients/{id} : Get a single client by ID.
     */
    public ResponseEntity<Client> getClientById( String id);


    /**
     * PUT /api/clients/{id} : Update an existing client.
     */
    public ResponseEntity<Client> updateClient( String id, Client clientDetails);

    /**
     * DELETE /api/clients/{id} : Delete a client by ID.
     */
    public ResponseEntity<?> deleteClient( String id);
}
