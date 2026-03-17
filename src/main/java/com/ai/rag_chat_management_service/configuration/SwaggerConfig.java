package com.ai.rag_chat_management_service.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI chatServiceOpenAPI() {

        final String securitySchemeName = "apiKeyAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("RAG Chat Management Microservice API")
                        .description("API documentation for managing chat sessions and messages for a RAG-based chatbot system.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Muhammad Yasir")
                                .email("yasiraamir74@gmailcom")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name("X-API-KEY")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                        )
                );
    }
}