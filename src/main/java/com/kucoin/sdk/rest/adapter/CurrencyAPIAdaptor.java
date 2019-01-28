package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.retrofit.CurrencyAPIRetrofit;
import com.kucoin.sdk.rest.interfaces.CurrencyAPI;
import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyResponse;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class CurrencyAPIAdaptor extends PublicRetrofitAPIImpl<CurrencyAPIRetrofit> implements CurrencyAPI {

    public CurrencyAPIAdaptor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<CurrencyResponse> getCurrencies() {
        return super.executeSync(getAPIImpl().getCurrencies());
    }

    @Override
    public CurrencyDetailResponse getCurrencyDetail(String currency) {
        return super.executeSync(getAPIImpl().getCurrencyDetail(currency));
    }
}
