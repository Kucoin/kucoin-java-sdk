package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class CrossMarginCurrencyResponse {
    private String currency;
    private long timestamp;
    private BigDecimal holdMaxAmount;
    private BigDecimal marginCoefficient;
    private BigDecimal buyMaxAmount;
    private BigDecimal borrowMaxAmount;
    private BigDecimal borrowCoefficient;
    private BigDecimal borrowMinUnit;
    private BigDecimal borrowMinAmount;
    private long precision;
    private boolean borrowEnabled;
}
