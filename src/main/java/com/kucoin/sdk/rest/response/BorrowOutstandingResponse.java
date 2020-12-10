/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BorrowOutstandingResponse {

    private String tradeId;

    private String currency;

    private BigDecimal principal;

    private BigDecimal accruedInterest;

    private BigDecimal liability;

    private BigDecimal repaidSize;

    private BigDecimal dailyIntRate;

    private Integer term;

    private Date createdAt;

    private Date maturityTime;
}
