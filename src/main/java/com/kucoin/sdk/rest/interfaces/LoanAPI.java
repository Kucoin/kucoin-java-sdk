/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by ezreal on 2020/12/08.
 */
public interface LoanAPI {

    /**
     * This API endpoint is used to initiate an application for cross or isolated margin borrowing.
     *
     * @param request
     * @return
     * @throws IOException
     */
    BorrowV3Response borrowV3(BorrowV3Request request) throws IOException;

    /**
     * This API endpoint is used to get the borrowing orders for cross and isolated margin accounts
     *
     * @param request
     * @return
     * @throws IOException
     */
    Pagination<BorrowQueryV3Response> queryBorrowV3(BorrowQueryV3Request request) throws IOException;

    /**
     * This API endpoint is used to initiate an application for the repayment of cross or isolated margin borrowing.
     *
     * @param request
     * @return
     * @throws IOException
     */
    RepayV3Response repayV3(RepayV3Request request) throws IOException;

    /**
     * This API endpoint is used to get the repayment orders for cross and isolated margin accounts.
     *
     * @param request
     * @return
     * @throws IOException
     */
    Pagination<RepayQueryV3Response> queryRepayV3(RepayQueryV3Request request) throws IOException;

    /**
     * Request via this endpoint to get the interest records of the cross/isolated margin lending.
     *
     * @param request
     * @return
     * @throws IOException
     */
    Pagination<InterestQueryV3Response> queryInterestV3(InterestQueryV3Request request) throws IOException;

    /**
     * Get Currency Information
     *
     * @param currency
     * @return
     * @throws IOException
     */
    List<MarginProjectListResponse> getProjectList(String currency) throws IOException;

    /**
     * Get Interest Rates
     *
     * @param currency
     * @return
     * @throws IOException
     */
    List<MarginMarketInterestRateResponse> getMarketInterestRate(String currency) throws IOException;

    /**
     * Initiate subscriptions of margin lending.
     *
     * @param request
     * @return
     * @throws IOException
     */
    PurchaseResponse purchase(PurchaseRequest request) throws IOException;

    /**
     * This API endpoint is used to update the interest rates of subscription orders,
     * which will take effect at the beginning of the next hour.
     *
     * @param request
     * @throws IOException
     */
    void updatePurchase(UpdatePurchaseRequest request) throws IOException;

    /**
     * This API endpoint provides pagination query for the subscription orders.
     *
     * @param request
     * @return
     * @throws IOException
     */
    Pagination<PurchaseQueryResponse> queryPurchase(PurchaseQueryRequest request) throws IOException;

    /**
     * Initiate redemptions of margin lending.
     *
     * @param request
     * @return
     * @throws IOException
     */
    RedeemResponse redeem(RedeemRequest request) throws IOException;

    /**
     * This API endpoint provides pagination query for the redemption orders.
     *
     * @param request
     * @return
     * @throws IOException
     */
    Pagination<RedeemQueryResponse> queryRedeem(RedeemQueryRequest request) throws IOException;

    /**
     * Post Borrow Order
     * @param request
     * @return
     */
    @Deprecated
    BorrowResponse borrow(BorrowRequest request) throws IOException;

    /**
     * Get Borrow Order
     * <p>
     * Request via this endpoint to get the info of the borrow order through the orderId retrieved from Post Borrow Order .
     * </p>
     * @param orderId
     * @return
     */
    @Deprecated
    BorrowQueryResponse queryBorrow(String orderId) throws IOException;

    /**
     * Get Repay Record
     * @param request
     * @return
     */
    @Deprecated
    Pagination<BorrowOutstandingResponse> queryBorrowOutstanding(BorrowRecordQueryRequest request) throws IOException;

    /**
     * Get Repayment Record
     * @param request
     * @return
     */
    @Deprecated
    Pagination<BorrowRepaidResponse> queryBorrowRepaid(BorrowRecordQueryRequest request) throws IOException;

    /**
     * One-Click Repayment
     * @param request
     * @return
     */
    @Deprecated
    Void repayAll(RepayAllRequest request) throws IOException;

