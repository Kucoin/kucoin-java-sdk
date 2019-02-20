/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.IsNull.notNullValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.request.WithdrawApplyRequest;
import com.kucoin.sdk.rest.response.AccountBalanceResponse;
import com.kucoin.sdk.rest.response.AccountBalancesResponse;
import com.kucoin.sdk.rest.response.AccountDetailResponse;
import com.kucoin.sdk.rest.response.AccountHoldsResponse;
import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyResponse;
import com.kucoin.sdk.rest.response.OrderBookResponse;
import com.kucoin.sdk.rest.response.OrderCancelResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.SymbolResponse;
import com.kucoin.sdk.rest.response.SymbolTickResponse;
import com.kucoin.sdk.rest.response.TickerResponse;
import com.kucoin.sdk.rest.response.TradeHistoryResponse;
import com.kucoin.sdk.rest.response.TradeResponse;
import com.kucoin.sdk.rest.response.WithdrawResponse;

/**
 * Created by chenshiwei on 2019/1/21.
 */
public class KucoinRestClientTest {
    private static KucoinRestClient kucoinRestClient;

    private static Long startAt;

    private static Long endAt;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
        kucoinRestClient = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
                .withApiKey("5c42a37bef83c73aa68e43c4", "7df80b16-1b95-4739-9b03-3d987599c332", "asd123456")
                .buildRestClient();
        startAt = LocalDateTime.of(2019, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.of("+8"));
        endAt = LocalDateTime.of(2019, 1, 21, 0, 0, 0).toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * Check that we can get all account balances.
     */
    @Test
    public void accountAPIMulti() throws Exception {
        List<AccountBalancesResponse> accountBalancesResponses
          = kucoinRestClient.accountAPI().listAccounts(null, null);
        assertThat(accountBalancesResponses.size(), Is.is(6));
    }

