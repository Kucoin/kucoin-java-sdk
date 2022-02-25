package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarginPriceStrategyResponse {

    private String currency;

    private BigDecimal borrowMaxAmount;

    private BigDecimal buyMaxAmount;

    private int precision;
}
