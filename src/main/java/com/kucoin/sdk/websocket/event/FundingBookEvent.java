package com.kucoin.sdk.websocket.event;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author blaze.tan
 */
@Data
public class FundingBookEvent {

    private long sequence;
    private String currency;
    private BigDecimal dailyIntRate;
    private BigDecimal annualIntRate;
    private int term;
    private BigDecimal size;
    private String side;
    private long ts;

}
