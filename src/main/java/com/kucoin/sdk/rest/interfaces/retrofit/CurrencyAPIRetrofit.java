/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.*;
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

    @GET(value = "api/v3/currencies")
    Call<KucoinResponse<List<CurrencyDetailV2Response>>> getCurrenciesV3();


    @GET(value = "api/v1/prices")
    Call<KucoinResponse<Map<String, BigDecimal>>> getFiatPrice(@Query("base") String base, @Query("currencies") String currencies);

    @GET(value = "api/v3/currencies/{currency}")
    Call<KucoinResponse<CurrencyDetailV3Response>> getCurrencyDetailV3(@Path("currency") String currency,
                                                                       @Query("chain") String chain);
}
