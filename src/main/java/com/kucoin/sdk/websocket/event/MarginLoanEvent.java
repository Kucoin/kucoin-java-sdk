package com.kucoin.sdk.websocket.event;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author blaze.tan
 */
@Data
public class MarginLoanEvent {

    private String currency;
    private String orderId;
    private BigDecimal dailyIntRate;
    private int term;
    private BigDecimal size;
    private BigDecimal lentSize;
    private String side;
    private long ts;
    private String reason;

}
