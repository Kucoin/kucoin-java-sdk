/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.OrderBookAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.OrderBookAPIRetrofit;
import com.kucoin.sdk.rest.response.Level3Response;
import com.kucoin.sdk.rest.response.OrderBookResponse;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public class OrderBookAPIAdapter extends PublicRetrofitAPIImpl<OrderBookAPIRetrofit> implements OrderBookAPI {

    public OrderBookAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public OrderBookResponse getTop100Level2OrderBook(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getTop100Level2OrderBook(symbol));
    }

    @Override
    public OrderBookResponse getTop20Level2OrderBook(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getTop20Level2OrderBook(symbol));
    }

    @Override
    public OrderBookResponse getFullLevel2OrderBook(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getFullLevel2OrderBook(symbol));
    }

    @Override
    public Level3Response getFullOrderBook(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getFullOrderBook(symbol));
    }
}
