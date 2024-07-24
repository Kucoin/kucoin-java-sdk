package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.HFMarginAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.HFMarginAPIRetrofit;
import com.kucoin.sdk.rest.request.HFMarginOrderCreateRequest;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
public class HFMarginAPIAdapter extends AuthRetrofitAPIImpl<HFMarginAPIRetrofit> implements HFMarginAPI {

    public HFMarginAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public HfMarginOrderActiveSymbolsResponse getHFMarginOrderActiveSymbols(String tradeType) throws IOException {
        return executeSync(getAPIImpl().getHfMarginOrderActiveSymbols(tradeType));
    }

    @Override
    public HFMarginOrderCreateResponse createHFMarginOrder(HFMarginOrderCreateRequest request) throws IOException {
        return executeSync(getAPIImpl().createHFMarginOrder(request));
    }

    @Override
    public HFMarginOrderCreateResponse createHFMarginOrderTest(HFMarginOrderCreateRequest request) throws IOException {
        return executeSync(getAPIImpl().createHFMarginOrderTest(request));
    }

    @Override
    public HFMarginOrderCancelByClientOidResponse cancelHFMarginOrderByClientOid(String clientOid, String symbol) throws IOException {
        return executeSync(getAPIImpl().cancelHFMarginOrderByClientOid(clientOid, symbol));
    }

    @Override
    public HFMarginOrderCancelByOrderIdResponse cancelHFMarginOrderByOrderId(String orderId, String symbol) throws IOException {
        return executeSync(getAPIImpl().cancelHFMarginOrderByOrderId(orderId, symbol));
    }

    @Override
    public String cancelAllHFMarginOrdersBySymbol(String symbol, String tradeType) throws IOException {
        return executeSync(getAPIImpl().cancelAllHFMarginOrdersBySymbol(symbol, tradeType));
    }

    @Override
    public List<HFMarginOrderResponse> getHFMarginActiveOrders(String tradeType, String symbol) throws IOException {
        return executeSync(getAPIImpl().getHFMarginActiveOrders(tradeType, symbol));
    }

    @Override
    public HFMarginOrderListResponse getHFMarginDoneOrders(String symbol, String tradeType,
                                                           String side, String type,
                                                           Long startAt, Long endAt,
                                                           Long lastId, Integer limit) throws IOException {
        return executeSync(getAPIImpl().getHFMarginDoneOrders(symbol, tradeType, side, type, startAt, endAt, lastId, limit));
    }

    @Override
    public HFMarginOrderResponse getHFMarginOrderByOrderId(String orderId, String symbol) throws IOException {
        return executeSync(getAPIImpl().getHFMarginOrderByOrderId(orderId, symbol));
    }

    @Override
    public HFMarginOrderResponse getHFMarginOrderByClientOid(String clientOid, String symbol) throws IOException {
        return executeSync(getAPIImpl().getHFMarginOrderByClientOid(clientOid, symbol));
    }

    @Override
    public HFMarginFillsResponse getHFMarginFills(String orderId, String symbol,
                                                  String tradeType, String side,
                                                  String type, Long startAt,
                                                  Long endAt, Long lastId, Integer limit) throws IOException {
        return executeSync(getAPIImpl().getHFMarginFills(orderId, symbol, tradeType, side, type, startAt, endAt, lastId, limit));
    }
}
