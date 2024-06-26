package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class InterestQueryV3Response {

    private String currency;

    /**
     * Daily interest rate
     */
    private BigDecimal dayRatio;

    /**
     * Interest amount
     */
    private BigDecimal interestAmount;

    /**
     * Time of repayment
     */
    private Long createdTime;

}
