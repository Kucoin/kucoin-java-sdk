package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class RedeemQueryResponse {

    private String currency;

    /**
     * Subscription order number
     */
    private String purchaseOrderNo;

    /**
     * Redemption order number
     */
    private String redeemOrderNo;

    /**
     * Redemption amount
     */
    private BigDecimal redeemAmount;

    /**
     * Redeemed amount
     */
    private BigDecimal receiptAmount;

    /**
     * Time of redemption
     */
    private Long applyTime;

    /**
     * Status: DONE-completed; PENDING-settling
     * @see com.kucoin.sdk.enums.LendingStatusEnum
     */
    private String status;

}
