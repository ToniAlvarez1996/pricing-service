package com.inditex.pricing.infrastructure.outbound.jpa.repository;

import com.inditex.pricing.infrastructure.outbound.jpa.entity.PriceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataPriceRepository
        extends JpaRepository<PriceJpaEntity, Long> {

    @Query("""
        select p
        from PriceJpaEntity p
        where p.brandId = :brandId
          and p.productId = :productId
          and :applicationDate between p.startDate and p.endDate
    """)
    List<PriceJpaEntity> findApplicablePrices(
            Long brandId,
            Long productId,
            LocalDateTime applicationDate
    );
}

