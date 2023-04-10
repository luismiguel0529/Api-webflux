package com.co.api_springreactivo.documents.gateway;

import com.co.api_springreactivo.documents.Contact;
import org.springframework.data.domain.Range;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContactGateway {

    Flux<Contact> findAll();
    Contact getContactById();
    Contact getContactByEmail();

    Mono<Contact> findById(String id);

    Mono<Contact> findByEmail(String email);

    Mono<Contact> save(Contact contact);

    Mono<Contact> update(String id, Contact contact);

    Mono<Void> delete(String id);
}
