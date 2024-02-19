/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OcoOrderCreateRequest {

    private final String symbol;

    private final String side;

    private final BigDecimal price;

    private final BigDecimal size;

    private final BigDecimal stopPrice;

    private final BigDecimal limitPrice;

    @Builder.Default
    private final String tradeType = "TRADE";

    private final String clientOid;

    private final String remark;

}
