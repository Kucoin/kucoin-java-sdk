package com.kucoin.sdk.rest.response;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Data
public class MarginMarketInterestRateResponse {

    /**
     * YYYYMMDDHH00
     */
    private String time;

    /**
     * Increment precision for subscription and redemption
     */
    private BigDecimal marketInterestRate;

}
