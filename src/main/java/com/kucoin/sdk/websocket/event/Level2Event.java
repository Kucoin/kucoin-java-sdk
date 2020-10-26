package com.kucoin.sdk.websocket.event;

import lombok.Data;

@Data
public class Level2Event {

    private Object[][] asks;

    private Object[][] bids;

    private long timestamp;
}
