/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SettledTradeItem {

    private String tradeId;

    private String currency;

    private BigDecimal size;

    private BigDecimal interest;

    private BigDecimal repaid;

    private BigDecimal dailyIntRate;

    private Integer term;

    private Date settledAt;

    private String note;
}
