package com.kucoin.sdk.websocket.event;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author blaze.tan
 */
@Data
public class IndicatorEvent {

    private String symbol;
    private BigDecimal granularity;
    private long timestamp;
    private BigDecimal value;

}
