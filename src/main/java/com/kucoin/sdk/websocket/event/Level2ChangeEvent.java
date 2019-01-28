/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket.event;

import com.kucoin.sdk.model.OrderBook;
import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/11.
 */
@Data
public class Level2ChangeEvent {

    private long sequenceStart;

    private long sequenceEnd;

    private String symbol;

    private OrderBook changes;

}
