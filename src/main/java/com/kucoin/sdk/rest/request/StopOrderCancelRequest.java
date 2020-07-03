/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.request;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class StopOrderCancelRequest {

    private String symbol;

    private String tradeType;

    private List<String> orderIds;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public StopOrderCancelRequest addOrderId(String orderId) {
        if (orderIds == null) {
            orderIds = new ArrayList<>();
        }
        orderIds.add(orderId);
        return this;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }

    public String getOrderIds() {
        if (orderIds == null) {
            return null;
        }
        return String.join(",", orderIds);
    }
}