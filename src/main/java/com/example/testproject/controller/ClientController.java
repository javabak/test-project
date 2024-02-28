package com.example.testproject.controller;

import com.example.testproject.dto.ClientDTO;
import com.example.testproject.entity.Client;
import com.example.testproject.factory.ClientDtoFactory;
import com.example.testproject.service.ClientService;
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
public class ClientController {

    ClientService clientService;
    ClientDtoFactory clientDtoFactory;

    public ClientController(ClientService clientService, ClientDtoFactory clientDtoFactory) {
        this.clientService = clientService;
        this.clientDtoFactory = clientDtoFactory;
    }

    @PostMapping("/addClient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully saved the client"),
            @ApiResponse(responseCode = "400", description = "bad request"),
    })
    @Operation(description = "save the client")
    public ResponseEntity<ClientDTO> addClient(@RequestBody Client client) {
        log.info("saved client - {}", client);
        return ResponseEntity.ok(clientDtoFactory.makeClientDto(clientService.addClient(client)));
    }

    @GetMapping("/getAllClients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully got all clients"),
            @ApiResponse(responseCode = "404", description = "clients not found"),
    })
    @Operation(description = "get all clients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        log.info("got all clients");
        return ResponseEntity.ok(clientDtoFactory.makeClientListDto(clientService.getAllClients()));
    }

    @GetMapping("/getClientById/{clientId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully got client"),
            @ApiResponse(responseCode = "404", description = "client with id not found"),
    })
    @Operation(description = "get client by id")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long clientId) {
        log.info("got client by id");
        return ResponseEntity.ok(clientDtoFactory.makeClientDto(clientService.getClientById(clientId)));
    }

}
