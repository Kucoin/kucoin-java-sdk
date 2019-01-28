/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.WebsocketPublicAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.WebsocketPublicAPIRetrofit;
import com.kucoin.sdk.rest.response.WebsocketTokenResponse;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class WebsocketPublicAPIAdaptor extends PublicRetrofitAPIImpl<WebsocketPublicAPIRetrofit>
        implements WebsocketPublicAPI {

    public WebsocketPublicAPIAdaptor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public WebsocketTokenResponse getPublicToken() {
        return super.executeSync(getAPIImpl().getPublicToken());
    }
}
