package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.SubUserInfoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by Reeta on 2019-05-20
 */
public interface UserAPIRetrofit {

    @GET("api/v1/sub/user")
    Call<KucoinResponse<List<SubUserInfoResponse>>> getSubUserList();

    @GET("api/v2/sub/user")
    Call<KucoinResponse<Pagination<SubUserInfoResponse>>> getSubUserPageList(@Query("currentPage") int currentPage,
                                                                             @Query("pageSize") int pageSize);
}
