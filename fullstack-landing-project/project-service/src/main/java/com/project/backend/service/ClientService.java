package com.project.backend.service;

import com.project.backend.entity.Client;

import java.util.List;

/**
 * Service interface for managing Client entities.
 * Defines the business logic operations for clients.
 */
public interface ClientService {

    /**
     * Creates and saves a new client.
     *
     * @param client The client entity to save.
     * @return The saved client entity (with ID).
     */
    Client createClient(Client client);

    /**
     * Retrieves all clients.
     *
     * @return A list of all client entities.
     */
    List<Client> getAllClients();

    /**
     * Retrieves a client by its ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The found client entity.
     * @throws com.project.backend.exception.ResourceNotFoundException if client not found.
     */
    Client getClientById(String id);

    /**
     * Updates an existing client.
     *
     * @param id             The ID of the client to update.
     * @param clientDetails The client object with updated details.
     * @return The updated client entity.
     * @throws com.project.backend.exception.ResourceNotFoundException if client not found.
     */
    Client updateClient(String id, Client clientDetails);

    /**
     * Deletes a client by its ID.
     *
     * @param id The ID of the client to delete.
     * @throws com.project.backend.exception.ResourceNotFoundException if client not found.
     */
    void deleteClient(String id);
}