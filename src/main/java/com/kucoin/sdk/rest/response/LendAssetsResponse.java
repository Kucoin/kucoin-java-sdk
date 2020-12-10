/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LendAssetsResponse {

    private String currency;

    private BigDecimal outstanding;

    private BigDecimal filledSize;

    private BigDecimal accruedInterest;

    private BigDecimal realizedProfit;

    private Boolean isAutoLend;

}
