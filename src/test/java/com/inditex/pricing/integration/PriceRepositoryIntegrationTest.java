package com.inditex.pricing.integration;

import com.inditex.pricing.infrastructure.outbound.jpa.entity.PriceJpaEntity;
import com.inditex.pricing.infrastructure.outbound.jpa.repository.SpringDataPriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PriceRepositoryIntegrationTest {

    @Autowired
    SpringDataPriceRepository repository;

    @Test
    void findsAllApplicableCandidatesForGivenDate() {
        var rows = repository.findApplicablePrices(
                1L,
                35455L,
                LocalDateTime.parse("2020-06-14T16:00:00")
        );

        // En esa hora aplican la tarifa base (1) y la promo (2)
        assertThat(rows).hasSize(2);
        assertThat(rows.stream().map(PriceJpaEntity::getPriceList)).contains(1L, 2L);
    }
}

