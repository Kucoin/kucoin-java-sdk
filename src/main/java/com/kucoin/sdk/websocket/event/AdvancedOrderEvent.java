package com.kucoin.sdk.websocket.event;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class AdvancedOrderEvent {

    private String orderId;

    private String symbol;

    private String type;

    private String orderType;

    private String side;

    private String error;

    private String tradeType;

    private BigDecimal size;

    private BigDecimal orderPrice;

    private long createdAt;

    private long ts;

}
