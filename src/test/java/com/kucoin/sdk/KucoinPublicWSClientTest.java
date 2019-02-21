/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.kucoin.sdk.model.enums.PublicChannelEnum;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.TickerResponse;
import com.kucoin.sdk.websocket.event.Level2ChangeEvent;
import com.kucoin.sdk.websocket.event.Level3ChangeEvent;
import com.kucoin.sdk.websocket.event.MatchExcutionChangeEvent;

/**
 * Created by chenshiwei on 2019/1/22.
 *
 * Run with -Dorg.slf4j.simpleLogger.defaultLogLevel=debug for debug logging
 */
public class KucoinPublicWSClientTest {

    private static KucoinPublicWSClient kucoinPublicWSClient;
    private static KucoinRestClient kucoinPrivateWSClient;

    @BeforeClass
    public static void setupClass() throws Exception {
        kucoinPublicWSClient = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
            .buildPublicWSClient();
        kucoinPrivateWSClient = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
            .withApiKey("5c42a37bef83c73aa68e43c4", "7df80b16-1b95-4739-9b03-3d987599c332", "asd123456")
            .buildRestClient();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        kucoinPublicWSClient.close();
    }

    @Test
    @Ignore
    public void onTicker() throws Exception {
        AtomicReference<TickerResponse> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onTicker(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.TICKER, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");

        // Make some actual executions
        buyAndSell();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    @Ignore // TODO broken
    public void onLevel2Data() throws Exception {
        AtomicReference<Level2ChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onLevel2Data(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL2, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");

        // Trigger a market change
        placeOrderAndCancelOrder();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    @Ignore
    public void onMatchExecutionData() throws Exception {
        AtomicReference<MatchExcutionChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onMatchExecutionData(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.MATCH, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");

        // Make some actual executions
        buyAndSell();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    @Ignore // TODO broken
    public void onLevel3Data() throws Exception {
      AtomicReference<Level3ChangeEvent> event = new AtomicReference<>();
      CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onLevel3Data(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL3, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");

        // Trigger a market change
        placeOrderAndCancelOrder();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void ping() throws Exception {
        String requestId = "1234567890";
        String ping = kucoinPublicWSClient.ping(requestId);
        assertThat(ping, Is.is(requestId));
    }

    private void placeOrderAndCancelOrder() throws InterruptedException {
      Thread.sleep(1000);
      OrderCreateApiRequest request = OrderCreateApiRequest.builder()
              .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy")
              .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
      OrderCreateResponse order = kucoinPrivateWSClient.orderAPI().createOrder(request);
      kucoinPrivateWSClient.orderAPI().cancelOrder(order.getOrderId());
    }

    private void buyAndSell() throws InterruptedException {
      Thread.sleep(1000);
      OrderCreateApiRequest request1 = OrderCreateApiRequest.builder()
              .size(new BigDecimal("0.001"))
              .side("buy")
              .symbol("ETH-BTC")
              .type("market")
              .clientOid(UUID.randomUUID().toString())
              .build();
      kucoinPrivateWSClient.orderAPI().createOrder(request1);
      OrderCreateApiRequest request2 = OrderCreateApiRequest.builder()
              .size(new BigDecimal("0.001"))
              .side("sell")
              .symbol("ETH-BTC")
              .type("market")
              .clientOid(UUID.randomUUID().toString())
              .build();
      kucoinPrivateWSClient.orderAPI().createOrder(request2);
    }
}