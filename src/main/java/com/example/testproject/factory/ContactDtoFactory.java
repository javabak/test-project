package com.example.testproject.factory;

import com.example.testproject.dto.ContactDTO;
import com.example.testproject.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactDtoFactory {

    public ContactDTO makeContactDto(Contact contact) {
        return ContactDTO
                .builder()
                .client(contact.getClient())
                .contactType(contact.getContactType())
                .contactValue(contact.getContactValue())
                .build();
    }

    public List<ContactDTO> makeContactListDto(List<Contact> contacts) {
        return contacts.stream().map(this::makeContactDto).collect(Collectors.toList());
    }
}
