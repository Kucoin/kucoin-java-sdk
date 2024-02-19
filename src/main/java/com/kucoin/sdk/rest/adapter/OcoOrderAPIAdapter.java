/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.OcoOrderAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.OcoOrderAPIRetrofit;
import com.kucoin.sdk.rest.request.OcoOrderCancelRequest;
import com.kucoin.sdk.rest.request.OcoOrderCreateRequest;
import com.kucoin.sdk.rest.request.OcoOrderQueryRequest;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author blazetan
 */
public class OcoOrderAPIAdapter extends AuthRetrofitAPIImpl<OcoOrderAPIRetrofit> implements OcoOrderAPI {

    public OcoOrderAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public OrderCreateResponse createOcoOrder(OcoOrderCreateRequest request) throws IOException {
        return executeSync(getAPIImpl().createOcoOrder(request));
    }

    @Override
    public OrderCancelResponse cancelOcoOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().cancelOcoOrderByOrderId(orderId));
    }

    @Override
    public OrderCancelResponse cancelOcoOrderByClientOid(String clientOid) throws IOException {
        return executeSync(getAPIImpl().cancelOcoOrderByClientOid(clientOid));
    }

    @Override
    public OrderCancelResponse cancelOcoOrders(OcoOrderCancelRequest request) throws IOException {
        Map<String, String> params = new HashMap<>();
        if (request.getSymbol() != null) {
            params.put("symbol", request.getSymbol());
        }
        if (request.getOrderIds() != null) {
            params.put("orderIds", request.getOrderIds());
        }
        return executeSync(getAPIImpl().cancelOcoOrders(params));
    }

    @Override
    public OcoOrderResponse getOcoOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().getOcoOrderByOrderId(orderId));
    }

    @Override
    public OcoOrderDetailResponse getOcoOrderDetails(String orderId) throws IOException {
        return executeSync(getAPIImpl().getOcoOrderDetails(orderId));
    }

    @Override
    public OcoOrderResponse getOcoOrderByClientOid(String clientOid) throws IOException {
        return executeSync(getAPIImpl().getOcoOrderByClientOid(clientOid));
    }

    @Override
    public Pagination<OcoOrderResponse> queryOcoOrders(OcoOrderQueryRequest request) throws IOException {
        Map<String, String> params = new HashMap<>();
        if (request.getSymbol() != null) {
            params.put("symbol", request.getSymbol());
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
        return executeSync(getAPIImpl().getOcoOrders(params));
    }

}
