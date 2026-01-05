package com.inditex.pricing.domain.port.in;

import java.time.LocalDateTime;

public record LookupPriceQuery(
        LocalDateTime applicationDate,
        Long productId,
        Long brandId
) {}
