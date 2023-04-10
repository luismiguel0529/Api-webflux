package com.co.api_springreactivo.infraestructura.driver_adapter.repository_jpa;

import com.co.api_springreactivo.documents.Contact;
import com.co.api_springreactivo.documents.gateway.ContactGateway;
import com.co.api_springreactivo.infraestructura.mapper.MapperContact;
import com.co.api_springreactivo.infraestructura.service.ContactService;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ContactGatewayImpl implements ContactGateway {

    final private MapperContact mapperContact;
    final private ContactService contactService;

    public ContactGatewayImpl(MapperContact mapperContact, ContactService contactService) {
        this.mapperContact = mapperContact;
        this.contactService = contactService;
    }

    @Override
    public Flux<Contact> findAll() {
        return mapperContact.toContactFlux(contactService.findAll());
    }

    @Override
    public Mono<Contact> save(Contact contact) {
        return mapperContact.toContactMono(contactService.save(mapperContact.toContactData(contact)));
    }

    @Override
    public Mono<Contact> update(String id, Contact contact) {
        return mapperContact.toContactMono(contactService.update(id, mapperContact.toContactData(contact)));
    }

    @Override
    public Mono<Void> delete(String id) {
        return contactService.delete(id);
    }

    @Override
    public Contact getContactById() {
        return null;
    }

    @Override
    public Contact getContactByEmail() {
        return null;
    }

    @Override
    public Mono<Contact> findById(String id) {
        return mapperContact.toContactMono(contactService.findById(id));
    }

    @Override
    public Mono<Contact> findByEmail(String email) {
        return mapperContact.toContactMono(contactService.findByEmail(email));
    }
}
