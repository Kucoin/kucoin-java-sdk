package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
@Builder
public class PurchaseRequest {

    private String currency;

    /**
     * Subscription amount
     */
    private BigDecimal size;

    /**
     * Subscription interest rate
     */
    private BigDecimal interestRate;

}
