/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.ServiceStatusResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by maot on 2019/1/15.
 */
public interface CommonAPIRetrofit {

    @GET("api/v1/status")
    Call<KucoinResponse<ServiceStatusResponse>> getServerStatus();
}
