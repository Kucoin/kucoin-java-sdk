/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarginAccount {

    private String currency;

    private BigDecimal totalBalance;

    private BigDecimal availableBalance;

    private BigDecimal holdBalance;

    private BigDecimal liability;

    private BigDecimal maxBorrowSize;
}
