package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
@Builder
public class RepayV3Request {

    /**
     * true-isolated, false-cross; default is false
     */
    private boolean isIsolated;

    /**
     * true: high frequency repayment, false: low frequency repayment; default false
     */
    private boolean isHf;

    /**
     * trading pair, mandatory for isolated margin account
     */
    private String symbol;

    private String currency;

    /**
     * Repayment amount
     */
    private BigDecimal size;

}