    @Test
    public void accountAPI() throws Exception {
        List<AccountBalancesResponse> accountBalancesResponses = kucoinRestClient.accountAPI().listAccounts("BTC", null);
        assertThat(accountBalancesResponses.size(), Is.is(2));
        Optional<AccountBalancesResponse> main = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("main")).findFirst();
        Optional<AccountBalancesResponse> trade = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("trade")).findFirst();
        assertThat(main.isPresent(), Is.is(true));
        assertThat(trade.isPresent(), Is.is(true));

        String mainAccountId = main.get().getId();
        String tradeAccountId = trade.get().getId();
        AccountBalanceResponse account = kucoinRestClient.accountAPI().getAccount(mainAccountId);
        assertThat(account, notNullValue());

        Pagination<AccountDetailResponse> accountHistory = kucoinRestClient.accountAPI().getAccountHistory(mainAccountId,
                startAt, endAt, 1, 10);
        assertThat(accountHistory, notNullValue());

        Pagination<AccountHoldsResponse> holds = kucoinRestClient.accountAPI().getHolds(mainAccountId, 1, 10);
        assertThat(holds, notNullValue());

        Map<String, String> result = kucoinRestClient.accountAPI().innerTransfer(String.valueOf(System.currentTimeMillis()), tradeAccountId, BigDecimal.valueOf(0.00000001), mainAccountId);
        kucoinRestClient.accountAPI().innerTransfer(String.valueOf(System.currentTimeMillis()), mainAccountId, BigDecimal.valueOf(0.00000001), tradeAccountId);
        assertThat(result, notNullValue());

        exception.expect(KucoinApiException.class);
        exception.expectMessage("account already exists");
        kucoinRestClient.accountAPI().createAccount("KCS", "main");
    }

    @Test
    public void fillAPI() throws Exception {
        Pagination<TradeResponse> fills = kucoinRestClient.fillAPI().listFills("ETH-BTC", null, "BUY",
                null, startAt, endAt, 1, 10);
        assertThat(fills, notNullValue());
    }

    @Test
    public void orderAPI() throws Exception {
        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy")
                .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
        OrderCreateResponse order = kucoinRestClient.orderAPI().createOrder(request);
        assertThat(order, notNullValue());

        Pagination<OrderResponse> orderResponsePagination = kucoinRestClient.orderAPI().listOrders("ETH-BTC",
                null, null, "active", null, null, 1, 1);
        assertThat(orderResponsePagination, notNullValue());

        OrderResponse orderResponse = kucoinRestClient.orderAPI().getOrder(order.getOrderId());
        assertThat(orderResponse, notNullValue());

        OrderCancelResponse orderCancelResponse = kucoinRestClient.orderAPI().cancelOrder(order.getOrderId());
        assertThat(orderCancelResponse, notNullValue());

        OrderCancelResponse ordersCancelResponse = kucoinRestClient.orderAPI().cancelAllOrders("ETH-BTC");
        assertThat(ordersCancelResponse, notNullValue());
    }

    @Test
    public void withdrawalAPI() throws Exception {
        Pagination<WithdrawResponse> withdrawList = kucoinRestClient.withdrawalAPI().getWithdrawList("KCS", "FAILURE",
                startAt, endAt, 1, 10);
        assertThat(withdrawList, notNullValue());

        // TODO doesn't work. To investigate
//        WithdrawQuotaResponse kcs = kucoinRestClient.withdrawalAPI().getWithdrawQuotas("KCS");
//        assertThat(kcs, notNullValue());
        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot be withdrawn");
        WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder().address("123467")
                .amount(BigDecimal.valueOf(0.00000001)).currency("KCS").build();
        kucoinRestClient.withdrawalAPI().applyWithdraw(withdrawApplyRequest);

        kucoinRestClient.withdrawalAPI().cancelWithdraw("1234567");
    }

    @Test
    public void depositAPI() throws Exception {
        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        kucoinRestClient.depositAPI().createDepositAddress("KCS");

        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        kucoinRestClient.depositAPI().getDepositAddress("KCS");

        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        kucoinRestClient.depositAPI().getDepositPageList("KCS", startAt, endAt, "SUCCESS", 1, 10);
    }

    @Test
    public void symbolAPI() throws Exception {
        TickerResponse ticker = kucoinRestClient.symbolAPI().getTicker("ETH-BTC");
        assertThat(ticker, notNullValue());

        List<SymbolResponse> symbols = kucoinRestClient.symbolAPI().getSymbols();
        assertThat(symbols, notNullValue());
        assertThat(symbols.size(), greaterThan(0));

        SymbolTickResponse hrStats = kucoinRestClient.symbolAPI().get24hrStats("ETH-BTC");
        assertThat(hrStats, notNullValue());
    }

    @Test
    public void orderBookAPI() throws Exception {
        OrderBookResponse fullOrderBookAggregated = kucoinRestClient.orderBookAPI().getFullOrderBookAggregated("ETH-BTC");
        assertThat(fullOrderBookAggregated, notNullValue());

        OrderBookResponse fullOrderBookAtomic = kucoinRestClient.orderBookAPI().getFullOrderBookAtomic("ETH-BTC");
        assertThat(fullOrderBookAtomic, notNullValue());

        OrderBookResponse partOrderBookAggregated = kucoinRestClient.orderBookAPI().getPartOrderBookAggregated("ETH-BTC");
        assertThat(partOrderBookAggregated, notNullValue());
    }

    @Test
    public void historyAPI() throws Exception {
        List<TradeHistoryResponse> tradeHistories = kucoinRestClient.historyAPI().getTradeHistories("ETH-BTC");
        assertThat(tradeHistories, notNullValue());
        assertThat(tradeHistories.size(), greaterThan(0));

        List<List<String>> historicRates = kucoinRestClient.historyAPI().getHistoricRates("ETH-BTC", startAt, endAt, "1min");
        assertThat(historicRates, notNullValue());
        assertThat(historicRates.size(), greaterThan(0));
    }

    @Test
    public void currencyAPI() throws Exception {
        List<CurrencyResponse> currencies = kucoinRestClient.currencyAPI().getCurrencies();
        assertThat(currencies, notNullValue());
        assertThat(currencies.size(), greaterThan(0));

        CurrencyDetailResponse kcs = kucoinRestClient.currencyAPI().getCurrencyDetail("KCS");
        assertThat(kcs, notNullValue());
    }

    @Test
    public void timeAPI() throws Exception {
        Long serverTimeStamp = kucoinRestClient.timeAPI().getServerTimeStamp();
        assertThat(System.currentTimeMillis() - serverTimeStamp, lessThanOrEqualTo(2000L));
    }

}