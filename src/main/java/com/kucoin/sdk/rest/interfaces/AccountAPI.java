/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;

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
     * @param type     Account type:，"main" or "trade" or "trade_hf"
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
    @Deprecated
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
     * FlexTransfer
     *
     * @param request
     * @return
     * @throws IOException
     */
    UniversalTransferResponse universalTransfer(UniversalTransferRequest request) throws IOException;

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
     * @param type   The account type: MAIN, TRADE, TRADE_HF, MARGIN or POOL
     * @param tag   [Optional] Trading pair, required when the account type is ISOLATED; other types are not passed, e.g.: BTC-USDT
     * @return
     */
    TransferableBalanceResponse transferable(String currency, String type, String tag) throws IOException;

    /**
     * This endpoint can be used to obtain account summary information.
     *
     * @return account summary information
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    UserSummaryInfoResponse getUserSummaryInfo() throws IOException;

    /**
     * This endpoint can be used to create sub-accounts.
     *
     * @param subName  Sub-account name(must contain 7-32 characters, at least one number and one letter. Cannot contain any spaces)
     * @param password Password(7-24 characters, must contain letters and numbers, cannot only contain numbers or include special characters)
     * @param access   Permission (types include Spot, Futures, Margin permissions, which can be used alone or in combination)
     * @param remarks  [Optional] Remarks(1~24 characters)
     * @return sub-user create info
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    SubUserCreateResponse createSubUser(String subName, String password, String access, String remarks) throws IOException;;

    /**
     * This endpoint can be used to obtain a list of Spot APIs pertaining to a sub-account.
     *
     * @param subName Sub-account name
     * @param apiKey  [Optional] API-Key.
     * @return a list of Spot APIs pertaining to a sub-account
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    List<SubApiKeyResponse> getSubApiKey(String subName, String apiKey) throws IOException;;

    /**
     * This endpoint can be used to create Spot APIs for sub-accounts.
     *
     * @param subName     Sub-account name, create sub account name of API Key
     * @param passphrase  Password(Must contain 7-32 characters. Cannot contain any spaces.)
     * @param remark      Remarks(1~24 characters)
     * @param permission  [Optional] Permissions(Only "General" and "Trade" permissions can be set, such as "General, Trade". The default is "General")
     * @param ipWhitelist [Optional] IP whitelist(You may add up to 20 IPs. Use a halfwidth comma to each IP)
     * @param expire      [Optional] API expiration time; Never expire(default)-1，30Day30，90Day90，180Day180，360Day360
     * @return sub-api-key info
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    SubApiKeyResponse createSubApiKey(String subName, String passphrase, String remark, String permission, String ipWhitelist, String expire) throws IOException;;

    /**
     * This endpoint can be used to modify sub-account Spot APIs.
     *
     * @param subName     Sub-account name, create sub account name of API Key
     * @param passphrase  Password(Must contain 7-32 characters. Cannot contain any spaces.)
     * @param apiKey      API-Key(Sub-account APIKey)
     * @param permission  [Optional] Permissions(Only "General" and "Trade" permissions can be set, such as "General, Trade". The default is "General")
     * @param ipWhitelist [Optional] IP whitelist(You may add up to 20 IPs. Use a halfwidth comma to each IP)
     * @param expire      [Optional] API expiration time; Never expire(default)-1，30Day30，90Day90，180Day180，360Day360
     * @return sub-api-key info
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    SubApiKeyResponse updateSubApiKey(String subName, String apiKey, String passphrase, String permission, String ipWhitelist, String expire) throws IOException;;

    /**
     * This endpoint can be used to delete sub-account Spot APIs.
     *
     * @param subName    Sub-account name, create sub account name of API Key
     * @param passphrase Password(Must contain 7-32 characters. Cannot contain any spaces.)
     * @param apiKey     API-Key(Sub-account APIKey)
     * @return sub-api-key info
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    SubApiKeyResponse deleteSubApiKey(String subName, String apiKey, String passphrase) throws IOException;;

    /**
     * This endpoint can be used to get paginated sub-account information. Pagination is required.
     *
     * @param currentPage Sub-account name, create sub account name of API Key
     * @param pageSize    Password(Must contain 7-32 characters. Cannot contain any spaces)
     * @return The sub-accounts
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    Pagination<SubAccountBalanceResponse> getSubAccountPageList(int currentPage, int pageSize) throws IOException;


    /**
     * transfer to the high-frequency account
     *
     * @param clientOid Client Order Id，a unique identifier created by the user, using UUID is recommended
     * @param currency currency
     * @param from Payment account type: main(main account), trade(trading account), trade_hf(high-frequency trading account)
     * @param amount Transfer amount, the precision is the precision of the currency multiplied by a positive integer
     * @return
     * @throws IOException
     */
    List<AccountBalancesResponse> transferToHFAccount(String clientOid, String currency, String from, BigDecimal amount) throws IOException;

    /**
     * This API endpoint returns all transfer (in and out) records in high-frequency trading account and supports multi-coin queries.
     * The query results are sorted in descending order by createdAt and id.
     *
     * @param currency  [Optional] Id of the account
     * @param direction [Optional] Side: in - Receive, out - Send
     * @param bizType   [Optional] Business type: DEPOSIT, WITHDRAW, TRANSFER, SUB_TRANSFER,TRADE_EXCHANGE, MARGIN_EXCHANGE, KUCOIN_BONUS.
     * @param startAt   [Optional] Start time. unix timestamp calculated in seconds, the creation time queried shall posterior to the start time
     * @param endAt     [Optional] End time. unix timestamp calculated in seconds, the creation time queried shall prior to the end time.
     * @param lastId    [Optional] The id of the last set of data from the previous batch of data. By default, the latest information is given.
     * @param limit     [Optional] Default100，Max200
     * @return The hf account activity.
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    List<AccountDetailResponse> getHFAccountLedgers(String currency, String direction, String bizType, Long lastId, Integer limit, Long startAt,
                                                    Long endAt) throws IOException;
}

