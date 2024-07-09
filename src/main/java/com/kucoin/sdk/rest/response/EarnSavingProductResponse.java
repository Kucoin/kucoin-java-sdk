package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class EarnSavingProductResponse {
    /**
     * Product ID
     */
    private String id;
    /**
     * Subscription currency
     */
    private String currency;
    /**
     * Product category: DEMAND (savings)
     */
    private String category;
    /**
     * Product subtype: TIME (fixed), DEMAND (demand)
     */
    private String type;
    /**
     * Maximum precision supported
     */
    private Integer precision;
    /**
     * Total product limit
     */
    private BigDecimal productUpperLimit;
    /**
     * Maximum subscription limit per user
     */
    private BigDecimal userUpperLimit;
    /**
     * Minimum subscription limit per user
     */
    private BigDecimal userLowerLimit;
    /**
     * Redemption waiting period (days)
     */
    private Integer redeemPeriod;
    /**
     * Product earliest interest start time, in milliseconds
     */
    private Long lockStartTime;
    /**
     * Product maturity time, in milliseconds
     */
    private Long lockEndTime;
    /**
     * Subscription start time, in milliseconds
     */
    private Long applyStartTime;
    /**
     * Subscription end time, in milliseconds
     */
    private Long applyEndTime;
    /**
     * Annual interest rate, raw value, not multiplied by 100
     */
    private BigDecimal returnRate;
    /**
     * Income currency
     */
    private String incomeCurrency;
    /**
     * Whether the fixed product supports early redemption: 0 (no), 1 (yes)
     */
    private Integer earlyRedeemSupported;
    /**
     * Remaining product limit
     */
    private BigDecimal productRemainAmount;
    /**
     * Product status: ONGOING (ongoing), PENDING (pending), FULL (full), INTERESTING (interest in progress)
     */
    private String status;
    /**
     * Redemption channel: MANUAL (manual redemption), TRANS_DEMAND (transfer to corresponding demand product upon maturity), AUTO (redeem to funding account upon maturity)
     */
    private String redeemType;
    /**
     * Income release type: DAILY (daily release), AFTER (release after product ends)
     */
    private String incomeReleaseType;
    /**
     * Most recent interest date
     */
    private Long interestDate;
    /**
     * Product duration (days)
     */
    private Integer duration;
    /**
     * Whether the product is exclusive for new users: 0 (no), 1 (yes)
     */
    private Integer newUserOnly;
}
