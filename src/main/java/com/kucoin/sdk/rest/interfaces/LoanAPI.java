/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.BorrowRecordQueryRequest;
import com.kucoin.sdk.rest.request.BorrowRequest;
import com.kucoin.sdk.rest.request.LendRequest;
import com.kucoin.sdk.rest.request.RepayAllRequest;
import com.kucoin.sdk.rest.request.RepaySingleRequest;
import com.kucoin.sdk.rest.request.ToggleAutoLendRequest;
import com.kucoin.sdk.rest.response.ActiveLendItem;
import com.kucoin.sdk.rest.response.BorrowOutstandingResponse;
import com.kucoin.sdk.rest.response.BorrowQueryResponse;
import com.kucoin.sdk.rest.response.BorrowRepaidResponse;
import com.kucoin.sdk.rest.response.BorrowResponse;
import com.kucoin.sdk.rest.response.DoneLendItem;
import com.kucoin.sdk.rest.response.LastTradeResponse;
import com.kucoin.sdk.rest.response.LendAssetsResponse;
import com.kucoin.sdk.rest.response.LendResponse;
import com.kucoin.sdk.rest.response.MarketItemResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.SettledTradeItem;
import com.kucoin.sdk.rest.response.UnsettledTradeItem;

import java.io.IOException;
import java.util.List;

/**
 * Created by ezreal on 2020/12/08.
 */
public interface LoanAPI {

    /**
     * Post Borrow Order
     * @param request
     * @return
     */
    BorrowResponse borrow(BorrowRequest request) throws IOException;

    /**
     * Get Borrow Order
     * <p>
     * Request via this endpoint to get the info of the borrow order through the orderId retrieved from Post Borrow Order .
     * </p>
     * @param orderId
     * @return
     */
    BorrowQueryResponse queryBorrow(String orderId) throws IOException;

    /**
     * Get Repay Record
     * @param request
     * @return
     */
    Pagination<BorrowOutstandingResponse> queryBorrowOutstanding(BorrowRecordQueryRequest request) throws IOException;

    /**
     * Get Repayment Record
     * @param request
     * @return
     */
    Pagination<BorrowRepaidResponse> queryBorrowRepaid(BorrowRecordQueryRequest request) throws IOException;

    /**
     * One-Click Repayment
     * @param request
     * @return
     */
    Void repayAll(RepayAllRequest request) throws IOException;

    /**
     * Repay a Single Order
     * <p>
     * Request via this endpoint to repay a single order.
     * </p>
     * @param request
     * @return
     */
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
    LendResponse lend(LendRequest request) throws IOException;

    /**
     * Cancel Lend Order
     * <p>
     * Request via this endpoint to cancel lend order.
     * </p>
     * @param orderId
     * @return
     */
    Void cancelLendOrder(String orderId) throws IOException;

    /**
     * Set Auto-lend
     * <p>
     * Request via this endpoint to set up the automatic lending for a specified currency.
     * </p>
     * @param request
     * @return
     */
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
    List<LastTradeResponse> queryLastTrade(String currency) throws IOException;

}
