/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.MultiOrderCreateRequest;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.request.StopOrderCreateRequest;
import com.kucoin.sdk.rest.response.ActiveOrderResponse;
import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.MultiOrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderCancelResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.UserFeeResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface OrderAPIRetrofit {

    @POST("api/v1/orders")
    Call<KucoinResponse<OrderCreateResponse>> createOrder(@Body OrderCreateApiRequest opsRequest);

    @POST("api/v1/orders/multi")
    Call<KucoinResponse<MultiOrderCreateResponse>> createMultipleOrders(@Body MultiOrderCreateRequest multiOrderCreateRequest);

    @DELETE("api/v1/orders/{orderId}")
    Call<KucoinResponse<OrderCancelResponse>> cancelOrder(@Path("orderId") String orderId);

    @DELETE("api/v1/order/client-order/{clientOid}")
    Call<KucoinResponse<OrderCancelResponse>> cancelOrderByClientOid(@Path("clientOid") String clientOid);

    @DELETE("api/v1/orders")
    Call<KucoinResponse<OrderCancelResponse>> cancelOrders(@Query("symbol") String symbol,
                                                           @Query("tradeType") String tradeType);


    @GET("api/v1/orders/{orderId}")
    Call<KucoinResponse<OrderResponse>> getOrder(@Path("orderId") String orderId);

    @GET("api/v1/order/client-order/{clientOid}")
    Call<KucoinResponse<ActiveOrderResponse>> getOrderByClientOid(@Path("clientOid") String clientOid);

    @GET("api/v1/orders")
    Call<KucoinResponse<Pagination<OrderResponse>>> queryOrders(@Query("symbol") String symbol,
                                                                @Query("side") String side,
                                                                @Query("type") String type,
                                                                @Query("tradeType") String tradeType,
                                                                @Query("status") String status,
                                                                @Query("startAt") Long startAt,
                                                                @Query("endAt") Long endAt,
                                                                @Query("pageSize") int pageSize,
                                                                @Query("currentPage") int currentPage);

    @GET("api/v1/trade-fees")
    Call<KucoinResponse<List<UserFeeResponse>>> getUserTradeFees(@Query("symbols") String symbols);

}
