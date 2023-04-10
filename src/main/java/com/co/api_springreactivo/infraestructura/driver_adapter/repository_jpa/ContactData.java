package com.co.api_springreactivo.infraestructura.driver_adapter.repository_jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "contact")
@AllArgsConstructor
public class ContactData {

    private String id;
    private String name;
    private String email;
    private String telephone;
}
