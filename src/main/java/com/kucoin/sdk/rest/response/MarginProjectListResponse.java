package com.kucoin.sdk.rest.response;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class MarginProjectListResponse {

    private String currency;

    /**
     * Support subscription
     */
    private Boolean purchaseEnable;

    /**
     * Support redemption
     */
    private Boolean redeemEnable;

    /**
     * Increment precision for subscription and redemption
     */
    private BigDecimal increment;

    /**
     * Minimum subscription amount
     */
    private BigDecimal minPurchaseSize;

    /**
     * Minimum annualized interest rate
     */
    private BigDecimal minInterestRate;

    /**
     * Maximum annualized interest rate
     */
    private BigDecimal maxInterestRate;

    /**
     * Increment precision for interest; default is 0.0001
     */
    private BigDecimal interestIncrement;

    /**
     * Maximum subscription limit per user
     */
    private BigDecimal maxPurchaseSize;

    /**
     * Latest market annualized interest rate
     */
    private BigDecimal marketInterestRate;

    /**
     * Auto-Subscribe enabled?: true: enable, false: disable
     */
    private Boolean autoPurchaseEnable;

}
