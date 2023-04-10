package com.co.api_springreactivo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ContactRouter {

    @Bean
    public RouterFunction<ServerResponse> routerContact(ContactHandler contactHandler){
        return RouterFunctions
                .route(GET("/functional/contact"), contactHandler::listContacts)
                .andRoute(GET("/functional/contact/{id}"), contactHandler::getContactById)
                .andRoute(GET("/functional/contact/byemail/{email}"), contactHandler::getContactByEmail);
    }
}
