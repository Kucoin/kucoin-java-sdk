/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kucoin.sdk.model.enums.PrivateChannelEnum;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.response.AccountBalancesResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.websocket.event.AccountChangeEvent;
import com.kucoin.sdk.websocket.event.OrderActivateEvent;

/**
 * Created by chenshiwei on 2019/1/23.
 *
 * Run with -Dorg.slf4j.simpleLogger.defaultLogLevel=debug for debug logging
 */
public class KucoinPrivateWSClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(KucoinPrivateWSClientTest.class);

    private static KucoinRestClient kucoinRestClient;
    private static KucoinPrivateWSClient kucoinPrivateWSClient;

    @BeforeClass
    public static void setupClass() throws Exception {
        KucoinClientBuilder builder = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
                .withApiKey("5c42a37bef83c73aa68e43c4", "7df80b16-1b95-4739-9b03-3d987599c332", "asd123456");
        kucoinRestClient = builder.buildRestClient();
        kucoinPrivateWSClient = builder.buildPrivateWSClient();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        kucoinPrivateWSClient.close();
    }

    @Test
    @Ignore // TODO broken
    public void onOrderActivate() throws Exception {
        AtomicReference<OrderActivateEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPrivateWSClient.onOrderActivate(response -> {
            LOGGER.info("Got response");
            event.set(response.getData());
            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ORDER, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");

        Thread.sleep(1000);

        new Thread(() -> {
            while (event.get() == null) {
                placeOrderAndCancelOrder();
            }
        }).start();

        LOGGER.info("Waiting...");
        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    @Ignore // TODO broken
    public void onAccountBalance() throws Exception {
        AtomicReference<AccountChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPrivateWSClient.onAccountBalance(response -> {
            LOGGER.info("Got response");
            event.set(response.getData());
            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ACCOUNT);
            gotEvent.countDown();
        });

        Thread.sleep(1000);

        new Thread(() -> {
            while (event.get() == null) {
                innerTransfer();
            }
        }).start();

        LOGGER.info("Waiting...");
        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void ping() throws Exception {
        String requestId = "1234567890";
        String ping = kucoinPrivateWSClient.ping(requestId);
        assertThat(ping, Is.is(requestId));
    }

    private void placeOrderAndCancelOrder() {
        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy")
                .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
        OrderCreateResponse order = kucoinRestClient.orderAPI().createOrder(request);
        kucoinRestClient.orderAPI().cancelOrder(order.getOrderId());
    }

    private void innerTransfer() {
        List<AccountBalancesResponse> accountBalancesResponses = kucoinRestClient.accountAPI().listAccounts("BTC", null);
        assertThat(accountBalancesResponses.size(), Is.is(2));
        Optional<AccountBalancesResponse> main = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("main")).findFirst();
        Optional<AccountBalancesResponse> trade = accountBalancesResponses.stream()
                .filter(accountBalancesResponse -> accountBalancesResponse.getType().equals("trade")).findFirst();
        String mainAccountId = main.get().getId();
        String tradeAccountId = trade.get().getId();
        kucoinRestClient.accountAPI().innerTransfer(String.valueOf(System.currentTimeMillis()), mainAccountId, BigDecimal.valueOf(0.00000001), tradeAccountId);
        kucoinRestClient.accountAPI().innerTransfer(String.valueOf(System.currentTimeMillis()), tradeAccountId, BigDecimal.valueOf(0.00000001), mainAccountId);
    }

}