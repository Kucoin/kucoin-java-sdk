/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BorrowRepaidResponse {

    private String tradeId;

    private String currency;

    private BigDecimal principal;

    private BigDecimal interest;

    private BigDecimal repaidSize;

    private BigDecimal dailyIntRate;

    private Integer term;

    private Date repayTime;

}
