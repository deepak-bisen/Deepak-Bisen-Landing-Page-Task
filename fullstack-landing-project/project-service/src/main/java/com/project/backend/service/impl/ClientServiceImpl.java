package com.project.backend.service.impl;

import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.repository.ClientRepository;
import com.project.backend.entity.Client;
import com.project.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    @Override
    public Client updateClient(String id, Client clientDetails) {
        Client client = getClientById(id); // Re-use getById to handle not-found case

        client.setName(clientDetails.getName());
        client.setAvatarUrl(clientDetails.getAvatarUrl());
        client.setTestimonial(clientDetails.getTestimonial());
        client.setCompany(clientDetails.getCompany());
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(String id) {
        Client client = getClientById(id); // Check if client exists before deleting
        clientRepository.delete(client);
    }

}
