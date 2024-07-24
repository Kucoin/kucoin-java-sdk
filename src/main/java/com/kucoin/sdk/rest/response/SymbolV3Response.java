/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
@Data
public class SymbolV3Response {
    /**
     * Trading pair code
     */
    private String symbol;
    /**
     * Trading pair name
     */
    private String name;
    /**
     * Whether trading is enabled: true for enabled, false for disabled
     */
    private Boolean enableTrading;
    /**
     * Trading market
     */
    private String market;
    /**
     * Base currency
     */
    private String baseCurrency;
    /**
     * Quote currency
     */
    private String quoteCurrency;
    /**
     * Minimum price increment for baseCurrency
     */
    private BigDecimal baseIncrement;
    /**
     * Minimum order size for baseCurrency. If empty, there is no limit
     */
    private BigDecimal baseMinSize;
    /**
     * Minimum price increment for quoteCurrency
     */
    private BigDecimal quoteIncrement;
    /**
     * Minimum order size for quoteCurrency
     */
    private BigDecimal quoteMinSize;
    /**
     * Maximum order size for baseCurrency
     */
    private BigDecimal baseMaxSize;
    /**
     * Maximum order size for quoteCurrency
     */
    private BigDecimal quoteMaxSize;
    /**
     * Price increment for limit orders. Orders must be placed in multiples of this increment
     */
    private BigDecimal priceIncrement;
    /**
     * Price protection threshold
     */
    private BigDecimal priceLimitRate;
    /**
     * Minimum trading amount
     */
    private BigDecimal minFunds;
    /**
     * Currency used for calculating trading fees
     */
    private String feeCurrency;

}
