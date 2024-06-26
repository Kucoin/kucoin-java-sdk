package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class PurchaseResponse {

    /**
     * Subscription order number If there already exists a subscription order with a specific currency and the interest rate,
     * the latest orderNo will be returned.
     */
    private String orderNo;

}
