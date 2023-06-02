package org.example.Lab11.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Lab11", version = "0.0.1-SNAPSHOT"),
        servers = {@Server(url = "http://localhost:8081")},
        tags = {
                @Tag(name = "Player", description = "These are player related requests."),
                @Tag(name = "Game", description = "These are game related requests.")
        })

public class OpenApiConfig {}
