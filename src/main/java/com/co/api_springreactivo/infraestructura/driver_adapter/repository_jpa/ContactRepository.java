package com.co.api_springreactivo.infraestructura.driver_adapter.repository_jpa;

import com.co.api_springreactivo.documents.Contact;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ContactRepository extends ReactiveMongoRepository<ContactData, String> {

    Mono<ContactData> findByEmail(String email);
    Mono<ContactData> findByTelephoneOrName(String telephoneOrName);
}
