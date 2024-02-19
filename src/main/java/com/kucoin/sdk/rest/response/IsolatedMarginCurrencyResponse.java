package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class IsolatedMarginCurrencyResponse {
    private String symbol;
    private boolean baseBorrowEnabled;
    private boolean quoteBorrowEnabled;
    private BigDecimal baseMaxHoldAmount;
    private BigDecimal quoteMaxBorrowAmount;
    private BigDecimal baseMarginCoefficient;
    private BigDecimal quoteMaxBuyAmount;
    private BigDecimal quoteBorrowMinUnit;
    private BigDecimal quoteBorrowMinAmount;
    private BigDecimal quoteBorrowCoefficient;
    private BigDecimal baseMaxBorrowAmount;
    private BigDecimal baseBorrowCoefficient;
    private BigDecimal baseMaxBuyAmount;
    private BigDecimal quoteMarginCoefficient;
    private BigDecimal quoteMaxHoldAmount;
    private long basePrecision;
    private long quotePrecision;
    private long timestamp;
}
