/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import java.io.IOException;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.WithdrawalAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.WithdrawalAPIRetrofit;
import com.kucoin.sdk.rest.request.WithdrawApplyRequest;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.WithdrawApplyResponse;
import com.kucoin.sdk.rest.response.WithdrawQuotaResponse;
import com.kucoin.sdk.rest.response.WithdrawResponse;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class WithdrawalAPIAdapter extends AuthRetrofitAPIImpl<WithdrawalAPIRetrofit> implements WithdrawalAPI {

    public WithdrawalAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public WithdrawQuotaResponse getWithdrawQuotas(String currency) throws IOException {
        return super.executeSync(getAPIImpl().getWithdrawQuotas(currency));
    }

    @Override
    public WithdrawApplyResponse applyWithdraw(WithdrawApplyRequest request) throws IOException {
        return super.executeSync(getAPIImpl().applyWithdraw(request));
    }

    @Override
    public void cancelWithdraw(String withdrawalId) throws IOException {
        super.executeSync(getAPIImpl().cancelWithdraw(withdrawalId));
    }

    @Override
    public Pagination<WithdrawResponse> getWithdrawList(String currency, String status, long startAt, long endAt, int currentPage, int pageSize) throws IOException {
        return super.executeSync(getAPIImpl().getWithdrawPageList(currentPage, pageSize, currency, status, startAt, endAt));
    }
}
