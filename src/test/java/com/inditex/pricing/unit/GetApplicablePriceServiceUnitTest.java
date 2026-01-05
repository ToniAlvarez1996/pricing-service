package com.inditex.pricing.unit;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.port.in.LookupPriceQuery;
import com.inditex.pricing.domain.port.out.PriceRepositoryPort;
import com.inditex.pricing.domain.service.GetApplicablePriceService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetApplicablePriceServiceUnitTest {

    @Test
    void choosesHighestPriorityWhenMultiplePricesApply() {
        PriceRepositoryPort fakeRepo = (brandId, productId, applicationDate) -> List.of(
                new Price(1L, 35455L, 1L, 0,
                        LocalDateTime.parse("2020-06-14T00:00:00"),
                        LocalDateTime.parse("2020-12-31T23:59:59"),
                        new BigDecimal("35.50"),
                        "EUR"),
                new Price(1L, 35455L, 2L, 1,
                        LocalDateTime.parse("2020-06-14T15:00:00"),
                        LocalDateTime.parse("2020-06-14T18:30:00"),
                        new BigDecimal("25.45"),
                        "EUR")
        );

        var service = new GetApplicablePriceService(fakeRepo);

        var result = service.getApplicablePrice(new LookupPriceQuery(
                LocalDateTime.parse("2020-06-14T16:00:00"),
                35455L,
                1L
        ));

        assertThat(result.priceList()).isEqualTo(2L);
        assertThat(result.amount()).isEqualByComparingTo("25.45");
    }
}

