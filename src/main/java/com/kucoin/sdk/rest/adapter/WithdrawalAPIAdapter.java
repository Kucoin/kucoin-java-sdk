/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.WithdrawalAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.WithdrawalAPIRetrofit;
import com.kucoin.sdk.rest.request.WithdrawApplyRequest;
import com.kucoin.sdk.rest.request.WithdrawApplyV3Request;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class WithdrawalAPIAdapter extends AuthRetrofitAPIImpl<WithdrawalAPIRetrofit> implements WithdrawalAPI {

    public WithdrawalAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public WithdrawQuotaResponse getWithdrawQuotas(String currency, String chain) throws IOException {
        return super.executeSync(getAPIImpl().getWithdrawQuotas(currency, chain));
    }

    @Override
    public WithdrawApplyResponse applyWithdraw(WithdrawApplyRequest request) throws IOException {
        return super.executeSync(getAPIImpl().applyWithdraw(request));
    }

    @Override
    public WithdrawApplyV3Response applyWithdrawV3(WithdrawApplyV3Request request) throws IOException {
        return super.executeSync(getAPIImpl().applyWithdrawV3(request));
    }

    @Override
    public void cancelWithdraw(String withdrawalId) throws IOException {
        super.executeSync(getAPIImpl().cancelWithdraw(withdrawalId));
    }

    @Override
    public Pagination<WithdrawResponse> getWithdrawList(String currency, String status, long startAt, long endAt, int currentPage, int pageSize) throws IOException {
        return super.executeSync(getAPIImpl().getWithdrawPageList(currentPage, pageSize, currency, status, startAt, endAt));
    }

    @Override
    public Pagination<WithdrawResponse> getHistWithdrawPageList(String currency, String status, long startAt, long endAt, int currentPage, int pageSize) throws IOException {
        return super.executeSync(getAPIImpl().getHistWithdrawPageList(currentPage, pageSize, currency, status, startAt, endAt));
    }
}
