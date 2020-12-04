/*
 * Copyright 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserFeeResponse {

    private String symbol;

    private BigDecimal takerFeeRate;

    private BigDecimal makerFeeRate;

}
