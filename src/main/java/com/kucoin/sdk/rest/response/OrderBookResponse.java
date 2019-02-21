/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import com.kucoin.sdk.model.OrderBook;

import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/18.
 */
@Data
public class OrderBookResponse extends OrderBook {

    private String sequence;

    private long time;

}
