package com.kucoin.sdk.restclient;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import com.kucoin.sdk.rest.response.CurrencyDetailV2Response;
import com.kucoin.sdk.rest.response.CurrencyDetailV3Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
public class CurrencyAPITest {

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
    public void test_getCurrenciesV3_normal_01() throws IOException {
        List<CurrencyDetailV2Response> currencies = liveKucoinRestClient.currencyAPI().getCurrenciesV3();
        assertNotNull(currencies);
        assertFalse(currencies.isEmpty());
        assertTrue(currencies.stream().anyMatch(i -> i.getCurrency().equals("USDT")));
    }

    @Test
    public void test_getCurrencyDetailV3_normal_01() throws IOException {
        String currency = "USDT";
        String chain = "eth";
        CurrencyDetailV3Response response = liveKucoinRestClient.currencyAPI().getCurrencyDetailV3(currency, chain);
        assertNotNull(response);
        assertNotNull(response.getChains());
        assertFalse(response.getChains().isEmpty());
    }
}
