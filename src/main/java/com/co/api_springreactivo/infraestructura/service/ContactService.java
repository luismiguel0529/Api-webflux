package com.co.api_springreactivo.infraestructura.service;

import com.co.api_springreactivo.documents.Contact;
import com.co.api_springreactivo.infraestructura.driver_adapter.repository_jpa.ContactData;
import com.co.api_springreactivo.infraestructura.driver_adapter.repository_jpa.ContactRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ContactService {

    final private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Flux<ContactData> findAll() {
        return contactRepository.findAll();
    }

    public Mono<ContactData> findById(String id) {
        return contactRepository.findById(id);
    }

    public Mono<ContactData> findByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    public Mono<ContactData> save(ContactData contactData) {
        return contactRepository.insert(contactData);
    }

    public Mono<Void> delete(String id) {
        return contactRepository.deleteById(id);
    }

    public Mono<ContactData> update(String id, ContactData contactData) {
        return contactRepository.findById(id)
                .flatMap(c -> {
                    contactData.setId(id);
                    return contactRepository.save(contactData);
                });
    }
}
