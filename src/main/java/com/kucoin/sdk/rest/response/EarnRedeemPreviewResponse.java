/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class EarnRedeemPreviewResponse {

    /**
     * Redemption currency
     */
    private String currency;

    /**
     * Expected redemption amount
     */
    private BigDecimal redeemAmount;

    /**
     * Penalty interest amount, incurred for early redemption of fixed-term products
     */
    private BigDecimal penaltyInterestAmount;

    /**
     * Redemption waiting period (days)
     */
    private Integer redeemPeriod;

    /**
     * Expected deliver time (milliseconds)
     */
    private Long deliverTime;

    /**
     * Whether manual redemption is possible
     */
    private Boolean manualRedeemable;

    /**
     * Whether the entire holding must be redeemed, required for early redemption of fixed-term products
     */
    private Boolean redeemAll;

}
