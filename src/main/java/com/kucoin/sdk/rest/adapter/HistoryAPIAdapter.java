package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.HistoryAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.HistoryAPIRetrofit;
import com.kucoin.sdk.rest.response.TradeHistoryResponse;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public class HistoryAPIAdapter extends PublicRetrofitAPIImpl<HistoryAPIRetrofit> implements HistoryAPI {

    public HistoryAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<TradeHistoryResponse> getTradeHistories(String symbol) {
        return super.executeSync(getAPIImpl().getTradeHistories(symbol));
    }

    @Override
    public List<List<String>> getHistoricRates(String symbol, long startAt, long endAt, String type) {
        return super.executeSync(getAPIImpl().getHistoricRates(symbol, startAt, endAt, type));
    }
}
