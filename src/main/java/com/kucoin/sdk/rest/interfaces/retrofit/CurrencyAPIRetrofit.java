package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyResponse;
import com.kucoin.sdk.rest.response.KucoinResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public interface CurrencyAPIRetrofit {

    @GET(value = "api/v1/currencies")
    Call<KucoinResponse<List<CurrencyResponse>>> getCurrencies();

    @GET(value = "api/v1/currencies/{currency}")
    Call<KucoinResponse<CurrencyDetailResponse>> getCurrencyDetail(@Path("currency") String currency);

}
