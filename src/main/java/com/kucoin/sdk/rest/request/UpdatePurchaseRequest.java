package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
@Builder
public class UpdatePurchaseRequest {

    private String currency;

    /**
     * Subscription order number
     */
    private String purchaseOrderNo;

    /**
     * Modified subscription interest rate
     */
    private BigDecimal interestRate;

}
