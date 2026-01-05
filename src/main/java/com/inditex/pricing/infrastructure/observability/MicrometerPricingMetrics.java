package com.inditex.pricing.infrastructure.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MicrometerPricingMetrics implements PricingMetrics {

    private final Counter priceQueries;

    public MicrometerPricingMetrics(MeterRegistry registry) {
        this.priceQueries = Counter.builder("pricing_price_queries_total")
                .description("Total number of price queries")
                .register(registry);
    }

    @Override
    public void incrementPriceQueries() {
        priceQueries.increment();
    }
}
