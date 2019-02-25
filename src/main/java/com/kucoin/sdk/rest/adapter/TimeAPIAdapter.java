/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import java.io.IOException;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.TimeAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.TimeAPIRetrofit;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class TimeAPIAdapter extends PublicRetrofitAPIImpl<TimeAPIRetrofit> implements TimeAPI {

    public TimeAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Long getServerTimeStamp() throws IOException {
        return super.executeSync(getAPIImpl().getServerTimeStamp());
    }
}
