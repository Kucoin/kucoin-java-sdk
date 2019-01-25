package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.AccountBalanceResponse;
import com.kucoin.sdk.rest.response.AccountBalancesResponse;
import com.kucoin.sdk.rest.response.AccountDetailResponse;
import com.kucoin.sdk.rest.response.AccountHoldsResponse;
import com.kucoin.sdk.rest.response.Pagination;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface AccountAPI {

    /**
     * Get a list of accounts.
     *
     * Your accounts are separate from your KuCoin accounts. See the Deposits section for documentation on how to
     * deposit funds to begin trading.
     *
     * @param currency the code of the currency
     * @param type     Account type ，"main" or "trade"
     * @return
     */
    List<AccountBalancesResponse> listAccounts(String currency, String type);

    /**
     * Information for a single account. Use this endpoint when you know the accountId.
     *
     * @param accountId id of the account
     * @return
     */
    AccountBalanceResponse getAccount(String accountId);

    /**
     * Create an account
     *
     * @param currency the code of the currency
     * @param type     Account type ，"main" or "trade"
     * @return
     */
    Map<String, String> createAccount(String currency, String type);

    /**
     * List account activity. Account activity either increases or decreases your account balance.
     * Items are paginated and sorted latest first.
     * See the Pagination section for retrieving additional entries after the first page.
     *
     * @param accountId   Id of the account
     * @param startAt     Start time. unix timestamp calculated in seconds, the creation time queried shall posterior to the start time
     * @param endAt       End time. unix timestamp calculated in seconds, the creation time queried shall prior to the end time.
     * @param currentPage
     * @param pageSize
     * @return
     */
    Pagination<AccountDetailResponse> getAccountHistory(String accountId, long startAt, long endAt, int currentPage, int pageSize);

    /**
     * Holds are placed on an account for any active orders or pending withdraw requests.
     * As an order is filled, the hold amount is updated.
     * If an order is canceled, any remaining hold is removed.
     * For a withdraw, once it is completed, the hold is removed.
     *
     * @param accountId   Id of the account
     * @param currentPage
     * @param pageSize
     * @return
     */
    Pagination<AccountHoldsResponse> getHolds(String accountId, int currentPage, int pageSize);

    /**
     * The inner transfer interface is used for assets transfer among the accounts of a user and is free of charges on the platform.
     * For example, a user could transfer assets for free form the main account to the trading account on the platform.
     *
     * @param clientOid    Request id
     * @param payAccountId Account id of payer
     * @param recAccountId Account id of receiver
     * @param amount       Transfer amount, a multiple and positive number of the amount precision.
     * @return
     */
    Map<String, String> innerTransfer(String clientOid, String payAccountId, BigDecimal amount, String recAccountId);

}
