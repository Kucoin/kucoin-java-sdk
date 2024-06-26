package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class PurchaseQueryResponse {

    private String currency;

    /**
     * Subscription order number
     */
    private String purchaseOrderNo;

    /**
     * Total subscription amount
     */
    private BigDecimal purchaseSize;

    /**
     * Executed amount
     */
    private BigDecimal matchSize;

    /**
     * Redeemed amount
     */
    private BigDecimal redeemSize;

    /**
     * Target annualized interest rate
     */
    private BigDecimal interestRate;

    /**
     * Total earnings
     */
    private BigDecimal incomeSize;

    /**
     * Time of subscription
     */
    private Long applyTime;

    /**
     * Status: DONE-completed; PENDING-settling
     * @see com.kucoin.sdk.enums.LendingStatusEnum
     */
    private String status;

}
