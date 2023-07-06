package com.kucoin.sdk.rest.interfaces.retrofit;

import com.kucoin.sdk.rest.request.IsolatedBorrowRequest;
import com.kucoin.sdk.rest.request.IsolatedRepayAllRequest;
import com.kucoin.sdk.rest.request.IsolatedRepaySingleRequest;
import com.kucoin.sdk.rest.response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
public interface IsolatedAPIRetrofit {

    @GET("api/v1/isolated/symbols")
    Call<KucoinResponse<List<IsolatedSymbolResponse>>> getSymbols();

    @GET("api/v1/isolated/accounts")
    Call<KucoinResponse<IsolatedAccountResponse>> getAccounts(@Query("balanceCurrency") String balanceCurrency);

    @GET("api/v1/isolated/account/{symbol}")
    Call<KucoinResponse<IsolatedAssetResponse>> getAccount(@Path("symbol") String symbol);

    @POST("api/v1/isolated/borrow")
    Call<KucoinResponse<IsolatedBorrowResponse>> borrow(@Body IsolatedBorrowRequest request);

    @GET("api/v1/isolated/borrow/outstanding")
    Call<KucoinResponse<Pagination<IsolatedBorrowOutstandingResponse>>> queryBorrowOutstanding(@Query("symbol") String symbol,
                                                                                               @Query("currency") String currency,
                                                                                               @Query("pageSize") int pageSize,
                                                                                               @Query("currentPage") int currentPage);

    @GET("api/v1/isolated/borrow/repaid")
    Call<KucoinResponse<Pagination<IsolatedBorrowRepaidResponse>>> queryBorrowRepaid(@Query("symbol") String symbol,
                                                                                     @Query("currency") String currency,
                                                                                     @Query("pageSize") int pageSize,
                                                                                     @Query("currentPage") int currentPage);

    @POST("api/v1/isolated/repay/all")
    Call<KucoinResponse<Void>> repayAll(@Body IsolatedRepayAllRequest request);

    @POST("api/v1/isolated/repay/single")
    Call<KucoinResponse<Void>> repaySingle(@Body IsolatedRepaySingleRequest request);
}
