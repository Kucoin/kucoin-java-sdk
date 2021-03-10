package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.UserAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.UserAPIRetrofit;
import com.kucoin.sdk.rest.response.SubUserInfoResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by Reeta on 2019-05-20
 */
public class UserAPIAdapter extends AuthRetrofitAPIImpl<UserAPIRetrofit> implements UserAPI {


    public UserAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public List<SubUserInfoResponse> listSubUsers() throws IOException {
        return super.executeSync(getAPIImpl().getSubUserList());
    }
}
