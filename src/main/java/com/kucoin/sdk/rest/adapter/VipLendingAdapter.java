package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.VipLendingAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.VipLendingAPIRetrofit;
import com.kucoin.sdk.rest.response.OtcLoanAccountResponse;
import com.kucoin.sdk.rest.response.OtcLoanLoanResponse;

import java.io.IOException;

/**
 * VipLendingAdapter
 *
 * @author Colt Han
 * @since 2024/7/23
 */
public class VipLendingAdapter extends AuthRetrofitAPIImpl<VipLendingAPIRetrofit> implements VipLendingAPI {

    public VipLendingAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public OtcLoanLoanResponse getOtcLoanLoan() throws IOException {
        return super.executeSync(getAPIImpl().getOtcLoanLoan());
    }

    @Override
    public OtcLoanAccountResponse getOtcLoanAccounts() throws IOException {
        return super.executeSync(getAPIImpl().getOtcLoanAccounts());
    }
}
