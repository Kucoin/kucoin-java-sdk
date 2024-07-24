package com.kucoin.sdk.restclient;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import com.kucoin.sdk.rest.request.UserLeverageUpdateRequest;
import com.kucoin.sdk.rest.response.MarginSymbolsResponse;
import com.kucoin.sdk.rest.response.SymbolV3Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
public class MarginAPITest {

    private static KucoinRestClient liveKucoinRestClient;

    @BeforeClass
    public static void setUpClass() {
        liveKucoinRestClient = new KucoinClientBuilder().withBaseUrl("https://openapi-v2.kucoin.com")
                .withApiKey("", "", "")
                // Version number of api-key
                .withApiKeyVersion(ApiKeyVersionEnum.V2.getVersion())
                .buildRestClient();
    }

    @Test
    public void MarginAPITest_getMarginSymbols_normal_01() throws Exception {
        String symbol = "ATOM-USDT";
        MarginSymbolsResponse response = liveKucoinRestClient.marginAPI().getMarginSymbols(symbol);
        assertNotNull("response is null", response);
        assertEquals(symbol, response.getItems().stream().map(SymbolV3Response::getSymbol).findFirst().orElse(null));
    }

    @Test
    public void MarginAPITest_getMarginSymbols_normal_02() throws Exception {
        String symbol = null;
        MarginSymbolsResponse response = liveKucoinRestClient.marginAPI().getMarginSymbols(symbol);
        assertNotNull("response is null", response);
        assertFalse("response.getItems() is empty.", response.getItems().isEmpty());
    }

    @Test
    public void MarginAPITest_updateUserLeverage_normal_01() throws Exception {
        UserLeverageUpdateRequest request = UserLeverageUpdateRequest.builder()
                .symbol("ATOM-USDT")
                .leverage("1.1")
                .isIsolated(true)
                .build();
        Void response = liveKucoinRestClient.marginAPI().updateUserLeverage(request);
        assertNull(response);
    }
}
