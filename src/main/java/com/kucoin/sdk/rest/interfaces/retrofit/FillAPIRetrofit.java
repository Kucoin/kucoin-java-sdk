/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.TradeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface FillAPIRetrofit {

    @GET(value = "api/v1/fills")
    Call<KucoinResponse<Pagination<TradeResponse>>> queryTrades(@Query("symbol") String symbol,
                                                                @Query("orderId") String orderId,
                                                                @Query("side") String side,
                                                                @Query("type") String type,
                                                                @Query("tradeType") String tradeType,
                                                                @Query("startAt") Long startAt,
                                                                @Query("endAt") Long endAt,
                                                                @Query("pageSize") int pageSize,
                                                                @Query("currentPage") int currentPage);
}
