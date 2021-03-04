/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.StopOrderAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.StopOrderAPIRetrofit;
import com.kucoin.sdk.rest.request.StopOrderCancelRequest;
import com.kucoin.sdk.rest.request.StopOrderCreateRequest;
import com.kucoin.sdk.rest.request.StopOrderQueryRequest;
import com.kucoin.sdk.rest.response.OrderCancelResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.StopOrderResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StopOrderAPIAdapter extends AuthRetrofitAPIImpl<StopOrderAPIRetrofit> implements StopOrderAPI {

    public StopOrderAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public OrderCreateResponse createStopOrder(StopOrderCreateRequest request) throws IOException {
        return executeSync(getAPIImpl().createStopOrder(request));
    }

    @Override
    public OrderCancelResponse cancelStopOrderByClientOid(String clientOid) throws IOException {
        return executeSync(getAPIImpl().cancelStopOrderByClientOid(clientOid));
    }

    @Override
    public OrderCancelResponse cancelStopOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().cancelStopOrder(orderId));
    }

    @Override
    public OrderCancelResponse cancelStopOrders(StopOrderCancelRequest request) throws IOException {
        Map<String, String> params = new HashMap<>();
        if (request.getSymbol() != null) {
            params.put("symbol", request.getSymbol());
        }
        if (request.getTradeType() != null) {
            params.put("tradeType", request.getTradeType());
        }
        if (request.getOrderIds() != null) {
            params.put("orderIds", request.getOrderIds());
        }
        return executeSync(getAPIImpl().cancelStopOrders(params));
    }

    @Override
    public StopOrderResponse getStopOrderByClientOid(String clientOid) throws IOException {
        return executeSync(getAPIImpl().getStopOrderByClientOid(clientOid));
    }

    @Override
    public StopOrderResponse getStopOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().getStopOrder(orderId));
    }

    @Override
    public Pagination<StopOrderResponse> queryStopOrders(StopOrderQueryRequest request) throws IOException {
        Map<String, String> params = new HashMap<>();
        if (request.getSymbol() != null) {
            params.put("symbol", request.getSymbol());
        }
        if (request.getSide() != null) {
            params.put("side", request.getSide());
        }
        if (request.getType() != null) {
            params.put("type", request.getType());
        }
        if (request.getTradeType() != null) {
            params.put("tradeType", request.getTradeType());
        }
        if (request.getStartAt() != null) {
            params.put("startAt", String.valueOf(request.getStartAt().getTime()));
        }
        if (request.getEndAt() != null) {
            params.put("endAt", String.valueOf(request.getEndAt().getTime()));
        }
        params.put("currentPage", String.valueOf(request.getCurrentPage()));
        params.put("pageSize", String.valueOf(request.getPageSize()));
        if (request.getOrderIds() != null) {
            params.put("orderIds", request.getOrderIds());
        }
        return executeSync(getAPIImpl().queryStopOrders(params));
    }

}
