package com.kucoin.sdk.websocket.event;

import lombok.Data;

/**
 * @author blaze.tan
 */
@Data
public class CandlesEvent {

    private String symbol;

    private String[] candles;

    private long time;

}
