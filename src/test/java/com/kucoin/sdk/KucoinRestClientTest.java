/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import com.google.common.collect.Lists;
import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import com.kucoin.sdk.rest.request.AccountTransferV2Request;
import com.kucoin.sdk.rest.request.BorrowRecordQueryRequest;
import com.kucoin.sdk.rest.request.BorrowRequest;
import com.kucoin.sdk.rest.request.LendRequest;
import com.kucoin.sdk.rest.request.MarginOrderCreateRequest;
import com.kucoin.sdk.rest.request.MultiOrderCreateRequest;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.request.RepayAllRequest;
import com.kucoin.sdk.rest.request.RepaySeqStrategy;
import com.kucoin.sdk.rest.request.RepaySingleRequest;
import com.kucoin.sdk.rest.request.StopOrderCreateRequest;
import com.kucoin.sdk.rest.request.ToggleAutoLendRequest;
import com.kucoin.sdk.rest.request.WithdrawApplyRequest;
import com.kucoin.sdk.rest.response.AccountBalanceResponse;
import com.kucoin.sdk.rest.response.AccountBalancesResponse;
import com.kucoin.sdk.rest.response.AccountDetailResponse;
import com.kucoin.sdk.rest.response.ActiveLendItem;
import com.kucoin.sdk.rest.response.ActiveOrderResponse;
import com.kucoin.sdk.rest.response.AllTickersResponse;
import com.kucoin.sdk.rest.response.BorrowOutstandingResponse;
import com.kucoin.sdk.rest.response.BorrowQueryResponse;
import com.kucoin.sdk.rest.response.BorrowRepaidResponse;
import com.kucoin.sdk.rest.response.BorrowResponse;
import com.kucoin.sdk.rest.response.CurrencyDetailResponse;
import com.kucoin.sdk.rest.response.CurrencyResponse;
import com.kucoin.sdk.rest.response.DoneLendItem;
import com.kucoin.sdk.rest.response.LastTradeResponse;
import com.kucoin.sdk.rest.response.LendAssetsResponse;
import com.kucoin.sdk.rest.response.LendResponse;
import com.kucoin.sdk.rest.response.Level3Response;
import com.kucoin.sdk.rest.response.MarginAccountResponse;
import com.kucoin.sdk.rest.response.MarginConfigResponse;
import com.kucoin.sdk.rest.response.MarginOrderCreateResponse;
import com.kucoin.sdk.rest.response.MarkPriceResponse;
import com.kucoin.sdk.rest.response.MarketItemResponse;
import com.kucoin.sdk.rest.response.MultiOrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderCancelResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.OrderResponse;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.ServiceStatusResponse;
import com.kucoin.sdk.rest.response.SettledTradeItem;
import com.kucoin.sdk.rest.response.StopOrderResponse;
import com.kucoin.sdk.rest.response.SubAccountBalanceResponse;
import com.kucoin.sdk.rest.response.SubUserInfoResponse;
import com.kucoin.sdk.rest.response.SymbolResponse;
import com.kucoin.sdk.rest.response.SymbolTickResponse;
import com.kucoin.sdk.rest.response.TickerResponse;
import com.kucoin.sdk.rest.response.TradeHistoryResponse;
import com.kucoin.sdk.rest.response.TradeResponse;
import com.kucoin.sdk.rest.response.TransferableBalanceResponse;
import com.kucoin.sdk.rest.response.UnsettledTradeItem;
import com.kucoin.sdk.rest.response.UserFeeResponse;
import com.kucoin.sdk.rest.response.WithdrawQuotaResponse;
import com.kucoin.sdk.rest.response.WithdrawResponse;
import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.IsNull.notNullValue;

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
                .withApiKey("6040ba17365ac600068963ed", "b69e3410-5215-4360-a2c8-569a6a669141", "1qaz2wsx")
                // Version number of api-key
                .withApiKeyVersion(ApiKeyVersionEnum.V2.getVersion())
                .buildRestClient();

        liveKucoinRestClient = new KucoinClientBuilder().withBaseUrl("https://openapi-v2.kucoin.com")
                .buildRestClient();
        startAt = LocalDateTime.of(2019, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.of("+8"));
        endAt = LocalDateTime.of(2019, 1, 21, 0, 0, 0).toEpochSecond(ZoneOffset.of("+8"));
    }


    @Test
    public void userAPI() throws Exception {
        List<SubUserInfoResponse> subUserInfoResponses = sandboxKucoinRestClient.userAPI().listSubUsers();
        assertThat(subUserInfoResponses.size(), Is.is(1));
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
        assertThat(accountBalancesResponses.size(), Is.is(3));
        Optional<AccountBalancesResponse> main = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("main")).findFirst();
        Optional<AccountBalancesResponse> trade = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("trade")).findFirst();
        assertThat(main.isPresent(), Is.is(true));
        assertThat(trade.isPresent(), Is.is(true));

        String tradeAccountId = trade.get().getId();
        AccountBalanceResponse account = sandboxKucoinRestClient.accountAPI().getAccount(tradeAccountId);
        assertThat(account, notNullValue());

        Map<String, String> result = sandboxKucoinRestClient.accountAPI().innerTransfer2(new AccountTransferV2Request(String.valueOf(System.currentTimeMillis()),"BTC", "main", "trade", BigDecimal.ONE));
        assertThat(result, notNullValue());

        TransferableBalanceResponse transferable = sandboxKucoinRestClient.accountAPI().transferable("BTC", "MARGIN");
        assertThat(transferable, notNullValue());

        Pagination<AccountDetailResponse> accountHistory = sandboxKucoinRestClient.accountAPI().getAccountLedgers("BTC",
                null, null, startAt, System.currentTimeMillis(), 1, 10);
        assertThat(accountHistory, notNullValue());

        List<SubAccountBalanceResponse> subAccountBalanceResponses = sandboxKucoinRestClient.accountAPI().listSubAccounts();
        Optional<SubAccountBalanceResponse> henryPeach = subAccountBalanceResponses.stream()
                .filter(subAccountBalanceResponse -> subAccountBalanceResponse.getSubName().equals("nilmiao01")).findFirst();
        assertThat(henryPeach.isPresent(), Is.is(true));

        String subUserId = henryPeach.get().getSubUserId();
        SubAccountBalanceResponse subAccount = sandboxKucoinRestClient.accountAPI().getSubAccount(subUserId);
        assertThat(subAccount, notNullValue());

        Map<String, String> transferResult = sandboxKucoinRestClient.accountAPI().transferBetweenSubAndMasterV2(
                String.valueOf(System.currentTimeMillis()), "BTC", BigDecimal.valueOf(0.00000001),
                "OUT", subUserId,"MAIN", "MAIN");
        assertThat(transferResult, notNullValue());

        exception.expect(KucoinApiException.class);
        exception.expectMessage("account already exists");

        Map<String, String> create = sandboxKucoinRestClient.accountAPI().createAccount("KCS", "main");
        assertThat(create, notNullValue());

    }

    @Test
    public void fillAPI() throws Exception {
        Pagination<TradeResponse> fills = sandboxKucoinRestClient.fillAPI().listFills("KCS-USDT", null, "buy",
                null,"TRADE", startAt, endAt, 10, 10);
        assertThat(fills, notNullValue());
    }

    @Test
    public void orderAPI() throws Exception {

        List<UserFeeResponse> userFees = sandboxKucoinRestClient.orderAPI().getUserTradeFees("BTC-USDT,KCS-USDT");
        assertThat(userFees, notNullValue());

        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy").tradeType("TRADE")
                .symbol("ETH-BTC").type("limit").clientOid(String.valueOf(System.currentTimeMillis())).build();
        OrderCreateResponse order = sandboxKucoinRestClient.orderAPI().createOrder(request);
        assertThat(order, notNullValue());

        MultiOrderCreateRequest multiOrderRequest = new MultiOrderCreateRequest();
        multiOrderRequest.setSymbol("ETH-BTC");
        OrderCreateApiRequest request2 = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy").tradeType("TRADE")
                .symbol("ETH-BTC").type("limit").clientOid(String.valueOf(System.currentTimeMillis())).build();
        request.setClientOid(String.valueOf(System.currentTimeMillis()));
        multiOrderRequest.setOrderList(Lists.newArrayList(request,request2));
        MultiOrderCreateResponse multiOrderResponse = sandboxKucoinRestClient.orderAPI().createMultipleOrders(multiOrderRequest);
        assertThat(multiOrderResponse, notNullValue());

        Pagination<OrderResponse> orderResponsePagination = sandboxKucoinRestClient.orderAPI().listOrders("ETH-BTC",
                null, null,"TRADE", "active", null, null, 10, 1);
        assertThat(orderResponsePagination, notNullValue());

        OrderResponse orderResponse = sandboxKucoinRestClient.orderAPI().getOrder(order.getOrderId());
        assertThat(orderResponse, notNullValue());

        ActiveOrderResponse activeOrder = sandboxKucoinRestClient.orderAPI().getOrderByClientOid(request.getClientOid());
        assertThat(activeOrder, notNullValue());

        OrderCancelResponse orderCancelResponse = sandboxKucoinRestClient.orderAPI().cancelOrder(order.getOrderId());
        assertThat(orderCancelResponse, notNullValue());

        OrderCancelResponse orderCancel = sandboxKucoinRestClient.orderAPI().cancelOrderByClientOid(request.getClientOid());
        assertThat(orderCancel, notNullValue());

        OrderCancelResponse ordersCancelResponse = sandboxKucoinRestClient.orderAPI().cancelAllOrders("ETH-BTC", "TRADE");
        assertThat(ordersCancelResponse, notNullValue());
    }

    @Test
    public void stopOrderAPI() throws Exception {
        StopOrderCreateRequest request = StopOrderCreateRequest.builder()
                .price(BigDecimal.valueOf(0.0001)).size(BigDecimal.ONE).side("buy")
                .stop("loss").stopPrice(BigDecimal.valueOf(0.0002))
                .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
        OrderCreateResponse stopOrder = sandboxKucoinRestClient.stopOrderAPI().createStopOrder(request);
        assertThat(stopOrder, notNullValue());

        StopOrderResponse stopOrderResponse = sandboxKucoinRestClient.stopOrderAPI().getStopOrder(stopOrder.getOrderId());
        assertThat(stopOrderResponse, notNullValue());

        OrderCancelResponse orderCancelResponse = sandboxKucoinRestClient.stopOrderAPI().cancelStopOrder(stopOrder.getOrderId());
        assertThat(orderCancelResponse, notNullValue());
    }

    @Test
    public void withdrawalAPI() throws Exception {
        Pagination<WithdrawResponse> withdrawList = sandboxKucoinRestClient.withdrawalAPI().getWithdrawList("KCS", "FAILURE",
                startAt, endAt, 1, 10);
        assertThat(withdrawList, notNullValue());

        WithdrawQuotaResponse kcs = sandboxKucoinRestClient.withdrawalAPI().getWithdrawQuotas("KCS", null);
        assertThat(kcs, notNullValue());
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
        sandboxKucoinRestClient.depositAPI().createDepositAddress("KCS", null);

        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        sandboxKucoinRestClient.depositAPI().getDepositAddress("KCS", null);

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
        TickerResponse ticker = sandboxKucoinRestClient.symbolAPI().getTicker("ETH-BTC");
        assertThat(ticker, notNullValue());

        List<SymbolResponse> symbols = sandboxKucoinRestClient.symbolAPI().getSymbols();
        assertThat(symbols, notNullValue());
        assertThat(symbols.size(), greaterThan(0));

        SymbolTickResponse hrStats = sandboxKucoinRestClient.symbolAPI().get24hrStats("ETH-BTC");
        assertThat(hrStats, notNullValue());

        List<String> marketList = sandboxKucoinRestClient.symbolAPI().getMarketList();
        assertThat(marketList.size(), greaterThan(1));

        AllTickersResponse allTickers = sandboxKucoinRestClient.symbolAPI().getAllTickers();
        assertThat(allTickers, notNullValue());
        assertThat(allTickers.getTicker().size(), greaterThan(1));

    }

    @Test
    public void orderBookAPI() throws Exception {
        Level3Response fullOrderBook = sandboxKucoinRestClient.orderBookAPI().getFullOrderBook("ETH-BTC");
        assertThat(fullOrderBook, notNullValue());
    }

    @Test
    public void historyAPI() throws Exception {
        List<TradeHistoryResponse> tradeHistories = sandboxKucoinRestClient.historyAPI().getTradeHistories("ETH-BTC");
        assertThat(tradeHistories, notNullValue());
        // TODO broken
        // assertThat(tradeHistories.size(), greaterThan(0));

        List<List<String>> historicRates = sandboxKucoinRestClient.historyAPI().getHistoricRates("ETH-BTC", startAt, endAt, "1min");
        assertThat(historicRates, notNullValue());
        assertThat(historicRates.size(), greaterThan(0));
    }

    @Test
    public void currencyAPI() throws Exception {
        List<CurrencyResponse> currencies = sandboxKucoinRestClient.currencyAPI().getCurrencies();
        assertThat(currencies, notNullValue());
        assertThat(currencies.size(), greaterThan(0));

        CurrencyDetailResponse kcs = sandboxKucoinRestClient.currencyAPI().getCurrencyDetail("KCS", null);
        assertThat(kcs, notNullValue());

        Map<String, BigDecimal> fiatPrice = sandboxKucoinRestClient.currencyAPI().getFiatPrice("USD", "KCS, BTC");
        assertThat(fiatPrice, notNullValue());
        assertThat(fiatPrice.keySet().size(), greaterThan(1));
    }

    @Test
    public void timeAPI() throws Exception {
        Long serverTimeStamp = sandboxKucoinRestClient.timeAPI().getServerTimeStamp();
        assertThat(System.currentTimeMillis() - serverTimeStamp, lessThanOrEqualTo(5000L));
    }

    @Test
    public void commonAPI() throws Exception {
        ServiceStatusResponse serverStatus = sandboxKucoinRestClient.commonAPI().getServerStatus();
        assertThat(serverStatus, notNullValue());
    }

    @Test
    public void marginAPI() throws Exception {

        MarkPriceResponse markPrice = sandboxKucoinRestClient.marginAPI().getMarkPrice("USDT-BTC");
        assertThat(markPrice, notNullValue());

        MarginConfigResponse marginConfig = sandboxKucoinRestClient.marginAPI().getMarginConfig();
        assertThat(marginConfig, notNullValue());

        MarginAccountResponse marginAccount = sandboxKucoinRestClient.marginAPI().getMarginAccount();
        assertThat(marginAccount, notNullValue());


        MarginOrderCreateRequest request = MarginOrderCreateRequest.builder()
                .price(BigDecimal.valueOf(20)).size(new BigDecimal("0.0130")).side("sell")
                .symbol("ATOM-USDT").type("limit").clientOid(String.valueOf(System.currentTimeMillis()))
                .build();
        MarginOrderCreateResponse marginOrderResponse = sandboxKucoinRestClient.marginAPI().createMarginOrder(request);
        assertThat(marginOrderResponse, notNullValue());


    }

    @Test
    public void loanAPI() throws Exception {

        List<LendAssetsResponse> lendAssets = sandboxKucoinRestClient.loanAPI().queryLendAssets("USDT");
        assertThat(lendAssets, notNullValue());

        List<MarketItemResponse> marketItem = sandboxKucoinRestClient.loanAPI().queryMarket("USDT", 7);
        assertThat(marketItem, notNullValue());

        List<LastTradeResponse> lastTrade = sandboxKucoinRestClient.loanAPI().queryLastTrade("USDT");
        assertThat(lastTrade, notNullValue());

        BorrowRequest borrowRequest = BorrowRequest.builder()
                .currency("USDT").type("IOC").size(BigDecimal.ONE).maxRate(new BigDecimal("0.001")).term("7")
                .build();
        BorrowResponse borrow = sandboxKucoinRestClient.loanAPI().borrow(borrowRequest);
        assertThat(borrow, notNullValue());

        BorrowQueryResponse borrowQuery = sandboxKucoinRestClient.loanAPI().queryBorrow(borrow.getOrderId());
        assertThat(borrowQuery, notNullValue());

        BorrowRecordQueryRequest borrowRecordQueryRequest = BorrowRecordQueryRequest.builder()
                .currency("USDT")
                .build();
        Pagination<BorrowOutstandingResponse> pageBorrowOutstanding = sandboxKucoinRestClient.loanAPI().queryBorrowOutstanding(borrowRecordQueryRequest);
        assertThat(pageBorrowOutstanding, notNullValue());

        BorrowRecordQueryRequest borrowRepaidRequest = BorrowRecordQueryRequest.builder()
                .currency("USDT")
                .build();
        Pagination<BorrowRepaidResponse> pageBorrowRepaid = sandboxKucoinRestClient.loanAPI().queryBorrowRepaid(borrowRepaidRequest);
        assertThat(pageBorrowRepaid, notNullValue());

        RepayAllRequest repayAllRequest = RepayAllRequest.builder()
                .currency("USDT")
                .size(BigDecimal.TEN)
                .sequence(RepaySeqStrategy.HIGHEST_RATE_FIRST)
                .build();
        sandboxKucoinRestClient.loanAPI().repayAll(repayAllRequest);

        RepaySingleRequest repaySingleRequest = RepaySingleRequest.builder()
                .currency("USDT")
                .size(BigDecimal.TEN)
                .tradeId(borrowQuery.getMatchList().get(0).getTradeId())
                .build();
        sandboxKucoinRestClient.loanAPI().repaySingle(repaySingleRequest);
        LendRequest lendRequest = LendRequest.builder()
                .currency("USDT")
                .dailyIntRate(new BigDecimal("0.001"))
                .size(BigDecimal.TEN)
                .term(7)
                .build();
        LendResponse lend = sandboxKucoinRestClient.loanAPI().lend(lendRequest);
        assertThat(lend, notNullValue());
        // sandboxKucoinRestClient.loanAPI().cancelLendOrder(lend.getOrderId());

        ToggleAutoLendRequest toggleAutoLendRequest = ToggleAutoLendRequest.builder()
                .currency("USDT")
                .isEnable(false)
                .term(28)
                .retainSize(new BigDecimal("10000000"))
                .dailyIntRate(new BigDecimal("0.0015"))
                .build();
        sandboxKucoinRestClient.loanAPI().toggleAutoLend(toggleAutoLendRequest);

        Pagination<ActiveLendItem> pageActiveLend = sandboxKucoinRestClient.loanAPI().queryActiveLend(
                "USDT", 1, 10);
        assertThat(pageActiveLend, notNullValue());

        Pagination<DoneLendItem> pageDoneLend = sandboxKucoinRestClient.loanAPI().queryDoneLend(
                "USDT", 1, 10);
        assertThat(pageDoneLend, notNullValue());

        Pagination<UnsettledTradeItem> pageUnsettledTrade = sandboxKucoinRestClient.loanAPI().queryUnsettledTrade(
                "USDT", 1, 10);
        assertThat(pageUnsettledTrade, notNullValue());

        Pagination<SettledTradeItem> pageSettledTrade = sandboxKucoinRestClient.loanAPI().querySettledTrade(
                "USDT", 1, 10);
        assertThat(pageSettledTrade, notNullValue());


    }

}