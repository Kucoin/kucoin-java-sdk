/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Created by devin@kucoin.com on 2018-12-27.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolResponse {

    private String symbol;

    private String name;

    private String market;

    private String baseCurrency;

    private String quoteCurrency;

    private BigDecimal baseMinSize;

    private BigDecimal quoteMinSize;

    private BigDecimal baseMaxSize;

    private BigDecimal quoteMaxSize;

    private BigDecimal baseIncrement;

    private BigDecimal quoteIncrement;

    private BigDecimal priceIncrement;

    private String feeCurrency;

    private Boolean enableTrading;

    private Boolean isMarginEnabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymbolResponse that = (SymbolResponse) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(market, that.market);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, market);
    }
}
