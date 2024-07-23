package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.OtcLoanAccountResponse;
import com.kucoin.sdk.rest.response.OtcLoanLoanResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by colt.han on 2024/07/23.
 */
public interface VipLendingAPIRetrofit {

    @GET("api/v1/otc-loan/loan")
    Call<KucoinResponse<OtcLoanLoanResponse>> getOtcLoanLoan();

    @GET("api/v1/otc-loan/accounts")
    Call<KucoinResponse<OtcLoanAccountResponse>> getOtcLoanAccounts();
}
