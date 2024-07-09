/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class EarnOrderRedemptionResponse {

    /**
     * Redemption order ID
     */
    private String orderTxId;

    /**
     * Expected deliver time (milliseconds)
     */
    private Long deliverTime;

    /**
     * Redemption status: SUCCESS (successful), PENDING (redemption pending)
     */
    private String status;

    /**
     * Redemption amount credited
     */
    private BigDecimal amount;

}
