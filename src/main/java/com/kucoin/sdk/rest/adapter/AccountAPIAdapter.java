/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.AccountAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.AccountAPIRetrofit;
import com.kucoin.sdk.rest.request.AccountCreateRequest;
import com.kucoin.sdk.rest.request.AccountTransferRequest;
import com.kucoin.sdk.rest.request.SubMasterTransferRequest;
import com.kucoin.sdk.rest.response.*;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public class AccountAPIAdapter extends AuthRetrofitAPIImpl<AccountAPIRetrofit> implements AccountAPI {

    public AccountAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
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
    public Pagination<AccountDetailResponse> getAccountHistory(String accountId, long startAt, long endAt,
                                                               int currentPage, int pageSize) throws IOException {
        return super.executeSync(getAPIImpl().getAccountDetail(accountId, currentPage, pageSize, startAt, endAt));
    }

    @Override
    public Pagination<AccountHoldsResponse> getHolds(String accountId, int currentPage, int pageSize) throws IOException {
        return super.executeSync(getAPIImpl().getAccountHold(accountId, currentPage, pageSize));
    }

    @Override
    public Map<String, String> innerTransfer(String clientOid, String payAccountId, BigDecimal amount,
                                             String recAccountId) throws IOException {
        AccountTransferRequest accountTransferRequest = new AccountTransferRequest(clientOid, payAccountId, amount, recAccountId);
        return super.executeSync(getAPIImpl().applyTransfer(accountTransferRequest));
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
    public Map<String, String> transferBetweenSubAndMaster(String clientOid, String currency, BigDecimal amount,
                                                           String direction, String subUserId) throws IOException {
        SubMasterTransferRequest request = new SubMasterTransferRequest(clientOid, currency, amount, direction, subUserId);
        return super.executeSync(getAPIImpl().transferBetweenSubAndMaster(request));
    }
}
