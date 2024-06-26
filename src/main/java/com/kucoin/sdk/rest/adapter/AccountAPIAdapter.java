/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.AccountAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.AccountAPIRetrofit;
import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
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
    public UniversalTransferResponse universalTransfer(UniversalTransferRequest request) throws IOException {
        return super.executeSync(getAPIImpl().universalTransfer(request));
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
    public TransferableBalanceResponse transferable(String currency, String type, String tag) throws IOException {
        return super.executeSync(getAPIImpl().transferable(currency, type, tag));
    }

    @Override
    public UserSummaryInfoResponse getUserSummaryInfo() throws IOException {
        return super.executeSync(getAPIImpl().getUserSummaryInfo());
    }

    @Override
    public SubUserCreateResponse createSubUser(String subName, String password, String access, String remarks) throws IOException {
        SubUserCreateRequest request = new SubUserCreateRequest();
        request.setSubName(subName);
        request.setPassword(password);
        request.setAccess(access);
        request.setRemarks(remarks);
        return super.executeSync(getAPIImpl().createSubUser(request));
    }

    @Override
    public List<SubApiKeyResponse> getSubApiKey(String subName, String apiKey) throws IOException {
        return super.executeSync(getAPIImpl().getSubApiKey(subName, apiKey));
    }

    @Override
    public SubApiKeyResponse createSubApiKey(String subName, String passphrase, String remark, String permission, String ipWhitelist, String expire) throws IOException {
        SubApiKeyCreateRequest request = new SubApiKeyCreateRequest();
        request.setSubName(subName);
        request.setPassphrase(passphrase);
        request.setRemark(remark);
        request.setPermission(permission);
        request.setIpWhitelist(ipWhitelist);
        request.setExpire(expire);
        return super.executeSync(getAPIImpl().createSubApiKey(request));
    }

    @Override
    public SubApiKeyResponse updateSubApiKey(String subName, String apiKey, String passphrase, String permission, String ipWhitelist, String expire) throws IOException {
        SubApiKeyUpdateRequest request = new SubApiKeyUpdateRequest();
        request.setSubName(subName);
        request.setPassphrase(passphrase);
        request.setApiKey(apiKey);
        request.setPermission(permission);
        request.setIpWhitelist(ipWhitelist);
        request.setExpire(expire);
        return super.executeSync(getAPIImpl().updateSubApiKey(request));
    }

    @Override
    public SubApiKeyResponse deleteSubApiKey(String subName, String apiKey, String passphrase) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("subName", subName);
        params.put("apiKey", apiKey);
        params.put("passphrase", passphrase);
        return super.executeSync(getAPIImpl().deleteSubApiKey(params));
    }

    @Override
    public Pagination<SubAccountBalanceResponse> getSubAccountPageList(int currentPage, int pageSize) throws IOException {
        return super.executeSync(getAPIImpl().getSubAccountPageList(currentPage, pageSize));
    }

    @Override
    public List<AccountBalancesResponse> transferToHFAccount(String clientOid, String currency, String from, BigDecimal amount) throws IOException {
        AccountTransferV2Request request = new AccountTransferV2Request(clientOid, currency, from, "trade_hf", amount);
        super.executeSync(getAPIImpl().applyTransfer2(request));

        return super.executeSync(getAPIImpl().getAccountList(currency, "trade_hf"));
    }

    @Override
    public List<AccountDetailResponse> getHFAccountLedgers(String currency, String direction, String bizType, Long lastId, Integer limit, Long startAt, Long endAt) throws IOException {
        return super.executeSync(getAPIImpl().getHFAccountLedgers(currency, direction, bizType, lastId, limit, startAt, endAt));
    }
}
