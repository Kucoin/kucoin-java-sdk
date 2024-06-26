/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.LoanAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.LoanAPIRetrofit;
import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ezreal on 2020/12/08.
 */
public class LoanAPIAdapter extends AuthRetrofitAPIImpl<LoanAPIRetrofit> implements LoanAPI {

    public LoanAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public BorrowV3Response borrowV3(BorrowV3Request request) throws IOException {
        return executeSync(getAPIImpl().borrowV3(request));
    }

    @Override
    public Pagination<BorrowQueryV3Response> queryBorrowV3(BorrowQueryV3Request request) throws IOException {
        return executeSync(getAPIImpl().queryBorrowV3(request.getMapParams()));
    }

    @Override
    public RepayV3Response repayV3(RepayV3Request request) throws IOException {
        return executeSync(getAPIImpl().repayV3(request));
    }

    @Override
    public Pagination<RepayQueryV3Response> queryRepayV3(RepayQueryV3Request request) throws IOException {
        return executeSync(getAPIImpl().queryRepayV3(request.getMapParams()));
    }

    @Override
    public Pagination<InterestQueryV3Response> queryInterestV3(InterestQueryV3Request request) throws IOException {
        return executeSync(getAPIImpl().queryInterestV3(request.getMapParams()));
    }

    @Override
    public List<MarginProjectListResponse> getProjectList(String currency) throws IOException {
        return executeSync(getAPIImpl().getProjectList(currency));
    }

    @Override
    public List<MarginMarketInterestRateResponse> getMarketInterestRate(String currency) throws IOException {
        return executeSync(getAPIImpl().getProjectMarketInterestRate(currency));
    }

    @Override
    public PurchaseResponse purchase(PurchaseRequest request) throws IOException {
        return executeSync(getAPIImpl().purchase(request));
    }

    @Override
    public void updatePurchase(UpdatePurchaseRequest request) throws IOException {
        executeSync(getAPIImpl().updatePurchase(request));
    }

    @Override
    public Pagination<PurchaseQueryResponse> queryPurchase(PurchaseQueryRequest request) throws IOException {
        return executeSync(getAPIImpl().queryPurchase(request.getMapParams()));
    }

    @Override
    public RedeemResponse redeem(RedeemRequest request) throws IOException {
        return executeSync(getAPIImpl().redeem(request));
    }

    @Override
    public Pagination<RedeemQueryResponse> queryRedeem(RedeemQueryRequest request) throws IOException {
        return executeSync(getAPIImpl().queryRedeem(request.getMapParams()));
    }

    @Override
    public BorrowResponse borrow(BorrowRequest request) throws IOException {
        return executeSync(getAPIImpl().borrow(request));
    }

    @Override
    public BorrowQueryResponse queryBorrow(String orderId) throws IOException {
        return executeSync(getAPIImpl().queryBorrow(orderId));
    }

    @Override
    public Pagination<BorrowOutstandingResponse> queryBorrowOutstanding(BorrowRecordQueryRequest request) throws IOException {
        return executeSync(getAPIImpl().queryBorrowOutstanding(request.getCurrency(), request.getCurrentPage(), request.getPageSize()));
    }

    @Override
    public Pagination<BorrowRepaidResponse> queryBorrowRepaid(BorrowRecordQueryRequest request) throws IOException {
        return executeSync(getAPIImpl().queryBorrowRepaid(request.getCurrency(), request.getCurrentPage(), request.getPageSize()));
    }

    @Override
    public Void repayAll(RepayAllRequest request) throws IOException {
        return executeSync(getAPIImpl().repayAll(request));
    }

    @Override
    public Void repaySingle(RepaySingleRequest request) throws IOException {
        return executeSync(getAPIImpl().repaySingle(request));
    }

    @Override
    public LendResponse lend(LendRequest request) throws IOException {
        return executeSync(getAPIImpl().lend(request));
    }

    @Override
    public Void cancelLendOrder(String orderId) throws IOException {
        return executeSync(getAPIImpl().cancelLendOrder(orderId));
    }

    @Override
    public Void toggleAutoLend(ToggleAutoLendRequest request) throws IOException {
        return executeSync(getAPIImpl().toggleAutoLend(request));
    }

    @Override
    public Pagination<ActiveLendItem> queryActiveLend(String currency, Integer currentPage, Integer pageSize) throws IOException {
        return executeSync(getAPIImpl().queryActiveLend(currency, currentPage, pageSize));
    }

    @Override
    public Pagination<DoneLendItem> queryDoneLend(String currency, Integer currentPage, Integer pageSize) throws IOException {
        return executeSync(getAPIImpl().queryDoneLend(currency, currentPage, pageSize));

    }

    @Override
    public Pagination<UnsettledTradeItem> queryUnsettledTrade(String currency, Integer currentPage, Integer pageSize)
            throws IOException {
        return executeSync(getAPIImpl().queryUnsettledTrade(currency, currentPage, pageSize));
    }

    @Override
    public Pagination<SettledTradeItem> querySettledTrade(String currency, Integer currentPage, Integer pageSize)
            throws IOException {
        return executeSync(getAPIImpl().querySettledTrade(currency, currentPage, pageSize));
    }

    @Override
    public List<LendAssetsResponse> queryLendAssets(String currency) throws IOException {
        return executeSync(getAPIImpl().queryLendAssets(currency));
    }

    @Override
    public List<MarketItemResponse> queryMarket(String currency, Integer term) throws IOException {
        return executeSync(getAPIImpl().queryMarket(currency, term));
    }

    @Override
    public List<LastTradeResponse> queryLastTrade(String currency) throws IOException {
        return executeSync(getAPIImpl().queryLastTrade(currency));
    }
}
