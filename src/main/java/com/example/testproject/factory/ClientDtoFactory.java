package com.example.testproject.factory;

import com.example.testproject.dto.ClientDTO;
import com.example.testproject.entity.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientDtoFactory {

    public ClientDTO makeClientDto(Client client) {
        return ClientDTO
                .builder()
                .name(client.getName())
                .build();
    }

    public List<ClientDTO> makeClientListDto(List<Client> clients) {
        return clients.stream().map(this::makeClientDto).collect(Collectors.toList());
    }
}
