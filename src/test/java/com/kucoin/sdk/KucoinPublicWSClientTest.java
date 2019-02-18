/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kucoin.sdk.model.enums.PublicChannelEnum;

/**
 * Created by chenshiwei on 2019/1/22.
 */
public class KucoinPublicWSClientTest {

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    {
        OBJECTMAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static KucoinPublicWSClient kucoinPublicWSClient;

    private static PipedInputStream pipedInputStream;

    private static PipedOutputStream pipedOutputStream;

    @BeforeClass
    public static void setupClass() throws Exception {
        kucoinPublicWSClient = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
                .buildPublicWSClient();
        pipedInputStream = new PipedInputStream();
        pipedOutputStream = new PipedOutputStream();
        pipedOutputStream.connect(pipedInputStream);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        kucoinPublicWSClient.close();
        pipedOutputStream.close();
        pipedInputStream.close();
    }

    @Test
    public void onTicker() throws Exception {
        kucoinPublicWSClient.onTicker(response -> {
            if (response.getData() != null) {
                try {
                    pipedOutputStream.write(OBJECTMAPPER.writeValueAsBytes(response.getData()));
                    pipedOutputStream.flush();
                } catch (Exception e) {

                }
            }
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.TICKER, "ETH-BTC", "KCS-BTC");
        }, "ETH-BTC", "KCS-BTC");
        byte[] bytes = new byte[1024];
        pipedInputStream.read(bytes);
        assertThat(bytes.length, Matchers.greaterThan(0));
    }

    @Test
    public void onLevel2Data() throws Exception {
        kucoinPublicWSClient.onLevel2Data(response -> {
            if (response.getData() != null) {
                try {
                    pipedOutputStream.write(OBJECTMAPPER.writeValueAsBytes(response.getData()));
                    pipedOutputStream.flush();
                } catch (Exception e) {

                }
            }
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL2, "ETH-BTC", "KCS-BTC");
        }, "ETH-BTC", "KCS-BTC");
        byte[] bytes = new byte[1024];
        pipedInputStream.read(bytes);
        assertThat(bytes.length, Matchers.greaterThan(0));
    }

    @Test
    public void onMatchExecutionData() throws Exception {
        kucoinPublicWSClient.onMatchExecutionData(response -> {
            if (response.getData() != null) {
                try {
                    pipedOutputStream.write(OBJECTMAPPER.writeValueAsBytes(response.getData()));
                    pipedOutputStream.flush();
                } catch (Exception e) {

                }
            }
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.MATCH, "ETH-BTC", "KCS-BTC");
        }, "ETH-BTC", "KCS-BTC");
        byte[] bytes = new byte[1024];
        pipedInputStream.read(bytes);
        assertThat(bytes.length, Matchers.greaterThan(0));
    }

    @Test
    public void onLevel3Data() throws Exception {
        kucoinPublicWSClient.onLevel3Data(response -> {
            if (response.getData() != null) {
                try {
                    pipedOutputStream.write(OBJECTMAPPER.writeValueAsBytes(response.getData()));
                    pipedOutputStream.flush();
                } catch (Exception e) {

                }
            }
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL3, "ETH-BTC", "KCS-BTC");
        }, "ETH-BTC", "KCS-BTC");
        byte[] bytes = new byte[1024];
        pipedInputStream.read(bytes);
        assertThat(bytes.length, Matchers.greaterThan(0));
    }

    @Test
    public void ping() throws Exception {
        String requestId = "1234567890";
        String ping = kucoinPublicWSClient.ping(requestId);
        assertThat(ping, Is.is(requestId));
    }

}