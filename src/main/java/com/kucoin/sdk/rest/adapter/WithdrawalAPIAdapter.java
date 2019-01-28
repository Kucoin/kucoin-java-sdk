package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.retrofit.WithdrawalAPIRetrofit;
import com.kucoin.sdk.rest.interfaces.WithdrawalAPI;
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
    public WithdrawQuotaResponse getWithdrawQuotas(String currency) {
        return super.executeSync(getAPIImpl().getWithdrawQuotas(currency));
    }

    @Override
    public WithdrawApplyResponse applyWithdraw(WithdrawApplyRequest request) {
        return super.executeSync(getAPIImpl().applyWithdraw(request));
    }

    @Override
    public void cancelWithdraw(String withdrawalId) {
        super.executeSync(getAPIImpl().cancelWithdraw(withdrawalId));
    }

    @Override
    public Pagination<WithdrawResponse> getWithdrawList(String currency, String status, long startAt, long endAt, int currentPage, int pageSize) {
        return super.executeSync(getAPIImpl().getWithdrawPageList(currentPage, pageSize, currency, status, startAt, endAt));
    }
}
