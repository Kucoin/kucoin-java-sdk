/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;


@Data
public class EarnOrderResponse {

    /**
     * Holding ID
     */
    private String orderId;

    /**
     * Subscription order ID
     */
    private String orderTxId;

}
