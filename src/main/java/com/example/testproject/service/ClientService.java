package com.example.testproject.service;

import com.example.testproject.entity.Client;
import com.example.testproject.exception.BadRequestException;
import com.example.testproject.exception.ClientNotFoundException;
import com.example.testproject.repository.ClientRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.testproject.util.InputValidate.validateId;
import static com.example.testproject.util.InputValidate.validateName;

@Log4j2
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientService {

    ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client) {
        if (validateName(client.getName())) {
            return clientRepository.save(client);
        } else {
            throw new BadRequestException("invalid name: " + client.getName());
        }
    }

    @Cacheable(key = "#clientRepository.findAll()", value = "all-clients")
    public List<Client> getAllClients() {
        if (clientRepository.findAll().isEmpty()) {
            return clientRepository.findAll();
        } else {
            throw new ClientNotFoundException("clients not found");
        }
    }

    @Cacheable(key = "#clientId", value = "client-id")
    public Client getClientById(Long clientId) {
        if (validateId(clientId)) {
            return clientRepository.findById(clientId)
                    .orElseThrow(() -> new ClientNotFoundException("client " + clientId + "not found"));
        } else {
            throw new BadRequestException("invalid client id " + clientId);
        }
    }
}