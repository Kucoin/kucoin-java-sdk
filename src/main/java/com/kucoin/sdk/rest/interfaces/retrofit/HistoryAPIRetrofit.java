/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.TradeHistoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface HistoryAPIRetrofit {

    @GET("api/v1/market/histories")
    Call<KucoinResponse<List<TradeHistoryResponse>>> getTradeHistories(@Query("symbol") String symbol);

    @GET("api/v1/market/candles")
    Call<KucoinResponse<List<List<String>>>> getHistoricRates(@Query("symbol") String symbol,
                                                              @Query("startAt") long startAt,
                                                              @Query("endAt") long endAt,
                                                              @Query("type") String type);

}
