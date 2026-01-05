package com.inditex.pricing.infrastructure.observability;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class BuildInfoContributorConfig {

    @Bean
    InfoContributor serviceInfoContributor() {
        return builder -> builder.withDetail("service",
                Map.of("name", "pricing-service"));
    }
}