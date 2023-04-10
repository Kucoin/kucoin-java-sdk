/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyDetailV2Response;
import com.kucoin.sdk.rest.response.CurrencyResponse;
import com.kucoin.sdk.rest.response.KucoinResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public interface CurrencyAPIRetrofit {

    @GET(value = "api/v1/currencies")
    Call<KucoinResponse<List<CurrencyResponse>>> getCurrencies();

    @GET(value = "api/v1/currencies/{currency}")
    Call<KucoinResponse<CurrencyDetailResponse>> getCurrencyDetail(@Path("currency") String currency,
                                                                   @Query("chain") String chain);

    @GET(value = "api/v2/currencies/{currency}")
    Call<KucoinResponse<CurrencyDetailV2Response>> getCurrencyDetailV2(@Path("currency") String currency,
                                                                                @Query("chain") String chain);


    @GET(value = "api/v1/prices")
    Call<KucoinResponse<Map<String, BigDecimal>>> getFiatPrice(@Query("base") String base, @Query("currencies") String currencies);
}
