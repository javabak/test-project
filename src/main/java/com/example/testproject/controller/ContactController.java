package com.example.testproject.controller;

import com.example.testproject.dto.ContactDTO;
import com.example.testproject.entity.Client;
import com.example.testproject.entity.Contact;
import com.example.testproject.factory.ContactDtoFactory;
import com.example.testproject.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/v1/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContactController {

    ContactService contactService;
    ContactDtoFactory contactDtoFactory;

    public ContactController(ContactService contactService, ContactDtoFactory contactDtoFactory) {
        this.contactService = contactService;
        this.contactDtoFactory = contactDtoFactory;
    }

    @PostMapping("/addContact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully saved contact"),
            @ApiResponse(responseCode = "400", description = "bad request"),
    })
    @Operation(description = "save contact")
    public ResponseEntity<ContactDTO> addContact(@RequestBody Contact contact) {
        log.info("saved contact");
        return ResponseEntity.ok(contactDtoFactory.makeContactDto(contactService.addContact(contact)));
    }


    @GetMapping("/getAllContactsByClientAndType")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully got all contacts by client and contact type"),
            @ApiResponse(responseCode = "404", description = "clients not found"),
    })
    @Operation(description = "get all contacts by client and contact type")
    public ResponseEntity<List<ContactDTO>> getContactsByClientAndType(@RequestBody Client client,
                                                                       @RequestParam String contactType) {
        log.info("get all contacts by client and type");
        return ResponseEntity.ok(contactDtoFactory.makeContactListDto(contactService.getContactsByClientAndType(client, contactType)));
    }

    @GetMapping("/getAllContactsByClient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully got all contacts client"),
            @ApiResponse(responseCode = "404", description = "contacts not found"),
    })
    @Operation(description = "get all contacts client")
    public ResponseEntity<List<ContactDTO>> getContactsByClient(@RequestBody Client client) {
        log.info("get all contacts by client");
        return ResponseEntity.ok(contactDtoFactory.makeContactListDto(contactService.getContactsByClient(client)));
    }
}
