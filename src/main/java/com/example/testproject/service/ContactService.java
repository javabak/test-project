package com.example.testproject.service;

import com.example.testproject.entity.Client;
import com.example.testproject.entity.Contact;
import com.example.testproject.exception.BadRequestException;
import com.example.testproject.exception.ClientNotFoundException;
import com.example.testproject.repository.ClientRepository;
import com.example.testproject.repository.ContactRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.testproject.util.InputValidate.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContactService {

    ContactRepository contactRepository;
    ClientRepository clientRepository;

    public ContactService(ContactRepository contactRepository, ClientRepository clientRepository) {
        this.contactRepository = contactRepository;
        this.clientRepository = clientRepository;
    }

    @Cacheable(key = "#client.id", value = "client-id")
    public List<Contact> getContactsByClient(Client client) {
        if (validateId(client.getId())) {
            Optional<Client> optionalClient = clientRepository.findById(client.getId());
            if (optionalClient.isPresent()) {
                return contactRepository.findByClient(client);
            } else {
                throw new ClientNotFoundException("contacts by this client not found");
            }
        } else {
            throw new BadRequestException("invalid client data");
        }
    }

    @Cacheable(key = "#client.id", value = "client-id-and-contact-type")
    public List<Contact> getContactsByClientAndType(Client client, String contactType) {
        if (validateId(client.getId()) && validateContactType(contactType)) {
            Optional<Client> optionalClient = clientRepository.findById(client.getId());
            if (optionalClient.isPresent()) {
                return contactRepository.findByClientAndContactType(client, contactType);
            } else {
                throw new ClientNotFoundException("contacts by this client not found");
            }
        } else {
            throw new BadRequestException("invalid client data");
        }
    }

    public Contact addContact(Contact contact) {
        if (validateContactType(contact.getContactType()) &&
            (validateEmail(contact.getContactValue()) || validateEmail(contact.getContactValue()))) {
            return contactRepository.save(contact);
        } else {
            throw new BadRequestException("Invalid email or phone number");
        }
    }
}
