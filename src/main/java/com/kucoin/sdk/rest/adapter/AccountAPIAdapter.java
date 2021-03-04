/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.AccountAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.AccountAPIRetrofit;
import com.kucoin.sdk.rest.request.AccountCreateRequest;
import com.kucoin.sdk.rest.request.AccountTransferV2Request;
import com.kucoin.sdk.rest.request.SubMasterTransferV2Request;
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
 * Created by chenshiwei on 2019/1/10.
 */
public class AccountAPIAdapter extends AuthRetrofitAPIImpl<AccountAPIRetrofit> implements AccountAPI {

    public AccountAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public List<AccountBalancesResponse> listAccounts(String currency, String type) throws IOException {
        return super.executeSync(getAPIImpl().getAccountList(currency, type));
    }

    @Override
    public AccountBalanceResponse getAccount(String accountId) throws IOException {
        return super.executeSync(getAPIImpl().getAccount(accountId));
    }

    @Override
    public Map<String, String> createAccount(String currency, String type) throws IOException {
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
        accountCreateRequest.setCurrency(currency);
        accountCreateRequest.setType(type);
        return super.executeSync(getAPIImpl().addAccount(accountCreateRequest));
    }

    @Override
    public Pagination<AccountDetailResponse> getAccountLedgers(String currency, String direction, String bizType, long startAt,
                                                               long endAt, int currentPage, int pageSize) throws IOException {

        return super.executeSync(getAPIImpl().getAccountLedgers(currency, direction, bizType, currentPage, pageSize, startAt, endAt));
    }

    @Override
    public Map<String, String> innerTransfer2(AccountTransferV2Request request) throws IOException {
        return super.executeSync(getAPIImpl().applyTransfer2(request));
    }

    @Override
    public List<SubAccountBalanceResponse> listSubAccounts() throws IOException {
        return super.executeSync(getAPIImpl().getSubAccountList());
    }

    @Override
    public SubAccountBalanceResponse getSubAccount(String subUserId) throws IOException {
        return super.executeSync(getAPIImpl().getSubAccount(subUserId));
    }

    @Override
    public Map<String, String> transferBetweenSubAndMasterV2(
            String clientOid, String currency, BigDecimal amount, String direction,
            String subUserId, String subAccountType, String accountType) throws IOException {

        SubMasterTransferV2Request request = new SubMasterTransferV2Request(clientOid, currency,
                amount, direction, accountType, subUserId, subAccountType);
        return super.executeSync(getAPIImpl().transferBetweenSubAndMasterV2(request));
    }

    @Override
    public TransferableBalanceResponse transferable(String currency, String type) throws IOException {
        return super.executeSync(getAPIImpl().transferable(currency, type));
    }

}
