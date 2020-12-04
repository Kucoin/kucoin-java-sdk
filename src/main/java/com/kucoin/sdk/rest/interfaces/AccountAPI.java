/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.rest.request.AccountTransferV2Request;
import com.kucoin.sdk.rest.response.AccountBalanceResponse;
import com.kucoin.sdk.rest.response.AccountBalancesResponse;
import com.kucoin.sdk.rest.response.AccountDetailResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.SubAccountBalanceResponse;
import com.kucoin.sdk.rest.response.TransferableBalanceResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface AccountAPI {

    /**
     * Get a list of accounts.
     * <p>
     * Your accounts are separate from your KuCoin accounts. See the Deposits section for documentation on how to
     * deposit funds to begin trading.
     *
     * @param currency The code of the currency
     * @param type     Account type:，"main" or "trade"
     * @return The accounts.
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    List<AccountBalancesResponse> listAccounts(String currency, String type) throws IOException;

    /**
     * Information for a single account. Use this endpoint when you know the accountId.
     *
     * @param accountId id of the account
     * @return The account balance.
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    AccountBalanceResponse getAccount(String accountId) throws IOException;

    /**
     * Create an account.
     *
     * @param currency the code of the currency
     * @param type     Account type ，"main" or "trade"
     * @return The account id.
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    Map<String, String> createAccount(String currency, String type) throws IOException;

    /**
     * List account activity. Account activity either increases or decreases your account balance.
     * Items are paginated and sorted latest first.
     * See the Pagination section for retrieving additional entries after the first page.
     *
     * @param currency    Id of the account
     * @param direction   [Optional] Side: in - Receive, out - Send
     * @param bizType     [Optional] Business type: DEPOSIT, WITHDRAW, TRANSFER, SUB_TRANSFER,TRADE_EXCHANGE, MARGIN_EXCHANGE, KUCOIN_BONUS.
     * @param startAt     Start time. unix timestamp calculated in seconds, the creation time queried shall posterior to the start time
     * @param endAt       End time. unix timestamp calculated in seconds, the creation time queried shall prior to the end time.
     * @param currentPage The page to fetch
     * @param pageSize    The page size.
     * @return The account activity.
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    Pagination<AccountDetailResponse> getAccountLedgers(String currency, String direction, String bizType, long startAt,
                                                        long endAt, int currentPage, int pageSize) throws IOException;

    /**
     * The inner transfer interface is used for assets transfer among the accounts of a user and is free of charges on the platform.
     * For example, a user could transfer assets for free form the main account to the trading account on the platform.
     *
     * @param request
     * @return
     */
    Map<String, String> innerTransfer2(AccountTransferV2Request request) throws IOException;

    /**
     * Get a list of sub-accounts.
     * <p>
     * You need to create the account by using the API-KEY of sub user firstly if the account of sub user is not exist.
     * Attention only trade account can be used for trading
     *
     * @return The sub-accounts.
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    List<SubAccountBalanceResponse> listSubAccounts() throws IOException;


    /**
     * Get info for a single sub-account. Use this endpoint when you know the subUserId.
     *
     * @param subUserId id of the sub user
     * @return The sub-account.
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    SubAccountBalanceResponse getSubAccount(String subUserId) throws IOException;

    /**
     * Transfer the assets between the master user and the sub-user and only supports main account.
     *
     * @param clientOid    Request id
     * @param currency     currency code
     * @param amount       Transfer amount, a multiple and positive number of the amount precision.
     * @param direction     OUT — the master user to sub user;IN — the sub user to the master user.
     * @param subUserId     id of the sub user
     * @param subAccountType The account type of the sub user: MAIN or TRADE
     * @param accountType The account type of the user: MAIN or TRADE
     *
     * @return The order id.
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    Map<String, String> transferBetweenSubAndMasterV2(
            String clientOid, String currency, BigDecimal amount, String direction,
            String subUserId, String subAccountType, String accountType) throws IOException;

    /**
     * This endpoint returns the transferable balance of a specified account.
     * @param currency
     * @param type   The account type: MAIN, TRADE, MARGIN or POOL
     * @return
     */
    TransferableBalanceResponse transferable(String currency,String type) throws IOException;

}

