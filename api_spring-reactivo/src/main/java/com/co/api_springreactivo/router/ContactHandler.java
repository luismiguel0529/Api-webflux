package com.co.api_springreactivo.router;

import com.co.api_springreactivo.documents.Contact;
import com.co.api_springreactivo.infraestructura.driver_adapter.repository_jpa.ContactGatewayImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class ContactHandler {

    final private ContactGatewayImpl contactGateway;

    public ContactHandler(ContactGatewayImpl contactGateway) {
        this.contactGateway = contactGateway;
    }

    private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
    private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();


    public Mono<ServerResponse> getContactById(ServerRequest request){
        String id = request.pathVariable("id");

        return contactGateway.findById(id)
                .flatMap(contact -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contact)))
                .switchIfEmpty(response404);
        
    }

    public Mono<ServerResponse> getContactByEmail(ServerRequest request){
        String email = request.pathVariable("email");

        return contactGateway.findByEmail(email)
                .flatMap(contact -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contact)))
                .switchIfEmpty(response404);

    }

    public Mono<ServerResponse> listContacts(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(contactGateway.findAll(), Contact.class);
    }


}
