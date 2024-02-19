package com.kucoin.sdk.rest.adapter;

import com.kucoin.sdk.rest.impl.retrofit.AuthRetrofitAPIImpl;
import com.kucoin.sdk.rest.interfaces.IsolatedAPI;
import com.kucoin.sdk.rest.interfaces.retrofit.IsolatedAPIRetrofit;
import com.kucoin.sdk.rest.request.IsolatedBorrowRequest;
import com.kucoin.sdk.rest.request.IsolatedRepayAllRequest;
import com.kucoin.sdk.rest.request.IsolatedRepaySingleRequest;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
public class IsolatedAPIAdapter extends AuthRetrofitAPIImpl<IsolatedAPIRetrofit> implements IsolatedAPI {

    public IsolatedAPIAdapter(String baseUrl, String apiKey, String secret, String passPhrase, Integer apiKeyVersion) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
        this.apiKeyVersion = apiKeyVersion;
    }

    @Override
    public List<IsolatedSymbolResponse> getSymbols() throws IOException {
        return executeSync(getAPIImpl().getSymbols());
    }

    @Override
    public IsolatedAccountResponse getAccounts(String balanceCurrency) throws IOException {
        return executeSync(getAPIImpl().getAccounts(balanceCurrency));
    }

    @Override
    public IsolatedAssetResponse getAccount(String symbol) throws IOException {
        return executeSync(getAPIImpl().getAccount(symbol));
    }

    @Override
    public IsolatedBorrowResponse borrow(String symbol, String currency, BigDecimal size, String borrowStrategy, BigDecimal maxRate, String period) throws IOException {
        IsolatedBorrowRequest request = new IsolatedBorrowRequest();
        request.setSymbol(symbol);
        request.setCurrency(currency);
        request.setSize(size);
        request.setBorrowStrategy(borrowStrategy);
        request.setMaxRate(maxRate);
        request.setPeriod(period);
        return executeSync(getAPIImpl().borrow(request));
    }

    @Override
    public Pagination<IsolatedBorrowOutstandingResponse> queryBorrowOutstanding(String symbol, String currency, int pageSize, int currentPage) throws IOException {
        return executeSync(getAPIImpl().queryBorrowOutstanding(symbol, currency, pageSize, currentPage));
    }

    @Override
    public Pagination<IsolatedBorrowRepaidResponse> queryBorrowRepaid(String symbol, String currency, int pageSize, int currentPage) throws IOException {
        return executeSync(getAPIImpl().queryBorrowRepaid(symbol, currency, pageSize, currentPage));
    }

    @Override
    public void repayAll(String symbol, String currency, BigDecimal size, String seqStrategy) throws IOException {
        IsolatedRepayAllRequest request = new IsolatedRepayAllRequest();
        request.setSymbol(symbol);
        request.setCurrency(currency);
        request.setSize(size);
        request.setSeqStrategy(seqStrategy);
        executeSync(getAPIImpl().repayAll(request));
    }

    @Override
    public void repaySingle(String symbol, String currency, BigDecimal size, String loanId) throws IOException {
        IsolatedRepaySingleRequest request = new IsolatedRepaySingleRequest();
        request.setSymbol(symbol);
        request.setCurrency(currency);
        request.setSize(size);
        request.setLoanId(loanId);
        executeSync(getAPIImpl().repaySingle(request));
    }

    @Override
    public List<IsolatedMarginCurrencyResponse> getIsolatedCurrencies(String symbol) throws IOException {
        return executeSync(getAPIImpl().getIsolatedCurrencies(true, symbol));
    }

    @Override
    public IsolatedAccountV3Response getIsolatedAccountsV3(String symbol, String quoteCurrency, String queryType) throws IOException {
        return executeSync(getAPIImpl().getIsolatedAccountsV3(symbol, quoteCurrency, queryType));
    }
}
