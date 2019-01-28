package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.OrderCancelResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderResponse;
import com.kucoin.sdk.rest.response.Pagination;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface OrderAPIRetrofit {

    @POST("api/v1/orders")
    Call<KucoinResponse<OrderCreateResponse>> createOrder(@Body OrderCreateApiRequest opsRequest);

    @DELETE("api/v1/orders/{orderId}")
    Call<KucoinResponse<OrderCancelResponse>> cancelOrder(@Path("orderId") String orderId);

    @DELETE("api/v1/orders")
    Call<KucoinResponse<OrderCancelResponse>> cancelOrders(@Query("symbol") String symbol);

    @GET("api/v1/orders/{orderId}")
    Call<KucoinResponse<OrderResponse>> getOrder(@Path("orderId") String orderId);

    @GET("api/v1/orders")
    Call<KucoinResponse<Pagination<OrderResponse>>> queryOrders(@Query("symbol") String symbol,
                                                                @Query("side") String side,
                                                                @Query("type") String type,
                                                                @Query("status") String status,
                                                                @Query("startAt") Long startAt,
                                                                @Query("endAt") Long endAt,
                                                                @Query("pageSize") int pageSize,
                                                                @Query("currentPage") int currentPage);

}
