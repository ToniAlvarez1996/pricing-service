package com.inditex.pricing.domain.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(Long brandId, Long productId) {
        super("No applicable price found for brandId=%d and productId=%d"
                .formatted(brandId, productId));
    }
}