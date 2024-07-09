package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class EarnHoldAssetResponse {
    /**
     * Holding ID
     */
    private String orderId;
    /**
     * Product ID
     */
    private String productId;
    /**
     * Product category
     */
    private String productCategory;
    /**
     * Product subtype
     */
    private String productType;
    /**
     * Subscription currency
     */
    private String currency;
    /**
     * Income currency
     */
    private String incomeCurrency;
    /**
     * Annual interest rate, raw value, not multiplied by 100
     */
    private BigDecimal returnRate;
    /**
     * Holding amount
     */
    private BigDecimal holdAmount;
    /**
     * Redeemed amount
     */
    private BigDecimal redeemedAmount;
    /**
     * Redeeming amount
     */
    private BigDecimal redeemingAmount;
    /**
     * Product earliest interest start time, in milliseconds
     */
    private Long lockStartTime;
    /**
     * Product maturity time, in milliseconds
     */
    private Long lockEndTime;
    /**
     * Most recent subscription time, in milliseconds
     */
    private Long purchaseTime;
    /**
     * Redemption waiting period (days)
     */
    private Integer redeemPeriod;
    /**
     * Status: LOCKED (holding), REDEEMING (redeeming)
     */
    private String status;
    /**
     * Whether the fixed product supports early redemption: 0 (no), 1 (yes)
     */
    private Integer earlyRedeemSupported;
}
