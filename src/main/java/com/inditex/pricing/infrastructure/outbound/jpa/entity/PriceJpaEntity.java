package com.inditex.pricing.infrastructure.outbound.jpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
public class PriceJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "price_list", nullable = false)
    private Long priceList;

    @Column(nullable = false)
    private Integer priority;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 3)
    private String currency;

    protected PriceJpaEntity() {
        // JPA
    }

    public Long getBrandId() { return brandId; }
    public Long getProductId() { return productId; }
    public Long getPriceList() { return priceList; }
    public Integer getPriority() { return priority; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public BigDecimal getPrice() { return price; }
    public String getCurrency() { return currency; }
}

