package com.example.contestapp.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "User System api",
                description = "User System", version = "1.0.0",
                contact = @Contact(
                        name = "Temnikov Alexei",
                        email = "alexei.temnikov02@gmail.com",
                        url = "https://github.com/AlexTemnikof"
                )
        )
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {

}
