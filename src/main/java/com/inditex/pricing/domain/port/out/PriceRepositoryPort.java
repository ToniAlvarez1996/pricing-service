package com.inditex.pricing.domain.port.out;

import com.inditex.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {

    List<Price> findApplicablePrices(
            Long brandId,
            Long productId,
            LocalDateTime applicationDate
    );
}