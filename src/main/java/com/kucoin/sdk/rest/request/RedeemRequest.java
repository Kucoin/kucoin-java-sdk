package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
@Builder
public class RedeemRequest {

    private String currency;

    /**
     * Redemption amount
     */
    private BigDecimal size;

    /**
     * Subscription order number
     */
    private String purchaseOrderNo;

}
