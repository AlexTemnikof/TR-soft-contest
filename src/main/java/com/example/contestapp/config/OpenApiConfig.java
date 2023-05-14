package com.example.contestapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

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
public class OpenApiConfig {

}
