package com.inditex.pricing.domain.port.in;

import com.inditex.pricing.domain.model.Price;

public interface GetApplicablePriceUseCase {

    Price getApplicablePrice(LookupPriceQuery query);
}
