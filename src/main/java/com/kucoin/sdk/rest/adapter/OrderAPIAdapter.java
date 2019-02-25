/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.OrderAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.OrderAPIRetrofit;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.response.OrderCancelResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderResponse;
import com.kucoin.sdk.rest.response.Pagination;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class OrderAPIAdapter extends AuthRetrofitAPIImpl<OrderAPIRetrofit> implements OrderAPI {

    public OrderAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public OrderCreateResponse createOrder(OrderCreateApiRequest opsRequest) throws IOException {
        return executeSync(getAPIImpl().createOrder(opsRequest));
    }

    @Override
    public OrderCancelResponse cancelOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().cancelOrder(orderId));
    }

    @Override
    public OrderCancelResponse cancelAllOrders(String symbol) throws IOException {
        return executeSync(getAPIImpl().cancelOrders(symbol));
    }

    @Override
    public OrderResponse getOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().getOrder(orderId));
    }

    @Override
    public Pagination<OrderResponse> listOrders(String symbol, String side, String type,
                                                String status, Long start,
                                                Long end, int pageSize, int currentPage) throws IOException {
        return executeSync(getAPIImpl().queryOrders(symbol, side, type, status,
                start, end, pageSize, currentPage));
    }
}
