/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.DepositAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.DepositAPIRetrofit;
import com.kucoin.sdk.rest.request.DepositAddressCreateRequest;
import com.kucoin.sdk.rest.response.DepositAddressResponse;
import com.kucoin.sdk.rest.response.DepositResponse;
import com.kucoin.sdk.rest.response.Pagination;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class DepositAPIAdapter extends AuthRetrofitAPIImpl<DepositAPIRetrofit> implements DepositAPI {

    public DepositAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public DepositAddressResponse createDepositAddress(String currency) throws IOException {
        DepositAddressCreateRequest request = new DepositAddressCreateRequest();
        request.setCurrency(currency);
        return super.executeSync(getAPIImpl().createDepositAddress(request));
    }

    @Override
    public DepositAddressResponse getDepositAddress(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getDepositAddress(currency));
    }

    @Override
    public Pagination<DepositResponse> getDepositPageList(String currency, long startAt, long endAt, String status,
                                                          int currentPage, int pageSize) throws IOException {
        return super.executeSync(getAPIImpl().getDepositPageList(currentPage, pageSize, currency, status, startAt, endAt));
    }
}
