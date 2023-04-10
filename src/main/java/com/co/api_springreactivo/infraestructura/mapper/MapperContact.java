package com.co.api_springreactivo.infraestructura.mapper;

import com.co.api_springreactivo.documents.Contact;
import com.co.api_springreactivo.infraestructura.driver_adapter.repository_jpa.ContactData;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class MapperContact {

    public Flux<Contact> toContactFlux(Flux<ContactData> contactData){
        return contactData.map(c -> new Contact(c.getId(),c.getName(),c.getEmail(),c.getTelephone()));
    }

    public Mono<Contact> toContactMono(Mono<ContactData> contactData){
        return contactData.map(c -> new Contact(c.getId(),c.getName(),c.getEmail(),c.getTelephone()));
    }

    public ContactData toContactData(Contact contact){
        return new ContactData(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getTelephone());
    }
}
