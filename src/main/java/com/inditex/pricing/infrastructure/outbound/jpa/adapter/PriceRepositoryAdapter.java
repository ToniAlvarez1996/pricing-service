package com.inditex.pricing.infrastructure.outbound.jpa.adapter;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.port.out.PriceRepositoryPort;
import com.inditex.pricing.infrastructure.outbound.jpa.repository.SpringDataPriceRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final SpringDataPriceRepository repository;

    public PriceRepositoryAdapter(SpringDataPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Price> findApplicablePrices(
            Long brandId,
            Long productId,
            LocalDateTime applicationDate
    ) {
        return repository.findApplicablePrices(brandId, productId, applicationDate)
                .stream()
                .map(entity -> new Price(
                        entity.getBrandId(),
                        entity.getProductId(),
                        entity.getPriceList(),
                        entity.getPriority(),
                        entity.getStartDate(),
                        entity.getEndDate(),
                        entity.getPrice(),
                        entity.getCurrency()
                ))
                .toList();
    }
}

