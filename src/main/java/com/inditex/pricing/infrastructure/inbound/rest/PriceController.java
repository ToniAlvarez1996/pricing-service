package com.inditex.pricing.infrastructure.inbound.rest;

import com.inditex.pricing.application.dto.PriceResponse;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.port.in.GetApplicablePriceUseCase;
import com.inditex.pricing.domain.port.in.LookupPriceQuery;
import com.inditex.pricing.infrastructure.observability.PricingMetrics;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final GetApplicablePriceUseCase useCase;
    private final PricingMetrics metrics;

    public PriceController(GetApplicablePriceUseCase useCase, PricingMetrics metrics) {
        this.useCase = useCase;
        this.metrics = metrics;
    }

    @GetMapping
    public PriceResponse getPrice(
            @RequestParam Long brandId,
            @RequestParam Long productId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime applicationDate
    ) {
        Price price = useCase.getApplicablePrice(
                new LookupPriceQuery(applicationDate, productId, brandId)
        );

        metrics.incrementPriceQueries();
        return new PriceResponse(
                price.productId(),
                price.brandId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.amount(),
                price.currency()
        );
    }
}
