package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.SubUserInfoResponse;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by Reeta on 2019-05-20
 */
public interface UserAPIRetrofit {

    @GET("api/v1/sub/user")
    Call<KucoinResponse<List<SubUserInfoResponse>>> getSubUserList();
}
