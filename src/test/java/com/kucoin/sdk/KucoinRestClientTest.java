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
    private static KucoinRestClient sandboxKucoinRestClient;
    private static KucoinRestClient liveKucoinRestClient;

    private static Long startAt;
    private static Long endAt;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
        sandboxKucoinRestClient = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
                .withApiKey("5c42a37bef83c73aa68e43c4", "7df80b16-1b95-4739-9b03-3d987599c332", "asd123456")
                .buildRestClient();
        liveKucoinRestClient = new KucoinClientBuilder().withBaseUrl("https://openapi-v2.kucoin.com")
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
          = sandboxKucoinRestClient.accountAPI().listAccounts(null, null);
        assertThat(accountBalancesResponses.size(), Is.is(6));
    }

    @Test
    public void accountAPI() throws Exception {
        List<AccountBalancesResponse> accountBalancesResponses = sandboxKucoinRestClient.accountAPI().listAccounts("BTC", null);
        assertThat(accountBalancesResponses.size(), Is.is(2));
        Optional<AccountBalancesResponse> main = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("main")).findFirst();
        Optional<AccountBalancesResponse> trade = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("trade")).findFirst();
        assertThat(main.isPresent(), Is.is(true));
        assertThat(trade.isPresent(), Is.is(true));

        String mainAccountId = main.get().getId();
        String tradeAccountId = trade.get().getId();
        AccountBalanceResponse account = sandboxKucoinRestClient.accountAPI().getAccount(mainAccountId);
        assertThat(account, notNullValue());

        Pagination<AccountDetailResponse> accountHistory = sandboxKucoinRestClient.accountAPI().getAccountHistory(mainAccountId,
                startAt, endAt, 1, 10);
        assertThat(accountHistory, notNullValue());

        Pagination<AccountHoldsResponse> holds = sandboxKucoinRestClient.accountAPI().getHolds(mainAccountId, 1, 10);
        assertThat(holds, notNullValue());

        Map<String, String> result = sandboxKucoinRestClient.accountAPI().innerTransfer(String.valueOf(System.currentTimeMillis()), tradeAccountId, BigDecimal.valueOf(0.00000001), mainAccountId);
        sandboxKucoinRestClient.accountAPI().innerTransfer(String.valueOf(System.currentTimeMillis()), mainAccountId, BigDecimal.valueOf(0.00000001), tradeAccountId);
        assertThat(result, notNullValue());

        exception.expect(KucoinApiException.class);
        exception.expectMessage("account already exists");
        sandboxKucoinRestClient.accountAPI().createAccount("KCS", "main");
    }

    @Test
    public void fillAPI() throws Exception {
        Pagination<TradeResponse> fills = sandboxKucoinRestClient.fillAPI().listFills("ETH-BTC", null, "BUY",
                null, startAt, endAt, 1, 10);
        assertThat(fills, notNullValue());
    }

    @Test
    public void orderAPI() throws Exception {
        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy")
                .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
        OrderCreateResponse order = sandboxKucoinRestClient.orderAPI().createOrder(request);
        assertThat(order, notNullValue());

        Pagination<OrderResponse> orderResponsePagination = sandboxKucoinRestClient.orderAPI().listOrders("ETH-BTC",
                null, null, "active", null, null, 1, 1);
        assertThat(orderResponsePagination, notNullValue());

        OrderResponse orderResponse = sandboxKucoinRestClient.orderAPI().getOrder(order.getOrderId());
        assertThat(orderResponse, notNullValue());

        OrderCancelResponse orderCancelResponse = sandboxKucoinRestClient.orderAPI().cancelOrder(order.getOrderId());
        assertThat(orderCancelResponse, notNullValue());

        OrderCancelResponse ordersCancelResponse = sandboxKucoinRestClient.orderAPI().cancelAllOrders("ETH-BTC");
        assertThat(ordersCancelResponse, notNullValue());
    }

    @Test
    public void withdrawalAPI() throws Exception {
        Pagination<WithdrawResponse> withdrawList = sandboxKucoinRestClient.withdrawalAPI().getWithdrawList("KCS", "FAILURE",
                startAt, endAt, 1, 10);
        assertThat(withdrawList, notNullValue());

        // TODO doesn't work. To investigate
//        WithdrawQuotaResponse kcs = kucoinRestClient.withdrawalAPI().getWithdrawQuotas("KCS");
//        assertThat(kcs, notNullValue());
        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot be withdrawn");
        WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder().address("123467")
                .amount(BigDecimal.valueOf(0.00000001)).currency("KCS").build();
        sandboxKucoinRestClient.withdrawalAPI().applyWithdraw(withdrawApplyRequest);

        sandboxKucoinRestClient.withdrawalAPI().cancelWithdraw("1234567");
    }

    @Test
    public void depositAPI() throws Exception {
        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        sandboxKucoinRestClient.depositAPI().createDepositAddress("KCS");

        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        sandboxKucoinRestClient.depositAPI().getDepositAddress("KCS");

        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        sandboxKucoinRestClient.depositAPI().getDepositPageList("KCS", startAt, endAt, "SUCCESS", 1, 10);
    }

    @Test
    public void symbolAPI() throws Exception {
        assertThat(sandboxKucoinRestClient.symbolAPI().getTicker("ETH-BTC"), notNullValue());

        List<SymbolResponse> symbols = sandboxKucoinRestClient.symbolAPI().getSymbols();
        assertThat(symbols, notNullValue());
        assertThat(symbols.size(), greaterThan(0));

        SymbolTickResponse hrStats = sandboxKucoinRestClient.symbolAPI().get24hrStats("ETH-BTC");
        assertThat(hrStats, notNullValue());
    }

    /**
     * The live and sandbox APIs seem to be divergent. Test against the live API too where
     * possible
     */
    @Test
    public void symbolAPILive() throws Exception {
        TickerResponse ticker = liveKucoinRestClient.symbolAPI().getTicker("ETH-BTC");
        assertThat(ticker, notNullValue());

        List<SymbolResponse> symbols = liveKucoinRestClient.symbolAPI().getSymbols();
        assertThat(symbols, notNullValue());
        assertThat(symbols.size(), greaterThan(0));

        SymbolTickResponse hrStats = liveKucoinRestClient.symbolAPI().get24hrStats("ETH-BTC");
        assertThat(hrStats, notNullValue());
    }

    @Test
    public void orderBookAPI() throws Exception {
        OrderBookResponse fullOrderBookAggregated = sandboxKucoinRestClient.orderBookAPI().getFullOrderBookAggregated("ETH-BTC");
        assertThat(fullOrderBookAggregated, notNullValue());

        OrderBookResponse fullOrderBookAtomic = sandboxKucoinRestClient.orderBookAPI().getFullOrderBookAtomic("ETH-BTC");
        assertThat(fullOrderBookAtomic, notNullValue());

        OrderBookResponse partOrderBookAggregated = sandboxKucoinRestClient.orderBookAPI().getPartOrderBookAggregated("ETH-BTC");
        assertThat(partOrderBookAggregated, notNullValue());
    }

    @Test
    public void historyAPI() throws Exception {
        List<TradeHistoryResponse> tradeHistories = sandboxKucoinRestClient.historyAPI().getTradeHistories("ETH-BTC");
        assertThat(tradeHistories, notNullValue());
        assertThat(tradeHistories.size(), greaterThan(0));

        List<List<String>> historicRates = sandboxKucoinRestClient.historyAPI().getHistoricRates("ETH-BTC", startAt, endAt, "1min");
        assertThat(historicRates, notNullValue());
        assertThat(historicRates.size(), greaterThan(0));
    }

    @Test
    public void currencyAPI() throws Exception {
        List<CurrencyResponse> currencies = sandboxKucoinRestClient.currencyAPI().getCurrencies();
        assertThat(currencies, notNullValue());
        assertThat(currencies.size(), greaterThan(0));

        CurrencyDetailResponse kcs = sandboxKucoinRestClient.currencyAPI().getCurrencyDetail("KCS");
        assertThat(kcs, notNullValue());
    }

    @Test
    public void timeAPI() throws Exception {
        Long serverTimeStamp = sandboxKucoinRestClient.timeAPI().getServerTimeStamp();
        assertThat(System.currentTimeMillis() - serverTimeStamp, lessThanOrEqualTo(5000L));
    }

}