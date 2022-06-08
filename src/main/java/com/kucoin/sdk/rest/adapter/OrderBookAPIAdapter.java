/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.OrderBookAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.OrderBookAPIRetrofit;
import com.kucoin.sdk.rest.response.Level3Response;
import com.kucoin.sdk.rest.response.OrderBookResponse;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public class OrderBookAPIAdapter extends AuthRetrofitAPIImpl<OrderBookAPIRetrofit> implements OrderBookAPI {

    public OrderBookAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
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