    /**
     * Repay a Single Order
     * <p>
     * Request via this endpoint to repay a single order.
     * </p>
     * @param request
     * @return
     */
    @Deprecated
    Void repaySingle(RepaySingleRequest request) throws IOException;

    /**
     * Post Lend Order
     * <p>
     * Request via this endpoint to post lend order.
     *
     * Please ensure that you have sufficient funds in your Main Account before you post the order. Once the post succeed,
     * the funds posted will be frozen until the order is succssfuly lent out or cancelled.
     * </p>
     * @param request
     * @return
     */
    @Deprecated
    LendResponse lend(LendRequest request) throws IOException;

    /**
     * Cancel Lend Order
     * <p>
     * Request via this endpoint to cancel lend order.
     * </p>
     * @param orderId
     * @return
     */
    @Deprecated
    Void cancelLendOrder(String orderId) throws IOException;

    /**
     * Set Auto-lend
     * <p>
     * Request via this endpoint to set up the automatic lending for a specified currency.
     * </p>
     * @param request
     * @return
     */
    @Deprecated
    Void toggleAutoLend(ToggleAutoLendRequest request) throws IOException;

    /**
     * Get Active Order
     * <p>
     * Request via this endpoint to get active lend orders. Items are paginated and sorted to show the latest first.
     * See the Pagination section for retrieving additional entries after the first page. The max pageSize is 100.
     *
     * Active lend orders include orders unfilled, partially filled and uncanceled.
     * </p>
     * @param currency
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Deprecated
    Pagination<ActiveLendItem> queryActiveLend(
            String currency,
            Integer currentPage,
            Integer pageSize) throws IOException;

    /**
     * Get Lent History
     * <p>
     * Request via this endpoint to get lent orders . Items are paginated and sorted to show the latest first.
     * See the Pagination section for retrieving additional entries after the first page.
     * The max pageSize is 100.
     *
     * Lent order history involves orders canceled or fully filled.
     * </p>
     * @param currency
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Deprecated
    Pagination<DoneLendItem> queryDoneLend(
            String currency,
            Integer currentPage,
            Integer pageSize) throws IOException;

    /**
     * Get Active Lend Order List
     * <p>
     * Request via this endpoint to get the outstanding lend order list. Items are paginated and sorted to show the latest first.
     * See the Pagination section for retrieving additional entries after the first page. The max pageSize is 100.
     *
     * When a lending order is executed, the system will generate the lending history.
     * The outstanding lend orders includes orders unrepaid and partially repaid.
     * </p>
     * @param currency
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Deprecated
    Pagination<UnsettledTradeItem> queryUnsettledTrade(
            String currency,
            Integer currentPage,
            Integer pageSize) throws IOException;

    /**
     * Get Settled Lend Order History
     * <p>
     * Request via this endpoint to get the settled lend orders . Items are paginated and sorted to show the latest first.
     * See the Pagination section for retrieving additional entries after the first page. The max pageSize is 100.
     *
     * The settled lend orders include orders repaid fully or partially before or at the maturity time.
     * </p>
     * @param currency
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Deprecated
    Pagination<SettledTradeItem> querySettledTrade(
            String currency,
            Integer currentPage,
            Integer pageSize) throws IOException;

    /**
     * Get Account Lend Record
     * <p>
     * Request via this endpoint to get the lending history of the main account.
     * </p>
     * @param currency
     * @return
     */
    @Deprecated
    List<LendAssetsResponse> queryLendAssets(String currency) throws IOException;

    /**
     * Lending Market Data
     * <p>
     * Request via this endpoint to get the configure info of the margin.
     * </p>
     * @param currency
     * @param term
     * @return
     */
    @Deprecated
    List<MarketItemResponse> queryMarket(
            String currency,
            Integer term) throws IOException;

    /**
     * Margin Trade Data
     * <p>
     * Request via this endpoint to get the last 300 fills in the lending and borrowing market.
     * The returned value is sorted based on the descending sequence of the order execution time.
     * </p>
     * @param currency
     * @return
     */
    @Deprecated
    List<LastTradeResponse> queryLastTrade(String currency) throws IOException;

}
