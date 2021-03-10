/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.OrderAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.OrderAPIRetrofit;
import com.kucoin.sdk.rest.request.MultiOrderCreateRequest;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.request.StopOrderCreateRequest;
import com.kucoin.sdk.rest.response.ActiveOrderResponse;
import com.kucoin.sdk.rest.response.MultiOrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderCancelResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.UserFeeResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class OrderAPIAdapter extends AuthRetrofitAPIImpl<OrderAPIRetrofit> implements OrderAPI {

    public OrderAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public OrderCreateResponse createOrder(OrderCreateApiRequest opsRequest) throws IOException {
        return executeSync(getAPIImpl().createOrder(opsRequest));
    }

    @Override
    public MultiOrderCreateResponse createMultipleOrders(MultiOrderCreateRequest multiOrderCreateRequest) throws IOException {
        return executeSync(getAPIImpl().createMultipleOrders(multiOrderCreateRequest));
    }

    @Override
    public OrderCancelResponse cancelOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().cancelOrder(orderId));
    }


    @Override
    public OrderCancelResponse cancelOrderByClientOid(String clientOid) throws IOException {
        return executeSync(getAPIImpl().cancelOrderByClientOid(clientOid));
    }

    @Override
    public OrderCancelResponse cancelAllOrders(String symbol, String tradeType) throws IOException {
        return executeSync(getAPIImpl().cancelOrders(symbol, tradeType));
    }

    @Override
    public OrderResponse getOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().getOrder(orderId));
    }

    @Override
    public ActiveOrderResponse getOrderByClientOid(String clientOid) throws IOException {
        return executeSync(getAPIImpl().getOrderByClientOid(clientOid));
    }

    @Override
    public Pagination<OrderResponse> listOrders(String symbol, String side, String type, String tradeType,
                                                String status, Long start,
                                                Long end, int pageSize, int currentPage) throws IOException {
        return executeSync(getAPIImpl().queryOrders(symbol, side, type, tradeType, status,
                start, end, pageSize, currentPage));
    }

    @Override
    public List<UserFeeResponse> getUserTradeFees(String symbols) throws IOException {
        return executeSync(getAPIImpl().getUserTradeFees(symbols));
    }
}
