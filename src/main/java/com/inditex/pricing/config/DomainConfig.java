package com.inditex.pricing.config;

import com.inditex.pricing.domain.port.in.GetApplicablePriceUseCase;
import com.inditex.pricing.domain.port.out.PriceRepositoryPort;
import com.inditex.pricing.domain.service.GetApplicablePriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public GetApplicablePriceUseCase getApplicablePriceUseCase(PriceRepositoryPort repositoryPort) {
        return new GetApplicablePriceService(repositoryPort);
    }
}
