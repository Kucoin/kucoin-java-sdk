/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LastTradeResponse {

    private String tradeId;

    private String currency;

    private BigDecimal size;

    private BigDecimal dailyIntRate;

    private Integer term;

    private Long timestamp;

}
