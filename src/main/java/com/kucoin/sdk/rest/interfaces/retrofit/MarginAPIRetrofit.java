/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.MarginOrderCreateRequest;
import com.kucoin.sdk.rest.request.UserLeverageUpdateRequest;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

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

    @POST("api/v1/margin/order/test")
    Call<KucoinResponse<MarginOrderCreateResponse>> createMarginOrderTest(@Body MarginOrderCreateRequest request);

    @Deprecated
    @GET("api/v1/risk/limit/strategy")
    Call<KucoinResponse<List<MarginPriceStrategyResponse>>> getMarginPriceStrategy(@Query("marginModel") String marginModel);

    @GET("api/v3/etf/info")
    Call<KucoinResponse<List<EtfInfoResponse>>> getEtfInfo(@Query("currency") String currency);

    @GET("api/v3/margin/currencies")
    Call<KucoinResponse<List<CrossMarginCurrencyResponse>>> getMarginCurrencies(@Query("symbol") String symbol, @Query("currency") String currency);

    @GET("api/v3/margin/accounts")
    Call<KucoinResponse<MarginAccountResponse>> getMarginAccounts(@Query("quoteCurrency") String quoteCurrency, @Query("queryType") String queryType);

    @GET("api/v3/margin/symbols")
    Call<KucoinResponse<MarginSymbolsResponse>> getMarginSymbols(@Query("symbol") String symbol);

    @POST("api/v3/position/update-user-leverage")
    Call<KucoinResponse<Void>> updateUserLeverage(@Body UserLeverageUpdateRequest request);

}
