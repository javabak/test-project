package com.example.testproject.repository;

import com.example.testproject.entity.Client;
import com.example.testproject.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByClient(Client client);
    List<Contact> findByClientAndContactType(Client client, String contactType);
}
