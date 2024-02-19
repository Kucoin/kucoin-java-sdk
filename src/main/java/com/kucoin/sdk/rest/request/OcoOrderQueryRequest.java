package com.kucoin.sdk.rest.request;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class OcoOrderQueryRequest extends PageRequest {

    private String symbol;

    private Date startAt;

    private Date endAt;

    private List<String> orderIds;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public OcoOrderQueryRequest addOrderId(String orderId) {
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
