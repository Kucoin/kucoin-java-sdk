/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import com.google.common.collect.Lists;
import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;
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

        Pagination<SubUserInfoResponse> subUserInfoResponsePagination = sandboxKucoinRestClient.userAPI().pageListSubUsers(1, 10);
        assertThat(subUserInfoResponsePagination, notNullValue());
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

        TransferableBalanceResponse transferable = sandboxKucoinRestClient.accountAPI().transferable("BTC", "MARGIN", null);
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

        UserSummaryInfoResponse userSummaryInfo = sandboxKucoinRestClient.accountAPI().getUserSummaryInfo();
        assertThat(userSummaryInfo, notNullValue());

        SubUserCreateResponse subUserCreateResponse = sandboxKucoinRestClient.accountAPI().createSubUser("TestSubUser001","1234abcd", "Spot", "testRemark");
        assertThat(subUserCreateResponse, notNullValue());

        List<SubApiKeyResponse> subApiKeyResponses = sandboxKucoinRestClient.accountAPI().getSubApiKey("TestSubUser001", null);
        assertThat(subApiKeyResponses, notNullValue());

        SubApiKeyResponse subApiKeyCreateResponse = sandboxKucoinRestClient.accountAPI().createSubApiKey("TestSubUser001", "12345678", "remark test", null, null, null);
        assertThat(subApiKeyCreateResponse, notNullValue());

        SubApiKeyResponse subApiKeyUpdateResponse = sandboxKucoinRestClient.accountAPI().updateSubApiKey("TestSubUser001", "6463406d22b2b50001c22af1", "12345678", "General,Trade", "127.0.0.1", "360");
        assertThat(subApiKeyUpdateResponse, notNullValue());

        SubApiKeyResponse subApiKeyDeleteResponse = sandboxKucoinRestClient.accountAPI().deleteSubApiKey("TestSubUser001", "6463406d22b2b50001c22af1", "12345678");
        assertThat(subApiKeyDeleteResponse, notNullValue());

        Pagination<SubAccountBalanceResponse> subAccountPageList = sandboxKucoinRestClient.accountAPI().getSubAccountPageList(1, 10);
        assertThat(subAccountPageList, notNullValue());

        List<AccountBalancesResponse> transferredToHFAccountResponse = sandboxKucoinRestClient.accountAPI().transferToHFAccount(String.valueOf(System.currentTimeMillis()), "BTC", "main", BigDecimal.ONE);
        assertThat(transferredToHFAccountResponse, notNullValue());

        List<AccountDetailResponse> hfAccountLedgers = sandboxKucoinRestClient.accountAPI().getHFAccountLedgers("BTC", null, null, null, null, null, null);
        assertThat(hfAccountLedgers, notNullValue());
    }

    @Test
    public void fillAPI() throws Exception {
        Pagination<TradeResponse> fills = sandboxKucoinRestClient.fillAPI().listFills("KCS-USDT", null, "buy",
                null,"TRADE", startAt, endAt, 10, 10);
        assertThat(fills, notNullValue());

        Pagination<TradeResponse> limitFillsPageList = sandboxKucoinRestClient.fillAPI().queryLimitFillsPageList(10, 1);
        assertThat(limitFillsPageList, notNullValue());

        HFTradeResponse hfTradeResponse = sandboxKucoinRestClient.fillAPI().queryHFTrades("KCS-USDT", null, null, null, null, null, null, null);
        assertThat(hfTradeResponse, notNullValue());
    }

    @Test
    public void orderAPI() throws Exception {

        List<UserFeeResponse> userFees = sandboxKucoinRestClient.orderAPI().getUserTradeFees("BTC-USDT,KCS-USDT");
        assertThat(userFees, notNullValue());

        UserFeeResponse userBaseFee = sandboxKucoinRestClient.orderAPI().getUserBaseFee("1");
        assertThat(userBaseFee, notNullValue());

        Pagination<OrderResponse> limitOrderPageList = sandboxKucoinRestClient.orderAPI().queryLimitOrderPageList(10, 1);
        assertThat(limitOrderPageList, notNullValue());

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

        HFOrderCreateRequest hfOrderCreateRequest = HFOrderCreateRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy")
                .symbol("ETH-BTC").type("limit").clientOid(String.valueOf(System.currentTimeMillis())).build();
        HFOrderCreateResponse hfOrderCreateResponse = sandboxKucoinRestClient.orderAPI().createHFOrder(hfOrderCreateRequest);
        assertThat(hfOrderCreateResponse, notNullValue());

        HFOrderSyncCreateResponse hfOrderSyncCreateResponse = sandboxKucoinRestClient.orderAPI().syncCreateHFOrder(hfOrderCreateRequest);
        assertThat(hfOrderSyncCreateResponse, notNullValue());

        HFOrderMultiCreateRequest multiCreateRequest = new HFOrderMultiCreateRequest();
        multiCreateRequest.setOrderList(Lists.newArrayList(hfOrderCreateRequest));
        List<HFOrderMultiCreateResponse> hfOrderCreateResponses = sandboxKucoinRestClient.orderAPI().createMultipleHFOrders(multiCreateRequest);
        assertThat(hfOrderCreateResponses, notNullValue());

        List<HFOrderSyncMultiCreateResponse> hfOrderSyncCreateResponses = sandboxKucoinRestClient.orderAPI().syncCreateMultipleHFOrders(multiCreateRequest);
        assertThat(hfOrderSyncCreateResponses, notNullValue());

        HFOrderAlterRequest alterRequest = new HFOrderAlterRequest();
        alterRequest.setSymbol("ETH-USDT");
        alterRequest.setClientOid("clientOid");
        alterRequest.setNewPrice("1");
        alterRequest.setNewSize("2");
        HFOrderAlterResponse hfOrderAlterResponse = sandboxKucoinRestClient.orderAPI().alterHFOrder(alterRequest);
        assertThat(hfOrderAlterResponse, notNullValue());

        HFOrderCancelResponse hfOrderCancelResponse = sandboxKucoinRestClient.orderAPI().cancelHFOrder("orderId", "ETH-USDT");
        assertThat(hfOrderCancelResponse, notNullValue());

        HFOrderSyncCancelResponse syncCancelResponse = sandboxKucoinRestClient.orderAPI().syncCancelHFOrder("orderId", "ETH-USDT");
        assertThat(syncCancelResponse, notNullValue());

        HFOrderCancelByClientOidResponse hfOrderCancelByClientOidResponse = sandboxKucoinRestClient.orderAPI().cancelHFOrderByClientOid("clientOid", "ETH-USDT");
        assertThat(hfOrderCancelByClientOidResponse, notNullValue());

        HFOrderSyncCancelResponse syncCancelHFOrderByClientOid = sandboxKucoinRestClient.orderAPI().syncCancelHFOrderByClientOid("clientOid", "ETH-USDT");
        assertThat(syncCancelHFOrderByClientOid, notNullValue());

        HFOrderCancelSizeResponse hfOrderCancelSizeResponse = sandboxKucoinRestClient.orderAPI().cancelHFOrderSize("orderId", "ETH-USDT", "1");
        assertThat(hfOrderCancelSizeResponse, notNullValue());

        String cancelHFOrdersBySymbolResponse = sandboxKucoinRestClient.orderAPI().cancelHFOrdersBySymbol("ETH-USDT");
        assertThat(cancelHFOrdersBySymbolResponse, notNullValue());

        List<HFOrderResponse> activeHFOrders = sandboxKucoinRestClient.orderAPI().getActiveHFOrders("ETH-USDT");
        assertThat(activeHFOrders, notNullValue());

        HFOrderActiveSymbolQueryResponse activeHFOrderSymbols = sandboxKucoinRestClient.orderAPI().getActiveHFOrderSymbols();
        assertThat(activeHFOrderSymbols, notNullValue());

        HFDoneOrderQueryResponse doneHFOrders = sandboxKucoinRestClient.orderAPI().getDoneHFOrders("ETH-USDT", "buy", "limit", null, null, 0L, 100);
        assertThat(doneHFOrders, notNullValue());

        HFOrderResponse hfOrder = sandboxKucoinRestClient.orderAPI().getHFOrder("645b6755952dc10001be52f6", "ETH-USDT");
        assertThat(hfOrder, notNullValue());

        HFOrderResponse hfOrderByClientOid = sandboxKucoinRestClient.orderAPI().getHFOrderByClientOid("clientOid", "ETH-USDT");
        assertThat(hfOrderByClientOid, notNullValue());

        HFOrderDeadCancelResponse hfOrderDeadCancelResponse = sandboxKucoinRestClient.orderAPI().deadCancelHFOrder(5, null);
        assertThat(hfOrderDeadCancelResponse, notNullValue());

        HFOrderDeadCancelQueryResponse hfOrderDeadCancelQueryResponse = sandboxKucoinRestClient.orderAPI().queryHFOrderDeadCancel();
        assertThat(hfOrderDeadCancelQueryResponse, notNullValue());
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

        StopOrderCancelRequest cancelRequest = new StopOrderCancelRequest();
        cancelRequest.setSymbol("ETH-BTC");
        OrderCancelResponse ordersCancelResponse = sandboxKucoinRestClient.stopOrderAPI().cancelStopOrders(cancelRequest);
        assertThat(ordersCancelResponse, notNullValue());

        OrderCancelResponse orderCancelByClientOidResponse = sandboxKucoinRestClient.stopOrderAPI().cancelStopOrderByClientOid("oid");
        assertThat(orderCancelByClientOidResponse, notNullValue());

        List<StopOrderResponse> stopOrderByOidResponse = sandboxKucoinRestClient.stopOrderAPI().getStopOrderByClientOid("oid", null);
        assertThat(stopOrderByOidResponse, notNullValue());

        StopOrderQueryRequest queryRequest = new StopOrderQueryRequest();
        Pagination<StopOrderResponse> stopOrderResponsePagination = sandboxKucoinRestClient.stopOrderAPI().queryStopOrders(queryRequest);
        assertThat(stopOrderResponsePagination, notNullValue());
    }

    @Test
    public void withdrawalAPI() throws Exception {
        Pagination<WithdrawResponse> withdrawList = sandboxKucoinRestClient.withdrawalAPI().getWithdrawList("KCS", "FAILURE",
                startAt, endAt, 1, 10);
        assertThat(withdrawList, notNullValue());

        Pagination<WithdrawResponse> histWithdrawPageList = sandboxKucoinRestClient.withdrawalAPI().getHistWithdrawPageList("KCS", null,
                startAt, endAt, 1, 10);
        assertThat(histWithdrawPageList, notNullValue());

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

        List<DepositAddressResponse> depositAddressResponseList = sandboxKucoinRestClient.depositAPI().getDepositAddresses("USDT");
        assertThat(depositAddressResponseList, notNullValue());

        Pagination<DepositResponse> histDepositPageList = sandboxKucoinRestClient.depositAPI().getHistDepositPageList("KCS", "SUCCESS", startAt, endAt, 1, 10);
        assertThat(histDepositPageList, notNullValue());
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

        List<SymbolResponse> symbolList = sandboxKucoinRestClient.symbolAPI().getSymbolList("BTC");
        assertThat(symbolList, notNullValue());
    }

    @Test
    public void orderBookAPI() throws Exception {

        OrderBookResponse fullLevel2OrderBook = sandboxKucoinRestClient.orderBookAPI().getAllLevel2OrderBook("ETH-BTC");
        assertThat(fullLevel2OrderBook, notNullValue());

        OrderBookResponse top20Level2OrderBook = sandboxKucoinRestClient.orderBookAPI().getTop20Level2OrderBook("BTC-USDT");
        assertThat(top20Level2OrderBook, notNullValue());

        OrderBookResponse top100Level2OrderBook = sandboxKucoinRestClient.orderBookAPI().getTop100Level2OrderBook("BTC-USDT");
        assertThat(top100Level2OrderBook, notNullValue());
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



        CurrencyDetailV2Response kcsv2 = liveKucoinRestClient.currencyAPI().getCurrencyDetailV2("KCS", null);
        assertThat(kcsv2, notNullValue());

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

        List<MarginPriceStrategyResponse> marginPriceStrategy = sandboxKucoinRestClient.marginAPI().getMarginPriceStrategy("cross");
        assertThat(marginPriceStrategy, notNullValue());
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

        sandboxKucoinRestClient.loanAPI().cancelLendOrder("orderId");
    }

    @Test
    public void IsolatedAPI() throws Exception {
        List<IsolatedSymbolResponse> isolatedSymbolResponses = sandboxKucoinRestClient.isolatedAPI().getSymbols();
        assertThat(isolatedSymbolResponses, notNullValue());

        IsolatedAccountResponse isolatedAccountResponse = sandboxKucoinRestClient.isolatedAPI().getAccounts("USDT");
        assertThat(isolatedAccountResponse, notNullValue());

        IsolatedAssetResponse isolatedAssetResponse = sandboxKucoinRestClient.isolatedAPI().getAccount("BTC-USDT");
        assertThat(isolatedAssetResponse, notNullValue());

        IsolatedBorrowResponse borrowResponse = sandboxKucoinRestClient.isolatedAPI().borrow("BTC-USDT", "USDT", BigDecimal.TEN, "FOK", null, null);
        assertThat(borrowResponse, notNullValue());

        Pagination<IsolatedBorrowOutstandingResponse> borrowOutstandingResponsePagination = sandboxKucoinRestClient.isolatedAPI().queryBorrowOutstanding("BTC-USDT", "USDT", 10, 1);
        assertThat(borrowOutstandingResponsePagination, notNullValue());

        Pagination<IsolatedBorrowRepaidResponse> borrowRepaidResponsePagination = sandboxKucoinRestClient.isolatedAPI().queryBorrowRepaid("BTC-USDT", "USDT", 10, 1);
        assertThat(borrowRepaidResponsePagination, notNullValue());

        sandboxKucoinRestClient.isolatedAPI().repayAll("BTC-USDT", "USDT", BigDecimal.TEN, "RECENTLY_EXPIRE_FIRST");

        sandboxKucoinRestClient.isolatedAPI().repaySingle("BTC-USDT", "USDT", BigDecimal.TEN, "loadId123456789000000000");
    }

}