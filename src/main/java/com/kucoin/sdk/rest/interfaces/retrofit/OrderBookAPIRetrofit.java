package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.OrderBookResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public interface OrderBookAPIRetrofit {

    @GET("api/v1/market/orderbook/level2_100")
    Call<KucoinResponse<OrderBookResponse>> getPartOrderBookAggregated(@Query("symbol") String symbol);

    @GET("api/v1/market/orderbook/level2")
    Call<KucoinResponse<OrderBookResponse>> getFullOrderBookAggregated(@Query("symbol") String symbol);

    @GET("api/v1/market/orderbook/level3")
    Call<KucoinResponse<OrderBookResponse>> getFullOrderBookAtomic(@Query("symbol") String symbol);

}
