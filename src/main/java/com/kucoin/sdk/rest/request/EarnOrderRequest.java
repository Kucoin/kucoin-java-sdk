/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class EarnOrderRequest {

    /**
     * Product ID
     * Necessary
     */
    private String productId;

    /**
     * Subscription amount
     * Necessary
     */
    private String amount;

    /**
     * MAIN (funding account), TRADE (trading account)
     * Necessary
     */
    private String accountType;

}
