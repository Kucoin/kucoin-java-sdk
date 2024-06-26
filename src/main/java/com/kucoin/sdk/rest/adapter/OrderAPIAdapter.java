/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.OrderAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.OrderAPIRetrofit;
import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
        if (Objects.nonNull(opsRequest) && StringUtils.isEmpty(opsRequest.getTradeType())) {
            opsRequest.setTradeType("TRADE");
        }
        return executeSync(getAPIImpl().createOrder(opsRequest));
    }

    @Override
    public OrderCreateResponse createOrderTest(OrderCreateApiRequest opsRequest) throws IOException {
        if (Objects.nonNull(opsRequest) && StringUtils.isEmpty(opsRequest.getTradeType())) {
            opsRequest.setTradeType("TRADE");
        }
        return executeSync(getAPIImpl().createOrderTest(opsRequest));
    }

    @Override
    public MultiOrderCreateResponse createMultipleOrders(MultiOrderCreateRequest multiOrderCreateRequest) throws IOException {
        if (Objects.nonNull(multiOrderCreateRequest)
                && CollectionUtils.isNotEmpty(multiOrderCreateRequest.getOrderList())) {
            multiOrderCreateRequest.getOrderList().forEach(order -> {
                if (StringUtils.isEmpty(order.getTradeType())) {
                    order.setTradeType("TRADE");
                }
            });
        }
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

    @Override
    public UserFeeResponse getUserBaseFee(String currencyType) throws IOException {
        return executeSync(getAPIImpl().getUserBaseFee(currencyType));
    }

    @Override
    public Pagination<OrderResponse> queryLimitOrderPageList(int pageSize, int currentPage) throws IOException {
        return executeSync(getAPIImpl().queryLimitOrderPageList(pageSize, currentPage));
    }

    @Override
    public List<OrderResponse> queryLimitOrderList() throws IOException {
        return executeSync(getAPIImpl().queryLimitOrderList());
    }

    @Override
    public HFOrderCreateResponse createHFOrder(HFOrderCreateRequest createRequest) throws IOException {
        return executeSync(getAPIImpl().createHFOrder(createRequest));
    }

    @Override
    public HFOrderCreateResponse createHFOrderTest(HFOrderCreateRequest createRequest) throws IOException {
        return executeSync(getAPIImpl().createHFOrderTest(createRequest));
    }

    @Override
    public HFOrderSyncCreateResponse syncCreateHFOrder(HFOrderCreateRequest createRequest) throws IOException {
        return executeSync(getAPIImpl().syncCreateHFOrder(createRequest));
    }

    @Override
    public List<HFOrderMultiCreateResponse> createMultipleHFOrders(HFOrderMultiCreateRequest multiCreateRequest) throws IOException {
        return executeSync(getAPIImpl().createMultipleHFOrders(multiCreateRequest));
    }

    @Override
    public List<HFOrderSyncMultiCreateResponse> syncCreateMultipleHFOrders(HFOrderMultiCreateRequest multiCreateRequest) throws IOException {
        return executeSync(getAPIImpl().syncCreateMultipleHFOrders(multiCreateRequest));
    }

    @Override
    public HFOrderAlterResponse alterHFOrder(HFOrderAlterRequest alterRequest) throws IOException {
        return executeSync(getAPIImpl().alterHFOrder(alterRequest));
    }

    @Override
    public HFOrderCancelResponse cancelHFOrder(String orderId, String symbol) throws IOException {
        return executeSync(getAPIImpl().cancelHFOrder(orderId, symbol));
    }

    @Override
    public HFOrderSyncCancelResponse syncCancelHFOrder(String orderId, String symbol) throws IOException {
        return executeSync(getAPIImpl().syncCancelHFOrder(orderId, symbol));
    }

    @Override
    public HFOrderCancelByClientOidResponse cancelHFOrderByClientOid(String clientOid, String symbol) throws IOException {
        return executeSync(getAPIImpl().cancelHFOrderByClientOid(clientOid, symbol));
    }

    @Override
    public HFOrderSyncCancelResponse syncCancelHFOrderByClientOid(String clientOid, String symbol) throws IOException {
        return executeSync(getAPIImpl().syncCancelHFOrderByClientOid(clientOid, symbol));
    }

    @Override
    public HFOrderCancelSizeResponse cancelHFOrderSize(String orderId, String symbol, String cancelSize) throws IOException {
        return executeSync(getAPIImpl().cancelHFOrderSize(orderId, symbol, cancelSize));
    }

    @Override
    public String cancelHFOrdersBySymbol(String symbol) throws IOException {
        return executeSync(getAPIImpl().cancelHFOrdersBySymbol(symbol));
    }

    @Override
    public HFOrderCancelAllResponse cancelAllHFOrders() throws IOException {
        return executeSync(getAPIImpl().cancelAllHFOrders());
    }

    @Override
    public List<HFOrderResponse> getActiveHFOrders(String symbol) throws IOException {
        return executeSync(getAPIImpl().getActiveHFOrders(symbol));
    }

    @Override
    public HFOrderActiveSymbolQueryResponse getActiveHFOrderSymbols() throws IOException {
        return executeSync(getAPIImpl().getActiveHFOrderSymbols());
    }

    @Override
    public HFDoneOrderQueryResponse getDoneHFOrders(String symbol, String side, String type, Long startAt, Long endAt, Long lastId, Integer limit) throws IOException {
        return executeSync(getAPIImpl().getDoneHFOrders(symbol, side, type, startAt, endAt, lastId, limit));
    }

    @Override
    public HFOrderResponse getHFOrder(String orderId, String symbol) throws IOException {
        return executeSync(getAPIImpl().getHFOrder(orderId, symbol));
    }

    @Override
    public HFOrderResponse getHFOrderByClientOid(String clientOid, String symbol) throws IOException {
        return executeSync(getAPIImpl().getHFOrderByClientOid(clientOid, symbol));
    }

    @Override
    public HFOrderDeadCancelResponse deadCancelHFOrder(int timeout, String symbols) throws IOException {
        HFOrderDeadCancelRequest request = new HFOrderDeadCancelRequest();
        request.setTimeout(timeout);
        request.setSymbols(symbols);
        return executeSync(getAPIImpl().deadCancelHFOrder(request));
    }

    @Override
    public HFOrderDeadCancelQueryResponse queryHFOrderDeadCancel() throws IOException {
        return executeSync(getAPIImpl().queryHFOrderDeadCancel());
    }

    @Override
    public ServerStatusResponse queryServerStatus() throws IOException {
        return executeSync(getAPIImpl().queryServerStatus());
    }
}
