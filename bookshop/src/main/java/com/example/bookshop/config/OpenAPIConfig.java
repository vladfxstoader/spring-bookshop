package com.example.bookshop.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("vlad.toader@s.unibuc.ro");
        contact.setName("Vlad Toader");

        Info info = new Info()
                .title("Online Bookshop System")
                .contact(contact)
                .description("This API exposes endpoints to manage an online bookshop system.");

        return new OpenAPI().info(info);
    }
}