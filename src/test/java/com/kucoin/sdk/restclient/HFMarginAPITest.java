package com.kucoin.sdk.restclient;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import com.kucoin.sdk.rest.request.HFMarginOrderCreateRequest;
import com.kucoin.sdk.rest.response.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Margin API Test
 *
 * @author Colt Han
 * @since 2024/7/24
 */
public class HFMarginAPITest {

    private static KucoinRestClient liveKucoinRestClient;

    private static Long startAt;
    private static Long endAt;

    @BeforeClass
    public static void setUpClass() {
        liveKucoinRestClient = new KucoinClientBuilder().withBaseUrl("https://openapi-v2.kucoin.com")
                .withApiKey("", "", "")
                // Version number of api-key
                .withApiKeyVersion(ApiKeyVersionEnum.V2.getVersion())
                .buildRestClient();

        startAt = LocalDateTime.of(2024, 8, 1, 0, 0, 0).toInstant(ZoneOffset.UTC).toEpochMilli();
        endAt = LocalDateTime.of(2024, 8, 8, 0, 0, 0).toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    @Test
    public void MarginAPITest_getHFMarginOrderActiveSymbols_normal_01() throws Exception {
        String tradeType = "MARGIN_TRADE";
        HfMarginOrderActiveSymbolsResponse response = liveKucoinRestClient.HFMarginAPI().getHFMarginOrderActiveSymbols(tradeType);
        System.out.println(response);
        assertNotNull("response is null", response);
    }

    @Test
    public void MarginAPITest_getHFMarginOrderActiveSymbols_normal_02() throws Exception {
        String tradeType = "MARGIN_ISOLATED_TRADE";
        HfMarginOrderActiveSymbolsResponse response = liveKucoinRestClient.HFMarginAPI().getHFMarginOrderActiveSymbols(tradeType);
        System.out.println(response);
        assertNotNull("response is null", response);
    }

    @Test
    public void MarginAPITest_HFMarginOrder_normal_01() throws Exception {
        String clientOid = UUID.randomUUID().toString();
        String symbol = "KCS-USDT";

        HFMarginOrderCreateRequest request = HFMarginOrderCreateRequest.builder()
                .side("buy")
                .symbol(symbol)
                .type("limit")
                .size("1")
                .stp("CN")
                .price("0.1")
                .clientOid(clientOid)
                .build();
        HFMarginOrderCreateResponse createResponse = liveKucoinRestClient.HFMarginAPI().createHFMarginOrder(request);
        System.out.println(createResponse);
        assertNotNull("createResponse is null", createResponse);
        assertNotNull("createResponse.getOrderId() is null", createResponse.getOrderId());

        String tradeType = "MARGIN_TRADE";
        HfMarginOrderActiveSymbolsResponse response = liveKucoinRestClient.HFMarginAPI().getHFMarginOrderActiveSymbols(tradeType);
        System.out.println(response);
        assertNotNull("response is null", response);

        HFMarginOrderCancelByClientOidResponse cancelResponse = liveKucoinRestClient.HFMarginAPI().cancelHFMarginOrderByClientOid(clientOid, symbol);
        System.out.println(cancelResponse);
        assertNotNull("cancelResponse is null", cancelResponse);

        tradeType = "MARGIN_TRADE";
        response = liveKucoinRestClient.HFMarginAPI().getHFMarginOrderActiveSymbols(tradeType);
        System.out.println(response);
        assertNotNull("response is null", response);
    }

    @Test
    public void MarginAPITest_HFMarginOrder_normal_02() throws Exception {
        String clientOid = UUID.randomUUID().toString();
        String symbol = "KCS-USDT";

        HFMarginOrderCreateRequest request = HFMarginOrderCreateRequest.builder()
                .side("buy")
                .symbol(symbol)
                .type("limit")
                .size("1")
                .stp("CN")
                .price("0.1")
                .clientOid(clientOid)
                .build();
        HFMarginOrderCreateResponse createResponse = liveKucoinRestClient.HFMarginAPI().createHFMarginOrder(request);
        System.out.println(createResponse);
        assertNotNull("createResponse is null", createResponse);
        assertNotNull("createResponse.getOrderId() is null", createResponse.getOrderId());
        String orderId = createResponse.getOrderId();

        String tradeType = "MARGIN_TRADE";
        HfMarginOrderActiveSymbolsResponse response = liveKucoinRestClient.HFMarginAPI().getHFMarginOrderActiveSymbols(tradeType);
        System.out.println(response);
        assertNotNull("response is null", response);

        HFMarginOrderCancelByOrderIdResponse cancelResponse = liveKucoinRestClient.HFMarginAPI().cancelHFMarginOrderByOrderId(orderId, symbol);
        System.out.println(cancelResponse);
        assertNotNull("cancelResponse is null", cancelResponse);

        tradeType = "MARGIN_TRADE";
        response = liveKucoinRestClient.HFMarginAPI().getHFMarginOrderActiveSymbols(tradeType);
        System.out.println(response);
        assertNotNull("response is null", response);
    }

    @Test
    public void MarginAPITest_cancelAllHFMarginOrdersBySymbol_normal_01() throws Exception {
        String tradeType = "MARGIN_TRADE";
        String symbol = "PEPE-USDT";

        String cancelResponse = liveKucoinRestClient.HFMarginAPI().cancelAllHFMarginOrdersBySymbol(symbol, tradeType);
        System.out.println(cancelResponse);
        assertNotNull("cancelResponse is null", cancelResponse);
        assertEquals("success", cancelResponse);

        tradeType = "MARGIN_TRADE";
        HfMarginOrderActiveSymbolsResponse response = liveKucoinRestClient.HFMarginAPI().getHFMarginOrderActiveSymbols(tradeType);
        System.out.println(response);
        assertNotNull("response is null", response);

    }

    @Test
    public void MarginAPITest_getHFMarginActiveOrders_normal_01() throws Exception {
        String tradeType = "MARGIN_TRADE";
        String symbol = "KCS-USDT";

        List<HFMarginOrderResponse> orderResponse = liveKucoinRestClient.HFMarginAPI().getHFMarginActiveOrders(tradeType, symbol);
        System.out.println(orderResponse);
        assertNull(orderResponse);

        String clientOid = UUID.randomUUID().toString();
        HFMarginOrderCreateRequest request = HFMarginOrderCreateRequest.builder()
                .side("buy")
                .symbol(symbol)
                .type("limit")
                .size("1")
                .stp("CN")
                .price("0.1")
                .clientOid(clientOid)
                .build();
        HFMarginOrderCreateResponse createResponse = liveKucoinRestClient.HFMarginAPI().createHFMarginOrder(request);
        System.out.println(createResponse);
        assertNotNull("createResponse is null", createResponse);
        assertNotNull("createResponse.getOrderId() is null", createResponse.getOrderId());

        orderResponse = liveKucoinRestClient.HFMarginAPI().getHFMarginActiveOrders(tradeType, symbol);
        System.out.println(orderResponse);
        assertNotNull("orderResponse is null", orderResponse);
        assertEquals(1, orderResponse.size());

        String cancelResponse = liveKucoinRestClient.HFMarginAPI().cancelAllHFMarginOrdersBySymbol(symbol, tradeType);
        System.out.println(cancelResponse);
        assertNotNull("cancelResponse is null", cancelResponse);
        assertEquals("success", cancelResponse);

        orderResponse = liveKucoinRestClient.HFMarginAPI().getHFMarginActiveOrders(tradeType, symbol);
        System.out.println(orderResponse);
        assertNull(orderResponse);
    }

    @Test
    public void MarginAPITest_getHFMarginDoneOrders_normal_01() throws Exception {
        String tradeType = "MARGIN_TRADE";
        String symbol = "KCS-USDT";

        HFMarginOrderListResponse orderResponse = liveKucoinRestClient.HFMarginAPI().getHFMarginDoneOrders(
                symbol, tradeType, "buy", "limit", startAt, endAt, null, 5);
        System.out.println(orderResponse);
        assertNotNull(orderResponse);
        assertEquals(5, orderResponse.getItems().size());

        String orderId = orderResponse.getItems().get(0).getId();
        HFMarginOrderResponse orderByOrderIdResponse = liveKucoinRestClient.HFMarginAPI().getHFMarginOrderByOrderId(orderId, symbol);
        assertNotNull(orderByOrderIdResponse);
        assertEquals(orderId, orderByOrderIdResponse.getId());

        String clientOid = orderResponse.getItems().get(0).getClientOid();
        HFMarginOrderResponse orderByClientOidResponse = liveKucoinRestClient.HFMarginAPI().getHFMarginOrderByClientOid(clientOid, symbol);
        assertNotNull(orderByClientOidResponse);
        assertEquals(clientOid, orderByClientOidResponse.getClientOid());
    }

    @Test
    public void MarginAPITest_getHFMarginFills_normal_01() throws Exception {
        String tradeType = "MARGIN_TRADE";
        String symbol = "KCS-USDT";

        HFMarginFillsResponse fillsResponse = liveKucoinRestClient.HFMarginAPI().getHFMarginFills(
                null, symbol, tradeType, "buy", "limit", startAt, endAt, null, 5);
        System.out.println(fillsResponse);
        assertNotNull(fillsResponse);
    }
}
