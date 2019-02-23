/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import java.io.IOException;
import java.util.List;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.HistoryAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.HistoryAPIRetrofit;
import com.kucoin.sdk.rest.response.TradeHistoryResponse;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public class HistoryAPIAdapter extends PublicRetrofitAPIImpl<HistoryAPIRetrofit> implements HistoryAPI {

    public HistoryAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<TradeHistoryResponse> getTradeHistories(String symbol) throws IOException {
        List<TradeHistoryResponse> executeSync = super.executeSync(getAPIImpl().getTradeHistories(symbol));
        return executeSync;
    }

    @Override
    public List<List<String>> getHistoricRates(String symbol, long startAt, long endAt, String type) throws IOException {
        List<List<String>> executeSync = super.executeSync(getAPIImpl().getHistoricRates(symbol, startAt, endAt, type));
        return executeSync;
    }
}
