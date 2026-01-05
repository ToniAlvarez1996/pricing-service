package com.inditex.pricing.domain.service;

import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.port.in.GetApplicablePriceUseCase;
import com.inditex.pricing.domain.port.in.LookupPriceQuery;
import com.inditex.pricing.domain.port.out.PriceRepositoryPort;

import java.util.Comparator;

public class GetApplicablePriceService implements GetApplicablePriceUseCase {

    private final PriceRepositoryPort repository;

    public GetApplicablePriceService(PriceRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Price getApplicablePrice(LookupPriceQuery query) {
        return repository.findApplicablePrices(
                        query.brandId(),
                        query.productId(),
                        query.applicationDate()
                )
                .stream()
                .max(Comparator
                        .comparing(Price::priority)
                        .thenComparing(Price::priceList))
                .orElseThrow(() ->
                        new PriceNotFoundException(
                                query.brandId(),
                                query.productId()
                        ));
    }
}
