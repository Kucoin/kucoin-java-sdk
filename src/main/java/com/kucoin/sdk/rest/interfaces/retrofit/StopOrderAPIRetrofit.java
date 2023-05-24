/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.StopOrderCreateRequest;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface StopOrderAPIRetrofit {

    @POST("api/v1/stop-order")
    Call<KucoinResponse<OrderCreateResponse>> createStopOrder(@Body StopOrderCreateRequest request);

    @DELETE("api/v1/stop-order/{orderId}")
    Call<KucoinResponse<OrderCancelResponse>> cancelStopOrder(@Path("orderId") String orderId);

    @DELETE("api/v1/stop-order/cancel")
    Call<KucoinResponse<OrderCancelResponse>> cancelStopOrders(@QueryMap Map<String, String> params);

    @GET("api/v1/stop-order/{orderId}")
    Call<KucoinResponse<StopOrderResponse>> getStopOrder(@Path("orderId") String orderId);

    @GET("api/v1/stop-order")
    Call<KucoinResponse<Pagination<StopOrderResponse>>> queryStopOrders(@QueryMap Map<String, String> params);

    @DELETE("api/v1/stop-order/cancelOrderByClientOid")
    Call<KucoinResponse<OrderCancelResponse>> cancelStopOrderByClientOid(@Query("clientOid") String clientOid);

    @GET("api/v1/stop-order/queryOrderByClientOid")
    Call<KucoinResponse<List<StopOrderResponse>>> getStopOrderByClientOid(@Query("clientOid") String clientOid, @Query("symbol") String symbol);
}
