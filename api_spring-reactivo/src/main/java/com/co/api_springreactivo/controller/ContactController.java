package com.co.api_springreactivo.controller;

import com.co.api_springreactivo.documents.Contact;
import com.co.api_springreactivo.infraestructura.driver_adapter.repository_jpa.ContactGatewayImpl;
import com.co.api_springreactivo.infraestructura.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class ContactController {

    final private ContactGatewayImpl contactGateway;

    public ContactController(ContactGatewayImpl contactGateway) {
        this.contactGateway = contactGateway;
    }


    @GetMapping(value = "/contact")
    public Flux<Contact> listContact(){
        return contactGateway.findAll();
    }

    @GetMapping(value = "/contact/{id}")
    public Mono<ResponseEntity<Contact>> getContactById(@PathVariable String id){
        return contactGateway.findById(id)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/contact/byEmail/{email}")
    public Mono<ResponseEntity<Contact>> getContactByEmail(@PathVariable String email){
        return contactGateway.findByEmail(email)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/contact")
    public Mono<ResponseEntity<Contact>> saveContact(@RequestBody Contact contact){
        return contactGateway.save(contact)
                .map(contactSave -> new ResponseEntity<>(contactSave, HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping(value = "/contact/{id}")
    public Mono<ResponseEntity<Contact>> updateContact(@RequestBody Contact contact,@PathVariable String id){
        return contactGateway.update(id, contact)
                .map(contact1 -> new ResponseEntity<>(contact1, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/contact/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteContact(@PathVariable String id){
        return contactGateway.delete(id);
    }

}
