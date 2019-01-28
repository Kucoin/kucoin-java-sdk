/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.PublicRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.retrofit.TimeAPIRetrofit;
import com.kucoin.sdk.rest.interfaces.TimeAPI;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public class TimeAPIAdapter extends PublicRetrofitAPIImpl<TimeAPIRetrofit> implements TimeAPI {

    public TimeAPIAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Long getServerTimeStamp() {
        return super.executeSync(getAPIImpl().getServerTimeStamp());
    }
}
