package com.co.api_springreactivo.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
public class Contact {

    private String id;
    private String name;
    private String email;
    private String telephone;
}
