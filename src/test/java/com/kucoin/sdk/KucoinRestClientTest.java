/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import com.google.common.collect.Lists;
import com.kucoin.sdk.enums.AccountTypeEnum;
import com.kucoin.sdk.enums.LendingStatusEnum;
import com.kucoin.sdk.enums.TimeInForceEnum;
import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import com.kucoin.sdk.rest.request.*;
import com.kucoin.sdk.rest.response.*;
import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by chenshiwei on 2019/1/21.
 */
public class KucoinRestClientTest {
    private static KucoinRestClient liveKucoinRestClient;

    private static Long startAt;
    private static Long endAt;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
        liveKucoinRestClient = new KucoinClientBuilder().withBaseUrl("https://openapi-v2.kucoin.com")
                .withApiKey("", "", "")
                // Version number of api-key
                .withApiKeyVersion(ApiKeyVersionEnum.V2.getVersion())
                .buildRestClient();

        startAt = LocalDateTime.of(2024, 6, 16, 0, 0, 0).toEpochSecond(ZoneOffset.of("+8"));
        endAt = LocalDateTime.of(2024, 6, 30, 0, 0, 0).toEpochSecond(ZoneOffset.of("+8"));
    }


    @Test
    public void userAPI() throws Exception {
        List<SubUserInfoResponse> subUserInfoResponses = liveKucoinRestClient.userAPI().listSubUsers();
        assertThat(subUserInfoResponses.size(), Is.is(1));

        Pagination<SubUserInfoResponse> subUserInfoResponsePagination = liveKucoinRestClient.userAPI().pageListSubUsers(1, 10);
        assertThat(subUserInfoResponsePagination, notNullValue());
    }

    @Test
    public void accountLedgers() throws IOException {
        Pagination<AccountDetailResponse> accountHistory = liveKucoinRestClient.accountAPI().getAccountLedgers("USDT",
                null, null, startAt * 1000, System.currentTimeMillis(), 1, 10);
        assertThat(accountHistory, notNullValue());
    }

    /**
     * Check that we can get all account balances.
     */
    @Test
    public void accountAPIMulti() throws Exception {
        List<AccountBalancesResponse> accountBalancesResponses
                = liveKucoinRestClient.accountAPI().listAccounts("USDT", null);
        assertThat(accountBalancesResponses.size(), Is.is(6));
    }

    @Test
    public void subAccountAPIQuery() throws Exception {
        List<SubApiKeyResponse> subApiKeyResponses = liveKucoinRestClient.accountAPI().getSubApiKey("tcwspot01", null);
        assertThat(subApiKeyResponses, notNullValue());
    }

    @Test
    public void testUniversalTransfer() throws IOException {
        UniversalTransferRequest request = UniversalTransferRequest.builder()
                .clientOid(UUID.randomUUID().toString())
                .currency("USDT")
                .amount(new BigDecimal("1"))
                .fromAccountType(AccountTypeEnum.TRADE.name())
                .toAccountType(AccountTypeEnum.MAIN.name())
                .type("INTERNAL")
                .build();
        UniversalTransferResponse universalTransfer = liveKucoinRestClient.accountAPI().universalTransfer(request);
        assertThat(universalTransfer, notNullValue());
    }

    @Test
    public void accountAPI() throws Exception {
        List<AccountBalancesResponse> accountBalancesResponses = liveKucoinRestClient.accountAPI().listAccounts("USDT", "trade_hf");
        assertThat(accountBalancesResponses.size(), Is.is(3));
        Optional<AccountBalancesResponse> main = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("main")).findFirst();
        Optional<AccountBalancesResponse> trade = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("trade")).findFirst();
        assertThat(main.isPresent(), Is.is(true));
        assertThat(trade.isPresent(), Is.is(true));

        String tradeAccountId = trade.get().getId();
        AccountBalanceResponse account = liveKucoinRestClient.accountAPI().getAccount(tradeAccountId);
        assertThat(account, notNullValue());

        Map<String, String> result = liveKucoinRestClient.accountAPI().innerTransfer2(new AccountTransferV2Request(String.valueOf(System.currentTimeMillis()), "BTC", "main", "trade", BigDecimal.ONE));
        assertThat(result, notNullValue());

        TransferableBalanceResponse transferable = liveKucoinRestClient.accountAPI().transferable("BTC", "MARGIN", null);
        assertThat(transferable, notNullValue());

        Pagination<AccountDetailResponse> accountHistory = liveKucoinRestClient.accountAPI().getAccountLedgers("BTC",
                null, null, startAt, System.currentTimeMillis(), 1, 10);
        assertThat(accountHistory, notNullValue());

        List<SubAccountBalanceResponse> subAccountBalanceResponses = liveKucoinRestClient.accountAPI().listSubAccounts();
        Optional<SubAccountBalanceResponse> henryPeach = subAccountBalanceResponses.stream()
                .filter(subAccountBalanceResponse -> subAccountBalanceResponse.getSubName().equals("nilmiao01")).findFirst();
        assertThat(henryPeach.isPresent(), Is.is(true));

        String subUserId = henryPeach.get().getSubUserId();
        SubAccountBalanceResponse subAccount = liveKucoinRestClient.accountAPI().getSubAccount(subUserId);
        assertThat(subAccount, notNullValue());

        Map<String, String> transferResult = liveKucoinRestClient.accountAPI().transferBetweenSubAndMasterV2(
                String.valueOf(System.currentTimeMillis()), "BTC", BigDecimal.valueOf(0.00000001),
                "OUT", subUserId, "MAIN", "MAIN");
        assertThat(transferResult, notNullValue());

        exception.expect(KucoinApiException.class);
        exception.expectMessage("account already exists");

        Map<String, String> create = liveKucoinRestClient.accountAPI().createAccount("KCS", "main");
        assertThat(create, notNullValue());

        UserSummaryInfoResponse userSummaryInfo = liveKucoinRestClient.accountAPI().getUserSummaryInfo();
        assertThat(userSummaryInfo, notNullValue());

        SubUserCreateResponse subUserCreateResponse = liveKucoinRestClient.accountAPI().createSubUser("TestSubUser001", "1234abcd", "Spot", "testRemark");
        assertThat(subUserCreateResponse, notNullValue());

        List<SubApiKeyResponse> subApiKeyResponses = liveKucoinRestClient.accountAPI().getSubApiKey("TestSubUser001", null);
        assertThat(subApiKeyResponses, notNullValue());

        SubApiKeyResponse subApiKeyCreateResponse = liveKucoinRestClient.accountAPI().createSubApiKey("TestSubUser001", "12345678", "remark test", null, null, null);
        assertThat(subApiKeyCreateResponse, notNullValue());

        SubApiKeyResponse subApiKeyUpdateResponse = liveKucoinRestClient.accountAPI().updateSubApiKey("TestSubUser001", "6463406d22b2b50001c22af1", "12345678", "General,Trade", "127.0.0.1", "360");
        assertThat(subApiKeyUpdateResponse, notNullValue());

        SubApiKeyResponse subApiKeyDeleteResponse = liveKucoinRestClient.accountAPI().deleteSubApiKey("TestSubUser001", "6463406d22b2b50001c22af1", "12345678");
        assertThat(subApiKeyDeleteResponse, notNullValue());

        Pagination<SubAccountBalanceResponse> subAccountPageList = liveKucoinRestClient.accountAPI().getSubAccountPageList(1, 10);
        assertThat(subAccountPageList, notNullValue());

        List<AccountBalancesResponse> transferredToHFAccountResponse = liveKucoinRestClient.accountAPI().transferToHFAccount(String.valueOf(System.currentTimeMillis()), "BTC", "main", BigDecimal.ONE);
        assertThat(transferredToHFAccountResponse, notNullValue());

        List<AccountDetailResponse> hfAccountLedgers = liveKucoinRestClient.accountAPI().getHFAccountLedgers("BTC", null, null, null, null, null, null);
        assertThat(hfAccountLedgers, notNullValue());
    }

    @Test
    public void fillAPI() throws Exception {
        Pagination<TradeResponse> fills = liveKucoinRestClient.fillAPI().listFills("KCS-USDT", null, "buy",
                null, "TRADE", startAt, endAt, 10, 10);
        assertThat(fills, notNullValue());

        List<TradeResponse> limitFillsPageList = liveKucoinRestClient.fillAPI().queryLimitFillsList();
        assertThat(limitFillsPageList, notNullValue());

        HFTradeResponse hfTradeResponse = liveKucoinRestClient.fillAPI().queryHFTrades("KCS-USDT", null, null, null, null, null, null, null);
        assertThat(hfTradeResponse, notNullValue());
    }

    @Test
    public void queryLimitOrderPageList() throws IOException {
        List<OrderResponse> limitOrderPageList = liveKucoinRestClient.orderAPI().queryLimitOrderList();
        assertThat(limitOrderPageList, notNullValue());
    }

    @Test
    public void createOrderTest() throws Exception {
        String clientOid = UUID.randomUUID().toString();
        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.0359)).size(BigDecimal.TEN).side("buy").tradeType("TRADE")
                .symbol("QRDO-USDT").type("limit").clientOid(clientOid).build();
        OrderCreateResponse order = liveKucoinRestClient.orderAPI().createOrder(request);
        System.out.println("clientOid:" + clientOid + " orderId:" + order.getOrderId());
        Thread.sleep(10000);
        liveKucoinRestClient.orderAPI().cancelOrder(order.getOrderId());
    }

    @Test
    public void createTestOrder() throws Exception {
        String clientOid = UUID.randomUUID().toString();
        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.0359)).size(BigDecimal.TEN).side("buy").tradeType("TRADE")
                .symbol("QRDO-USDT").type("limit").clientOid(clientOid).build();
        OrderCreateResponse order = liveKucoinRestClient.orderAPI().createOrderTest(request);
        System.out.println("clientOid:" + clientOid + " orderId:" + order.getOrderId());
        Thread.sleep(10000);
    }

    @Test
    public void cancelOrderTest() throws IOException {
        OrderCancelResponse orderCancelResponse = liveKucoinRestClient.orderAPI().cancelOrder("64dded91f715e60007a10c4e");
        System.out.println(orderCancelResponse);
    }

    @Test
    public void queryServerStatus() throws Exception {
        ServerStatusResponse status = liveKucoinRestClient.orderAPI().queryServerStatus();
        System.out.println(status);
    }

    @Test
    public void orderAPI() throws Exception {

        List<UserFeeResponse> userFees = liveKucoinRestClient.orderAPI().getUserTradeFees("BTC-USDT,KCS-USDT");
        assertThat(userFees, notNullValue());

        UserFeeResponse userBaseFee = liveKucoinRestClient.orderAPI().getUserBaseFee("1");
        assertThat(userBaseFee, notNullValue());

        Pagination<OrderResponse> limitOrderPageList = liveKucoinRestClient.orderAPI().queryLimitOrderPageList(10, 1);
        assertThat(limitOrderPageList, notNullValue());

        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy").tradeType("TRADE")
                .symbol("ETH-BTC").type("limit").clientOid(String.valueOf(System.currentTimeMillis())).build();
        OrderCreateResponse order = liveKucoinRestClient.orderAPI().createOrder(request);
        assertThat(order, notNullValue());

        MultiOrderCreateRequest multiOrderRequest = new MultiOrderCreateRequest();
        multiOrderRequest.setSymbol("ETH-BTC");
        OrderCreateApiRequest request2 = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy").tradeType("TRADE")
                .symbol("ETH-BTC").type("limit").clientOid(String.valueOf(System.currentTimeMillis())).build();
        request.setClientOid(String.valueOf(System.currentTimeMillis()));
        multiOrderRequest.setOrderList(Lists.newArrayList(request, request2));
        MultiOrderCreateResponse multiOrderResponse = liveKucoinRestClient.orderAPI().createMultipleOrders(multiOrderRequest);
        assertThat(multiOrderResponse, notNullValue());

        Pagination<OrderResponse> orderResponsePagination = liveKucoinRestClient.orderAPI().listOrders("ETH-BTC",
                null, null, "TRADE", "active", null, null, 10, 1);
        assertThat(orderResponsePagination, notNullValue());

        OrderResponse orderResponse = liveKucoinRestClient.orderAPI().getOrder(order.getOrderId());
        assertThat(orderResponse, notNullValue());

        ActiveOrderResponse activeOrder = liveKucoinRestClient.orderAPI().getOrderByClientOid(request.getClientOid());
        assertThat(activeOrder, notNullValue());

        OrderCancelResponse orderCancelResponse = liveKucoinRestClient.orderAPI().cancelOrder(order.getOrderId());
        assertThat(orderCancelResponse, notNullValue());

        OrderCancelResponse orderCancel = liveKucoinRestClient.orderAPI().cancelOrderByClientOid(request.getClientOid());
        assertThat(orderCancel, notNullValue());

        OrderCancelResponse ordersCancelResponse = liveKucoinRestClient.orderAPI().cancelAllOrders("ETH-BTC", "TRADE");
        assertThat(ordersCancelResponse, notNullValue());

        HFOrderCreateRequest hfOrderCreateRequest = HFOrderCreateRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy")
                .symbol("ETH-BTC").type("limit").clientOid(String.valueOf(System.currentTimeMillis())).build();
        HFOrderCreateResponse hfOrderCreateResponse = liveKucoinRestClient.orderAPI().createHFOrder(hfOrderCreateRequest);
        assertThat(hfOrderCreateResponse, notNullValue());

        hfOrderCreateResponse = liveKucoinRestClient.orderAPI().createHFOrderTest(hfOrderCreateRequest);
        assertThat(hfOrderCreateResponse, notNullValue());

        HFOrderSyncCreateResponse hfOrderSyncCreateResponse = liveKucoinRestClient.orderAPI().syncCreateHFOrder(hfOrderCreateRequest);
        assertThat(hfOrderSyncCreateResponse, notNullValue());

        HFOrderMultiCreateRequest multiCreateRequest = new HFOrderMultiCreateRequest();
        multiCreateRequest.setOrderList(Lists.newArrayList(hfOrderCreateRequest));
        List<HFOrderMultiCreateResponse> hfOrderCreateResponses = liveKucoinRestClient.orderAPI().createMultipleHFOrders(multiCreateRequest);
        assertThat(hfOrderCreateResponses, notNullValue());

        List<HFOrderSyncMultiCreateResponse> hfOrderSyncCreateResponses = liveKucoinRestClient.orderAPI().syncCreateMultipleHFOrders(multiCreateRequest);
        assertThat(hfOrderSyncCreateResponses, notNullValue());

        HFOrderAlterRequest alterRequest = new HFOrderAlterRequest();
        alterRequest.setSymbol("ETH-USDT");
        alterRequest.setClientOid("clientOid");
        alterRequest.setNewPrice("1");
        alterRequest.setNewSize("2");
        HFOrderAlterResponse hfOrderAlterResponse = liveKucoinRestClient.orderAPI().alterHFOrder(alterRequest);
        assertThat(hfOrderAlterResponse, notNullValue());

        HFOrderCancelResponse hfOrderCancelResponse = liveKucoinRestClient.orderAPI().cancelHFOrder("orderId", "ETH-USDT");
        assertThat(hfOrderCancelResponse, notNullValue());

        HFOrderSyncCancelResponse syncCancelResponse = liveKucoinRestClient.orderAPI().syncCancelHFOrder("orderId", "ETH-USDT");
        assertThat(syncCancelResponse, notNullValue());

        HFOrderCancelByClientOidResponse hfOrderCancelByClientOidResponse = liveKucoinRestClient.orderAPI().cancelHFOrderByClientOid("clientOid", "ETH-USDT");
        assertThat(hfOrderCancelByClientOidResponse, notNullValue());

        HFOrderSyncCancelResponse syncCancelHFOrderByClientOid = liveKucoinRestClient.orderAPI().syncCancelHFOrderByClientOid("clientOid", "ETH-USDT");
        assertThat(syncCancelHFOrderByClientOid, notNullValue());

        HFOrderCancelSizeResponse hfOrderCancelSizeResponse = liveKucoinRestClient.orderAPI().cancelHFOrderSize("orderId", "ETH-USDT", "1");
        assertThat(hfOrderCancelSizeResponse, notNullValue());

        String cancelHFOrdersBySymbolResponse = liveKucoinRestClient.orderAPI().cancelHFOrdersBySymbol("ETH-USDT");
        assertThat(cancelHFOrdersBySymbolResponse, notNullValue());

        List<HFOrderResponse> activeHFOrders = liveKucoinRestClient.orderAPI().getActiveHFOrders("ETH-USDT");
        assertThat(activeHFOrders, notNullValue());

        HFOrderActiveSymbolQueryResponse activeHFOrderSymbols = liveKucoinRestClient.orderAPI().getActiveHFOrderSymbols();
        assertThat(activeHFOrderSymbols, notNullValue());

        HFDoneOrderQueryResponse doneHFOrders = liveKucoinRestClient.orderAPI().getDoneHFOrders("ETH-USDT", "buy", "limit", null, null, 0L, 100);
        assertThat(doneHFOrders, notNullValue());

        HFOrderResponse hfOrder = liveKucoinRestClient.orderAPI().getHFOrder("645b6755952dc10001be52f6", "ETH-USDT");
        assertThat(hfOrder, notNullValue());

        HFOrderResponse hfOrderByClientOid = liveKucoinRestClient.orderAPI().getHFOrderByClientOid("clientOid", "ETH-USDT");
        assertThat(hfOrderByClientOid, notNullValue());

        HFOrderDeadCancelResponse hfOrderDeadCancelResponse = liveKucoinRestClient.orderAPI().deadCancelHFOrder(5, null);
        assertThat(hfOrderDeadCancelResponse, notNullValue());

        HFOrderDeadCancelQueryResponse hfOrderDeadCancelQueryResponse = liveKucoinRestClient.orderAPI().queryHFOrderDeadCancel();
        assertThat(hfOrderDeadCancelQueryResponse, notNullValue());

        HFOrderCancelAllResponse response = liveKucoinRestClient.orderAPI().cancelAllHFOrders();
        assertThat(response, notNullValue());

    }

    @Test
    public void stopOrderAPI() throws Exception {
        StopOrderCreateRequest request = StopOrderCreateRequest.builder()
                .price(BigDecimal.valueOf(0.0001)).size(BigDecimal.ONE).side("buy")
                .stop("loss").stopPrice(BigDecimal.valueOf(0.0002))
                .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
        OrderCreateResponse stopOrder = liveKucoinRestClient.stopOrderAPI().createStopOrder(request);
        assertThat(stopOrder, notNullValue());

        StopOrderResponse stopOrderResponse = liveKucoinRestClient.stopOrderAPI().getStopOrder(stopOrder.getOrderId());
        assertThat(stopOrderResponse, notNullValue());

        OrderCancelResponse orderCancelResponse = liveKucoinRestClient.stopOrderAPI().cancelStopOrder(stopOrder.getOrderId());
        assertThat(orderCancelResponse, notNullValue());

        StopOrderCancelRequest cancelRequest = new StopOrderCancelRequest();
        cancelRequest.setSymbol("ETH-BTC");
        OrderCancelResponse ordersCancelResponse = liveKucoinRestClient.stopOrderAPI().cancelStopOrders(cancelRequest);
        assertThat(ordersCancelResponse, notNullValue());

        OrderCancelResponse orderCancelByClientOidResponse = liveKucoinRestClient.stopOrderAPI().cancelStopOrderByClientOid("oid");
        assertThat(orderCancelByClientOidResponse, notNullValue());

        List<StopOrderResponse> stopOrderByOidResponse = liveKucoinRestClient.stopOrderAPI().getStopOrderByClientOid("oid", null);
        assertThat(stopOrderByOidResponse, notNullValue());

        StopOrderQueryRequest queryRequest = new StopOrderQueryRequest();
        Pagination<StopOrderResponse> stopOrderResponsePagination = liveKucoinRestClient.stopOrderAPI().queryStopOrders(queryRequest);
        assertThat(stopOrderResponsePagination, notNullValue());
    }

    @Test
    public void withdrawalAPI() throws Exception {
        Pagination<WithdrawResponse> withdrawList = liveKucoinRestClient.withdrawalAPI().getWithdrawList("KCS", "FAILURE",
                startAt, endAt, 1, 10);
        assertThat(withdrawList, notNullValue());

        Pagination<WithdrawResponse> histWithdrawPageList = liveKucoinRestClient.withdrawalAPI().getHistWithdrawPageList("KCS", null,
                startAt, endAt, 1, 10);
        assertThat(histWithdrawPageList, notNullValue());

        WithdrawQuotaResponse kcs = liveKucoinRestClient.withdrawalAPI().getWithdrawQuotas("KCS", null);
        assertThat(kcs, notNullValue());
        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot be withdrawn");
        WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder().address("123467")
                .amount(BigDecimal.valueOf(0.00000001)).currency("KCS").build();
        liveKucoinRestClient.withdrawalAPI().applyWithdraw(withdrawApplyRequest);

        liveKucoinRestClient.withdrawalAPI().cancelWithdraw("1234567");
    }

    @Test
    public void depositAPI() throws Exception {
        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        liveKucoinRestClient.depositAPI().createDepositAddress("KCS", null);

        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        liveKucoinRestClient.depositAPI().getDepositAddress("KCS", null);

        exception.expect(KucoinApiException.class);
        exception.expectMessage("Sandbox environment cannot get deposit address");
        liveKucoinRestClient.depositAPI().getDepositPageList("KCS", startAt, endAt, "SUCCESS", 1, 10);

        List<DepositAddressResponse> depositAddressResponseList = liveKucoinRestClient.depositAPI().getDepositAddresses("USDT");
        assertThat(depositAddressResponseList, notNullValue());

        Pagination<DepositResponse> histDepositPageList = liveKucoinRestClient.depositAPI().getHistDepositPageList("KCS", "SUCCESS", startAt, endAt, 1, 10);
        assertThat(histDepositPageList, notNullValue());
    }

    @Test
    public void symbolAPI() throws Exception {
        assertThat(liveKucoinRestClient.symbolAPI().getTicker("ETH-BTC"), notNullValue());

        List<SymbolResponse> symbols = liveKucoinRestClient.symbolAPI().getSymbols();
        assertThat(symbols, notNullValue());
        assertThat(symbols.size(), greaterThan(0));

        SymbolTickResponse hrStats = liveKucoinRestClient.symbolAPI().get24hrStats("ETH-BTC");
        assertThat(hrStats, notNullValue());
    }

    @Test
    public void getAllTickers() throws Exception {

        SymbolTickResponse hrStats = liveKucoinRestClient.symbolAPI().get24hrStats("GO-USDT");
        assertThat(hrStats, notNullValue());

        List<String> invalidCurrency = Arrays.asList("GO", "ITAMCUBE", "RPC", "BURP", "ETHO", "MXW", "COMB", "SOV", "ALBT",
                "CARR", "CBC", "DINO", "DPI", "EPS", "EXRD", "KIN", "MFT", "NIF", "PIVX", "PLATO", "RBS", "SRBS", "SPI", "TKY", "UMB");

        List<SymbolResponse> symbolList = liveKucoinRestClient.symbolAPI().getSymbolList(null);
        symbolList.forEach(symbolResponse -> {
            if (symbolResponse.getSymbol().equals("BTC-USDT")) {
                System.out.println(symbolResponse);
            }
        });
        assertThat(symbolList, notNullValue());
        symbolList.forEach(symbolResponse -> {
            String cur1 = symbolResponse.getSymbol().split("-")[0];
            String cur2 = symbolResponse.getSymbol().split("-")[1];
            if (invalidCurrency.contains(cur1) || invalidCurrency.contains(cur2)) {
                System.out.println(symbolResponse.getSymbol());
            }
        });

        AllTickersResponse allTickers = liveKucoinRestClient.symbolAPI().getAllTickers();
        allTickers.getTicker().forEach(marketTickerResponse -> {
            String cur1 = marketTickerResponse.getSymbol().split("-")[0];
            String cur2 = marketTickerResponse.getSymbol().split("-")[1];
            if (invalidCurrency.contains(cur1) || invalidCurrency.contains(cur2)) {
                System.out.println(marketTickerResponse.getSymbol());
            }
        });
        assertThat(allTickers, notNullValue());
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

        List<String> marketList = liveKucoinRestClient.symbolAPI().getMarketList();
        assertThat(marketList.size(), greaterThan(1));

        AllTickersResponse allTickers = liveKucoinRestClient.symbolAPI().getAllTickers();
        assertThat(allTickers, notNullValue());
        assertThat(allTickers.getTicker().size(), greaterThan(1));

        List<SymbolResponse> symbolList = liveKucoinRestClient.symbolAPI().getSymbolList("BTC");
        assertThat(symbolList, notNullValue());

        SymbolResponse symbol = liveKucoinRestClient.symbolAPI().getSymbolDetail("BTC-USDT");
        assertThat(symbol, notNullValue());
    }

    @Test
    public void orderBookAPI() throws Exception {

        OrderBookResponse fullLevel2OrderBook = liveKucoinRestClient.orderBookAPI().getAllLevel2OrderBook("STRK-BTC");
        assertThat(fullLevel2OrderBook, notNullValue());

        OrderBookResponse top20Level2OrderBook = liveKucoinRestClient.orderBookAPI().getTop20Level2OrderBook("BTC-USDT");
        assertThat(top20Level2OrderBook, notNullValue());

        OrderBookResponse top100Level2OrderBook = liveKucoinRestClient.orderBookAPI().getTop100Level2OrderBook("BTC-USDT");
        assertThat(top100Level2OrderBook, notNullValue());
    }

    @Test
    public void historyAPI() throws Exception {
        List<TradeHistoryResponse> tradeHistories = liveKucoinRestClient.historyAPI().getTradeHistories("ETH-BTC");
        assertThat(tradeHistories, notNullValue());
        // TODO broken
        // assertThat(tradeHistories.size(), greaterThan(0));

        List<List<String>> historicRates = liveKucoinRestClient.historyAPI().getHistoricRates("ETH-BTC", startAt, endAt, "1min");
        assertThat(historicRates, notNullValue());
        assertThat(historicRates.size(), greaterThan(0));
    }

    @Test
    public void currencyAPI() throws Exception {
        List<CurrencyResponse> currencies = liveKucoinRestClient.currencyAPI().getCurrencies();
        assertThat(currencies, notNullValue());
        assertThat(currencies.size(), greaterThan(0));

        CurrencyDetailResponse kcs = liveKucoinRestClient.currencyAPI().getCurrencyDetail("KCS", null);
        assertThat(kcs, notNullValue());


        CurrencyDetailV2Response kcsv2 = liveKucoinRestClient.currencyAPI().getCurrencyDetailV2("KCS", null);
        assertThat(kcsv2, notNullValue());

        Map<String, BigDecimal> fiatPrice = liveKucoinRestClient.currencyAPI().getFiatPrice("USD", "KCS, BTC");
        assertThat(fiatPrice, notNullValue());
        assertThat(fiatPrice.keySet().size(), greaterThan(1));
    }

    @Test
    public void getCurrenciesTest() throws IOException {
        List<CurrencyDetailV2Response> currencyDetailV2ResponseList = liveKucoinRestClient.currencyAPI().getCurrenciesV3();
        assertThat(currencyDetailV2ResponseList, notNullValue());
    }

    @Test
    public void timeAPI() throws Exception {
        Long serverTimeStamp = liveKucoinRestClient.timeAPI().getServerTimeStamp();
        assertThat(System.currentTimeMillis() - serverTimeStamp, lessThanOrEqualTo(5000L));
    }

    @Test
    public void commonAPI() throws Exception {
        ServiceStatusResponse serverStatus = liveKucoinRestClient.commonAPI().getServerStatus();
        assertThat(serverStatus, notNullValue());
    }

    @Test
    public void marginAPI() throws Exception {

        MarkPriceResponse markPrice = liveKucoinRestClient.marginAPI().getMarkPrice("USDT-BTC");
        assertThat(markPrice, notNullValue());

        MarginConfigResponse marginConfig = liveKucoinRestClient.marginAPI().getMarginConfig();
        assertThat(marginConfig, notNullValue());

        MarginAccountResponse marginAccount = liveKucoinRestClient.marginAPI().getMarginAccount();
        assertThat(marginAccount, notNullValue());

        MarginOrderCreateRequest request = MarginOrderCreateRequest.builder()
                .price(BigDecimal.valueOf(20)).size(new BigDecimal("0.0130")).side("sell")
                .symbol("ATOM-USDT").type("limit").clientOid(String.valueOf(System.currentTimeMillis()))
                .build();
        MarginOrderCreateResponse marginOrderResponse = liveKucoinRestClient.marginAPI().createMarginOrder(request);
        assertThat(marginOrderResponse, notNullValue());

        marginOrderResponse = liveKucoinRestClient.marginAPI().createMarginOrderTest(request);
        assertThat(marginOrderResponse, notNullValue());

        List<EtfInfoResponse> etfInfoResponseList = liveKucoinRestClient.marginAPI().getEtfInfo("BTCUP");
        assertThat(etfInfoResponseList, notNullValue());

        List<CrossMarginCurrencyResponse> marginCurrencies = liveKucoinRestClient.marginAPI().getMarginCurrencies("", "XEM");
        assertThat(marginCurrencies, notNullValue());

        MarginAccountResponse marginAccountResponse = liveKucoinRestClient.marginAPI().getMarginAccounts("BTC", "");
        assertThat(marginAccountResponse, notNullValue());

    }

    @Test
    public void loanAPI() throws Exception {

        BorrowV3Request borrowRequest = BorrowV3Request.builder()
                .currency("BOME").timeInForce(TimeInForceEnum.IOC.name()).size(BigDecimal.valueOf(100))
                .build();
        BorrowV3Response borrow = liveKucoinRestClient.loanAPI().borrowV3(borrowRequest);
        assertThat(borrow, notNullValue());

        BorrowQueryV3Request borrowQueryV3Request = BorrowQueryV3Request.builder().currency("BOME").pageSize(20).isIsolated(true).build();
        Pagination<BorrowQueryV3Response> borrowQuery = liveKucoinRestClient.loanAPI().queryBorrowV3(borrowQueryV3Request);
        assertThat(borrowQuery, notNullValue());

        RepayV3Request repayV3Request = RepayV3Request.builder()
                .currency("BOME").size(BigDecimal.valueOf(100))
                .build();
        RepayV3Response repayV3Response = liveKucoinRestClient.loanAPI().repayV3(repayV3Request);
        assertThat(repayV3Response, notNullValue());

        RepayQueryV3Request repayQueryV3Request = RepayQueryV3Request.builder().currency("BOME").pageSize(20).build();
        Pagination<RepayQueryV3Response> repayQueryV3Response = liveKucoinRestClient.loanAPI().queryRepayV3(repayQueryV3Request);
        assertThat(repayQueryV3Response, notNullValue());

        InterestQueryV3Request interestQueryV3Request = InterestQueryV3Request.builder().currency("BOME").pageSize(20).build();
        Pagination<InterestQueryV3Response> interestQueryV3ResponsePagination = liveKucoinRestClient.loanAPI().queryInterestV3(interestQueryV3Request);
        assertThat(interestQueryV3ResponsePagination, notNullValue());

        List<MarginProjectListResponse> marginProjectListResponseList = liveKucoinRestClient.loanAPI().getProjectList("BTC");
        assertThat(marginProjectListResponseList, notNullValue());

        List<MarginMarketInterestRateResponse> marketInterestRate = liveKucoinRestClient.loanAPI().getMarketInterestRate("BOME");
        assertThat(marketInterestRate, notNullValue());

        PurchaseRequest purchaseRequest = PurchaseRequest.builder().currency("BOME").interestRate(marketInterestRate.get(0).getMarketInterestRate()).size(new BigDecimal("200")).build();
        PurchaseResponse purchaseResponse = liveKucoinRestClient.loanAPI().purchase(purchaseRequest);
        assertThat(purchaseResponse, notNullValue());

        PurchaseQueryRequest purchaseQueryRequest = PurchaseQueryRequest.builder().currency("BOME").currentPage(1).pageSize(20).status(LendingStatusEnum.PENDING).build();
        Pagination<PurchaseQueryResponse> purchaseQueryResponsePagination = liveKucoinRestClient.loanAPI().queryPurchase(purchaseQueryRequest);
        assertThat(purchaseQueryResponsePagination, notNullValue());

        String purchaseOrderNo = purchaseQueryResponsePagination.getItems().get(0).getPurchaseOrderNo();

        UpdatePurchaseRequest updatePurchaseRequest = UpdatePurchaseRequest.builder().currency("BOME").purchaseOrderNo(purchaseOrderNo).interestRate(marketInterestRate.get(1).getMarketInterestRate()).build();
        liveKucoinRestClient.loanAPI().updatePurchase(updatePurchaseRequest);

        RedeemRequest redeemRequest = RedeemRequest.builder().currency("BOME").purchaseOrderNo(purchaseOrderNo).size(new BigDecimal(200)).build();
        RedeemResponse redeemResponse = liveKucoinRestClient.loanAPI().redeem(redeemRequest);
        assertThat(redeemResponse, notNullValue());

        RedeemQueryRequest redeemQueryRequest = RedeemQueryRequest.builder().currency("BOME").status(LendingStatusEnum.DONE).build();
        Pagination<RedeemQueryResponse> redeemQueryResponsePagination = liveKucoinRestClient.loanAPI().queryRedeem(redeemQueryRequest);
        assertThat(redeemQueryResponsePagination, notNullValue());

    }

    @Test
    public void IsolatedAPI() throws Exception {
        List<IsolatedSymbolResponse> isolatedSymbolResponses = liveKucoinRestClient.isolatedAPI().getSymbols();
        assertThat(isolatedSymbolResponses, notNullValue());

        IsolatedAccountResponse isolatedAccountResponse = liveKucoinRestClient.isolatedAPI().getAccounts("USDT");
        assertThat(isolatedAccountResponse, notNullValue());

        IsolatedAssetResponse isolatedAssetResponse = liveKucoinRestClient.isolatedAPI().getAccount("BTC-USDT");
        assertThat(isolatedAssetResponse, notNullValue());

        List<IsolatedMarginCurrencyResponse> isolatedMarginCurrencyResponseList = liveKucoinRestClient.isolatedAPI().getIsolatedCurrencies("NKN-USDT");
        assertThat(isolatedMarginCurrencyResponseList, notNullValue());

        IsolatedAccountV3Response isolatedAccountsV3 = liveKucoinRestClient.isolatedAPI().getIsolatedAccountsV3("NKN-USDT", null , null);
        assertThat(isolatedAccountsV3, notNullValue());

    }

    @Test
    public void ocoOrderAPI() throws Exception {
        OrderCreateResponse ocoResp = liveKucoinRestClient.ocoOrderAPI().createOcoOrder(
                OcoOrderCreateRequest
                        .builder()
                        .symbol("BTC-USDT")
                        .side("buy")
                        .price(new BigDecimal("40000"))
                        .size(new BigDecimal("0.00001"))
                        .limitPrice(new BigDecimal("40000"))
                        .stopPrice(new BigDecimal("43500"))
                        .clientOid(UUID.randomUUID().toString())
                        .build());
        assertThat(ocoResp, notNullValue());
        OcoOrderResponse ocoOrder = liveKucoinRestClient.ocoOrderAPI().getOcoOrder(ocoResp.getOrderId());
        assertThat(ocoOrder, notNullValue());
        OrderCancelResponse orderCancelResponse = liveKucoinRestClient.ocoOrderAPI().cancelOcoOrder(ocoOrder.getOrderId());
        assertThat(orderCancelResponse, notNullValue());
        ocoResp = liveKucoinRestClient.ocoOrderAPI().createOcoOrder(
                OcoOrderCreateRequest
                        .builder()
                        .symbol("BTC-USDT")
                        .side("sell")
                        .price(new BigDecimal("43500"))
                        .size(new BigDecimal("0.00001"))
                        .limitPrice(new BigDecimal("40000"))
                        .stopPrice(new BigDecimal("40000"))
                        .clientOid(UUID.randomUUID().toString())
                        .build());
        assertThat(ocoResp, notNullValue());
        OcoOrderDetailResponse ocoOrderDetails = liveKucoinRestClient.ocoOrderAPI().getOcoOrderDetails(ocoResp.getOrderId());
        assertThat(ocoOrderDetails, notNullValue());
        OcoOrderResponse ocoOrderByClientOid = liveKucoinRestClient.ocoOrderAPI().getOcoOrderByClientOid(ocoOrderDetails.getClientOid());
        assertThat(ocoOrderByClientOid, notNullValue());
        orderCancelResponse = liveKucoinRestClient.ocoOrderAPI().cancelOcoOrderByClientOid(ocoOrderDetails.getClientOid());
        assertThat(orderCancelResponse, notNullValue());

        ocoResp = liveKucoinRestClient.ocoOrderAPI().createOcoOrder(
                OcoOrderCreateRequest
                        .builder()
                        .symbol("BTC-USDT")
                        .side("buy")
                        .price(new BigDecimal("40000"))
                        .size(new BigDecimal("0.00001"))
                        .limitPrice(new BigDecimal("40000"))
                        .stopPrice(new BigDecimal("43500"))
                        .clientOid(UUID.randomUUID().toString())
                        .build());
        ocoResp = liveKucoinRestClient.ocoOrderAPI().createOcoOrder(
                OcoOrderCreateRequest
                        .builder()
                        .symbol("BTC-USDT")
                        .side("sell")
                        .price(new BigDecimal("43500"))
                        .size(new BigDecimal("0.00001"))
                        .limitPrice(new BigDecimal("40000"))
                        .stopPrice(new BigDecimal("40000"))
                        .clientOid(UUID.randomUUID().toString())
                        .build());
        OcoOrderCancelRequest cancelRequest = new OcoOrderCancelRequest();
        cancelRequest.setSymbol("BTC-USDT");
        orderCancelResponse = liveKucoinRestClient.ocoOrderAPI().cancelOcoOrders(cancelRequest);
        assertThat(orderCancelResponse, notNullValue());
        List<String> ocoIdList = new ArrayList<>();
        ocoResp = liveKucoinRestClient.ocoOrderAPI().createOcoOrder(
                OcoOrderCreateRequest
                        .builder()
                        .symbol("BTC-USDT")
                        .side("buy")
                        .price(new BigDecimal("40000"))
                        .size(new BigDecimal("0.00001"))
                        .limitPrice(new BigDecimal("40000"))
                        .stopPrice(new BigDecimal("43500"))
                        .clientOid(UUID.randomUUID().toString())
                        .build());
        ocoIdList.add(ocoResp.getOrderId());
        ocoResp = liveKucoinRestClient.ocoOrderAPI().createOcoOrder(
                OcoOrderCreateRequest
                        .builder()
                        .symbol("BTC-USDT")
                        .side("sell")
                        .price(new BigDecimal("43500"))
                        .size(new BigDecimal("0.00001"))
                        .limitPrice(new BigDecimal("40000"))
                        .stopPrice(new BigDecimal("40000"))
                        .clientOid(UUID.randomUUID().toString())
                        .build());
        ocoIdList.add(ocoResp.getOrderId());
        cancelRequest = new OcoOrderCancelRequest();
        cancelRequest.setOrderIds(ocoIdList);
        orderCancelResponse = liveKucoinRestClient.ocoOrderAPI().cancelOcoOrders(cancelRequest);
        assertThat(orderCancelResponse, notNullValue());

    }

    @Test
    public void earnAPI() throws Exception {
        List<EarnSavingProductResponse> bomeProduct = liveKucoinRestClient.earnAPI().getSavingProducts("BOME");
        assertThat(bomeProduct, notNullValue());

        EarnOrderResponse order = liveKucoinRestClient.earnAPI().createOrder(EarnOrderRequest.builder().productId(bomeProduct.get(0).getId()).amount("1000").accountType(AccountTypeEnum.MAIN.name()).build());
        System.out.println("orderId:" + order.getOrderId());
        assertThat(order, notNullValue());

        Pagination<EarnHoldAssetResponse> bome = liveKucoinRestClient.earnAPI().getHoldAssets(EarnHoldAssetQueryRequest.builder().currency("BOME").build());
        assertThat(bome, notNullValue());

        EarnHoldAssetResponse holdBomeAsset = bome.getItems().get(0);
        EarnRedeemPreviewResponse redeemPreview = liveKucoinRestClient.earnAPI().getRedeemPreview(EarnRedeemPreviewRequest.builder().orderId(holdBomeAsset.getOrderId()).build());
        assertThat(redeemPreview, notNullValue());

        EarnOrderRedemptionResponse earnOrderRedemptionResponse = liveKucoinRestClient.earnAPI().redemptionOrder(EarnOrderRedemptionRequest.builder().orderId(holdBomeAsset.getOrderId()).amount("1000").confirmPunishRedeem("1").build());
        assertThat(earnOrderRedemptionResponse, notNullValue());

        List<EarnPromotionProductResponse> bnbStaking = liveKucoinRestClient.earnAPI().getPromotionProducts("BNB");
        assertThat(bnbStaking, notNullValue());

        List<EarnStakingProductResponse> lunaStaking = liveKucoinRestClient.earnAPI().getStakingProducts("LUNA");
        assertThat(lunaStaking, notNullValue());

        List<EarnEthStakingProductResponse> ethStaking = liveKucoinRestClient.earnAPI().getEthStakingProducts();
        assertThat(ethStaking, notNullValue());

        List<EarnKcsStakingProductResponse> kcsStaking = liveKucoinRestClient.earnAPI().getKcsStakingProducts("KCS");
        assertThat(kcsStaking, notNullValue());

    }

}