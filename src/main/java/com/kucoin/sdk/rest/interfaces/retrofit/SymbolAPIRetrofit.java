package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.SymbolResponse;
import com.kucoin.sdk.rest.response.SymbolTickResponse;
import com.kucoin.sdk.rest.response.TickerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/11.
 */
public interface SymbolAPIRetrofit {

    @GET("api/v1/symbols")
    Call<KucoinResponse<List<SymbolResponse>>> getSymbols();

    @GET("api/v1/market/orderbook/level1")
    Call<KucoinResponse<TickerResponse>> getTicker(@Query("symbol") String symbol);

    @GET("api/v1/market/stats/{symbol}")
    Call<KucoinResponse<SymbolTickResponse>> getMarketStats(@Path("symbol") String symbol);
}
