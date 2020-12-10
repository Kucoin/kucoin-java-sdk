/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MatchItem {

    private String currency;

    private String tradeId;

    private BigDecimal size;

    private BigDecimal dailyIntRate;

    private Integer term;

    private Date timestamp;
}
