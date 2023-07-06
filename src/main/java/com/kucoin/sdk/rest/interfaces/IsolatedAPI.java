package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.*;
import retrofit2.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
public interface IsolatedAPI {

    /**
     * This API endpoint returns the current isolated margin trading pair configuration.
     *
     * @return Isolated symbol configuration
     */
    List<IsolatedSymbolResponse> getSymbols() throws IOException;

    /**
     * This API endpoint returns all isolated margin accounts of the current user.
     *
     * @param balanceCurrency [Optional] The pricing coin, currently only supports USDT, KCS, and BTC. Defaults to BTC if no value is passed.
     * @return List of isolated accounts;
     */
    IsolatedAccountResponse getAccounts(String balanceCurrency) throws IOException;

    /**
     * This API endpoint returns the info on a single isolated margin account of the current user.
     *
     * @param symbol Trading pair, e.g.: BTC-USDT
     * @return The isolated account;
     */
    IsolatedAssetResponse getAccount(@Path("symbol") String symbol) throws IOException;

    /**
     * This API endpoint returns the info on a single isolated margin account of the current user.
     *
     * @param symbol         Trading pair, e.g.: BTC-USDT
     * @param currency       Borrowed coin type
     * @param size           Borrowed amount
     * @param borrowStrategy Borrowing strategy: FOK, IOC
     * @param maxRate        [Optional] Max interest rate, defaults to all interest rates if left blank
     * @param period         [Optional] The term in days. Defaults to all terms if left blank. 7,14,28
     * @return The isolated borrow result;
     */
    IsolatedBorrowResponse borrow(String symbol, String currency, BigDecimal size, String borrowStrategy, BigDecimal maxRate, String period) throws IOException;

    /**
     * This API endpoint is used to query the outstanding repayment records of isolated margin positions.
     *
     * @param symbol      [Optional] Trading pair, e.g.: BTC-USDT
     * @param currency    [Optional] Coin type
     * @param pageSize    [Optional] Page size [10-50]
     * @param currentPage [Optional] Current page number [1-100]
     * @return The outstanding repayment records
     */
    Pagination<IsolatedBorrowOutstandingResponse> queryBorrowOutstanding(String symbol, String currency, int pageSize, int currentPage) throws IOException;

    /**
     * This API endpoint is used to query the repayment records of isolated margin positions.
     *
     * @param symbol      [Optional] Trading pair, e.g.: BTC-USDT
     * @param currency    [Optional] Coin type
     * @param pageSize    [Optional] Page size [10-50]
     * @param currentPage [Optional] Current page number [1-100]
     * @return The repayment records
     */
    Pagination<IsolatedBorrowRepaidResponse> queryBorrowRepaid(String symbol, String currency, int pageSize, int currentPage) throws IOException;

    /**
     * This API endpoint is used to initiate quick repayment for isolated margin accounts
     *
     * @param symbol      Trading pair, e.g.: BTC-USDT
     * @param currency    Repayment coin type
     * @param size        Repayment amount
     * @param seqStrategy Repayment sequence strategy, RECENTLY_EXPIRE_FIRST: Maturity date priority (the loan with the closest maturity is repaid first), HIGHEST_RATE_FIRST: Interest rate priority (the loan with the highest interest rate is repaid first)
     */
    void repayAll(String symbol, String currency, BigDecimal size, String seqStrategy) throws IOException;

    /**
     * This API endpoint is used to initiate quick repayment for single margin accounts
     *
     * @param symbol   Trading pair, e.g.: BTC-USDT
     * @param currency Repayment coin type
     * @param size     Repayment amount
     * @param loanId   Trade order number; when this field is configured, the sequence strategy is invalidated
     */
    void repaySingle(String symbol, String currency, BigDecimal size, String loanId) throws IOException;
}
