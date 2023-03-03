/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BorrowResponse {

    private String orderId;

    private String currency;

    private BigDecimal actualSize;
}
