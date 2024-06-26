package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class RepayQueryV3Response {

    private String orderNo;

    private String symbol;

    private String currency;

    /**
     * Initiated repayment amount
     */
    private BigDecimal size;

    /**
     * Principal to be paid
     */
    private BigDecimal principal;

    /**
     * Interest to be paid
     */
    private BigDecimal interest;

    /**
     * Status Repaying, Completed, Failed
     */
    private String status;

    /**
     * Time of repayment
     */
    private Long createdTime;

}
