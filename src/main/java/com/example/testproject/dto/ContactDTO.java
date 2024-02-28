package com.example.testproject.dto;

import com.example.testproject.entity.Client;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactDTO {

    Client client;
    String contactType;
    String contactValue;
}
