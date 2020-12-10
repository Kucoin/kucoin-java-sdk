/*
 * Copyright (c) 2019 Mek Global Limited
 */
package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MarkPriceResponse implements Serializable {

    private static final long serialVersionUID = -5271075504620444867L;

    private String symbol;

    private Long granularity;

    private Long timePoint;

    private BigDecimal value;
        
}
