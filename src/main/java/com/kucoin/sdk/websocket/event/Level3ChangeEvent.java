/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket.event;

import java.math.BigDecimal;

import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/19.
 */
@Data
public class Level3ChangeEvent {

    private String sequence;

    private String symbol;

    private String side;

    private BigDecimal size;

    private BigDecimal remainSize;

    private String orderId;

    private BigDecimal price;

    private long time;

    private String type;

    private String orderType;

    private String reason;
}
