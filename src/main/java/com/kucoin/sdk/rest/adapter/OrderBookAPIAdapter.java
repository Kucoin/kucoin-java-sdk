/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import java.io.IOException;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.OrderBookAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.OrderBookAPIRetrofit;
import com.kucoin.sdk.rest.response.OrderBookResponse;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public class OrderBookAPIAdapter extends PublicRetrofitAPIImpl<OrderBookAPIRetrofit> implements OrderBookAPI {

    public OrderBookAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public OrderBookResponse getPartOrderBookAggregated(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getPartOrderBookAggregated(symbol));
    }

    @Override
    public OrderBookResponse getFullOrderBookAggregated(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getFullOrderBookAggregated(symbol));
    }

    @Override
    public OrderBookResponse getFullOrderBookAtomic(String symbol) throws IOException {
        return super.executeSync(getAPIImpl().getFullOrderBookAtomic(symbol));
    }
}
