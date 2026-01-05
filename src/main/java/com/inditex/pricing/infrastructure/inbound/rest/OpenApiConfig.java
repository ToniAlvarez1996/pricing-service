package com.inditex.pricing.infrastructure.inbound.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Pricing Service API",
                version = "1.0.0",
                description = "API to retrieve applicable product prices"
        )
)
public class OpenApiConfig { }