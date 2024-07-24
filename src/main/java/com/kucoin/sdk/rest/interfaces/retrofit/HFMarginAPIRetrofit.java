package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.HFMarginOrderCreateRequest;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
public interface HFMarginAPIRetrofit {

    @GET("api/v3/hf/margin/order/active/symbols")
    Call<KucoinResponse<HfMarginOrderActiveSymbolsResponse>> getHfMarginOrderActiveSymbols(@Query("tradeType") String tradeType);

    @POST("api/v3/hf/margin/order")
    Call<KucoinResponse<HFMarginOrderCreateResponse>> createHFMarginOrder(@Body HFMarginOrderCreateRequest request);

    @POST("api/v3/hf/margin/order/test")
    Call<KucoinResponse<HFMarginOrderCreateResponse>> createHFMarginOrderTest(@Body HFMarginOrderCreateRequest request);

    @DELETE("api/v3/hf/margin/orders/client-order/{clientOid}")
    Call<KucoinResponse<HFMarginOrderCancelByClientOidResponse>> cancelHFMarginOrderByClientOid(@Path("clientOid") String clientOid,
                                                                                                @Query("symbol") String symbol);

    @DELETE("api/v3/hf/margin/orders/{orderId}")
    Call<KucoinResponse<HFMarginOrderCancelByOrderIdResponse>> cancelHFMarginOrderByOrderId(@Path("orderId") String orderId,
                                                                                            @Query("symbol") String symbol);

    @DELETE("api/v3/hf/margin/orders")
    Call<KucoinResponse<String>> cancelAllHFMarginOrdersBySymbol(@Query("symbol") String symbol,
                                                                 @Query("tradeType") String tradeType);

    @GET("api/v3/hf/margin/orders/active")
    Call<KucoinResponse<List<HFMarginOrderResponse>>> getHFMarginActiveOrders(@Query("tradeType") String tradeType,
                                                                              @Query("symbol") String symbol);

    @GET("api/v3/hf/margin/orders/done")
    Call<KucoinResponse<HFMarginOrderListResponse>> getHFMarginDoneOrders(@Query("symbol") String symbol,
                                                                            @Query("tradeType") String tradeType,
                                                                            @Query("side") String side,
                                                                            @Query("type") String type,
                                                                            @Query("startAt") Long startAt,
                                                                            @Query("endAt") Long endAt,
                                                                            @Query("lastId") Long lastId,
                                                                            @Query("limit") Integer limit);

    @GET("api/v3/hf/margin/orders/{orderId}")
    Call<KucoinResponse<HFMarginOrderResponse>> getHFMarginOrderByOrderId(@Path("orderId") String orderId,
                                                                          @Query("symbol") String symbol);

    @GET("api/v3/hf/margin/orders/client-order/{clientOid}")
    Call<KucoinResponse<HFMarginOrderResponse>> getHFMarginOrderByClientOid(@Path("clientOid") String clientOid,
                                                                            @Query("symbol") String symbol);

    @GET("api/v3/hf/margin/fills")
    Call<KucoinResponse<HFMarginFillsResponse>> getHFMarginFills(@Query("orderId") String orderId,
                                                                 @Query("symbol") String symbol,
                                                                 @Query("tradeType") String tradeType,
                                                                 @Query("side") String side,
                                                                 @Query("type") String type,
                                                                 @Query("startAt") Long startAt,
                                                                 @Query("endAt") Long endAt,
                                                                 @Query("lastId") Long lastId,
                                                                 @Query("limit") Integer limit);
}
