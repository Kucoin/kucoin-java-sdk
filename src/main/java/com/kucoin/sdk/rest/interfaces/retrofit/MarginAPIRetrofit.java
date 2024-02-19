/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.MarginOrderCreateRequest;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by ezreal on 2020/12/08.
 */
public interface MarginAPIRetrofit {

    @GET("api/v1/mark-price/{symbol}/current")
    Call<KucoinResponse<MarkPriceResponse>> getMarkPrice(@Path("symbol") String symbol);

    @GET("api/v1/margin/config")
    Call<KucoinResponse<MarginConfigResponse>> getMarginConfig();

    @GET("api/v1/margin/account")
    Call<KucoinResponse<MarginAccountResponse>> getMarginAccount();

    @POST("api/v1/margin/order")
    Call<KucoinResponse<MarginOrderCreateResponse>> createMarginOrder(@Body MarginOrderCreateRequest request);

    @GET("api/v1/risk/limit/strategy")
    Call<KucoinResponse<List<MarginPriceStrategyResponse>>> getMarginPriceStrategy(@Query("marginModel") String marginModel);

    @GET("api/v3/etf/info")
    Call<KucoinResponse<List<EtfInfoResponse>>> getEtfInfo(@Query("currency") String currency);

    @GET("/api/v3/margin/currencies")
    Call<KucoinResponse<List<CrossMarginCurrencyResponse>>> getMarginCurrencies(@Query("symbol") String symbol, @Query("currency") String currency);

    @GET("/api/v3/margin/accounts")
    Call<KucoinResponse<MarginAccountResponse>> getMarginAccounts(@Query("quoteCurrency") String quoteCurrency, @Query("queryType") String queryType);

}
