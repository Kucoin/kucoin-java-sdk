/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.Level3Response;
import com.kucoin.sdk.rest.response.OrderBookResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface OrderBookAPIRetrofit {

    @GET("api/v1/market/orderbook/level2_100")
    Call<KucoinResponse<OrderBookResponse>> getTop100Level2OrderBook(@Query("symbol") String symbol);

    @GET("api/v1/market/orderbook/level2_20")
    Call<KucoinResponse<OrderBookResponse>> getTop20Level2OrderBook(@Query("symbol") String symbol);

    @GET("api/v3/market/orderbook/level2")
    Call<KucoinResponse<OrderBookResponse>> getFullLevel2OrderBook(@Query("symbol") String symbol);

    @GET("api/v2/market/orderbook/level3")
    Call<KucoinResponse<Level3Response>> getFullOrderBook(@Query("symbol") String symbol);

}
