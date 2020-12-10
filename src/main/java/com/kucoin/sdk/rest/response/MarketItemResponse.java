/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketItemResponse {


    private BigDecimal dailyIntRate;

    private Integer term;

    private BigDecimal size;
}
