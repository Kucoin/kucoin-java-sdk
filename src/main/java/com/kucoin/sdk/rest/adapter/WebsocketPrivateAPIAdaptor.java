/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import java.io.IOException;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.WebsocketPrivateAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.WebsocketAuthAPIRetrofit;
import com.kucoin.sdk.rest.response.WebsocketTokenResponse;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class WebsocketPrivateAPIAdaptor extends AuthRetrofitAPIImpl<WebsocketAuthAPIRetrofit>
        implements WebsocketPrivateAPI {

    public WebsocketPrivateAPIAdaptor(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    @Override
    public WebsocketTokenResponse getPrivateToken() throws IOException {
        return super.executeSync(getAPIImpl().getPrivateToken());
    }
}
