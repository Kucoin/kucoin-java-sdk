/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class StopOrderCreateRequest {

    private final String symbol;

    @Builder.Default
    private final String type = "limit";

    private final String side;

    private final BigDecimal price;

    private final BigDecimal size;

    private final BigDecimal funds;

    @Builder.Default
    private final String tradeType = "TRADE";

    private final String stp;

    @Builder.Default
    private final String stop = "loss";

    private final BigDecimal stopPrice;

    @Builder.Default
    private final String timeInForce = "GTC";

    private final boolean postOnly;

    private final boolean hidden;

    private final boolean iceberg;

    private final BigDecimal visibleSize;

    private final Long cancelAfter;

    private final String clientOid;

    private final String remark;

    private final String tags;

}
