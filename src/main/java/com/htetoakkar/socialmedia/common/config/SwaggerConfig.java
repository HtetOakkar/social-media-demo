package com.htetoakkar.socialmedia.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition (
    info = @Info (
        title = "Social Media App",
        description = "Social Media App API Documentation",
        version = "0.0.1",
        summary = "This is documentation for a demo documentation.",
        contact = @Contact (
                name = "Htet Oakkar",
                email = "htetoakkar.dev@gmail.com"
        )
    ),
        servers = {
            @Server (
                    description = "Localhost",
                    url = "http://192.168.0.144"
            )
        },
        security ={
            @SecurityRequirement(
                    name = "Bearer Token"
            )
        }
)
@SecurityScheme(
        name = "Bearer Auth",
        description = "Some endpoints requires bearer token for security.",
        scheme = "Bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "Json Web Token",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
