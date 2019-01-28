/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by devin@kucoin.com on 2018-12-27.
 */
@Data
public class SymbolResponse {

    private String symbol;

    private String name;

    private String baseCurrency;

    private String quoteCurrency;

    private BigDecimal baseMinSize;

    private BigDecimal quoteMinSize;

    private BigDecimal baseMaxSize;

    private BigDecimal quoteMaxSize;

    private BigDecimal baseIncrement;

    private BigDecimal quoteIncrement;

    private BigDecimal priceIncrement;

    private boolean enableTrading;
}
