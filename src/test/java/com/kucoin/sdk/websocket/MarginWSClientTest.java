package com.kucoin.sdk.websocket;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinPrivateWSClient;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
public class MarginWSClientTest {

    private static KucoinPrivateWSClient kucoinPrivateWSClient;

    @BeforeClass
    public static void setupClass() throws Exception {
        KucoinClientBuilder builder = new KucoinClientBuilder().withBaseUrl("https://openapi-v2.kucoin.com")
                .withApiKey("", "", "")
                // Version number of api-key
                .withApiKeyVersion(ApiKeyVersionEnum.V2.getVersion());

        kucoinPrivateWSClient = builder.buildPrivateWSClient();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        kucoinPrivateWSClient.close();
    }
}
