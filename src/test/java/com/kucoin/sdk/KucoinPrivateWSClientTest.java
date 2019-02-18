/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kucoin.sdk.model.enums.PrivateChannelEnum;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.response.AccountBalancesResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;

/**
 * Created by chenshiwei on 2019/1/23.
 */
public class KucoinPrivateWSClientTest {

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    {
        OBJECTMAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static KucoinRestClient kucoinRestClient;

    private static KucoinPrivateWSClient kucoinPrivateWSClient;

    private static PipedInputStream pipedInputStream;

    private static PipedOutputStream pipedOutputStream;

    private static volatile boolean placeOrderSwitch = true;

    private static volatile boolean innerTransferSwitch = true;

    @BeforeClass
    public static void setupClass() throws Exception {
        KucoinClientBuilder builder = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
                .withApiKey("5c42a37bef83c73aa68e43c4", "7df80b16-1b95-4739-9b03-3d987599c332", "asd123456");
        kucoinRestClient = builder.buildRestClient();
        kucoinPrivateWSClient = builder.buildPrivateWSClient();
        pipedInputStream = new PipedInputStream();
        pipedOutputStream = new PipedOutputStream();
        pipedOutputStream.connect(pipedInputStream);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        kucoinPrivateWSClient.close();
        pipedOutputStream.close();
        pipedInputStream.close();
    }

    @Test
    public void onOrderActivate() throws Exception {
        kucoinPrivateWSClient.onOrderActivate(response -> {
            if (response.getData() != null) {
                try {
                    pipedOutputStream.write(OBJECTMAPPER.writeValueAsBytes(response.getData()));
                    pipedOutputStream.flush();
                } catch (Exception e) {

                }
            }
            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ORDER, "ETH-BTC", "KCS-BTC");
        }, "ETH-BTC", "KCS-BTC");

        Runnable runnable = () -> {
            while (placeOrderSwitch) {
                placeOrderAndCancelOrder();
            }
        };
        new Thread(runnable).start();

        byte[] bytes = new byte[1024];
        pipedInputStream.read(bytes);
        assertThat(bytes.length, Matchers.greaterThan(0));
        placeOrderSwitch = false;
    }

    @Test
    public void onAccountBalance() throws Exception {
        kucoinPrivateWSClient.onAccountBalance(response -> {
            if (response.getData() != null) {
                try {
                    pipedOutputStream.write(OBJECTMAPPER.writeValueAsBytes(response.getData()));
                    pipedOutputStream.flush();
                } catch (Exception e) {

                }
            }
            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ACCOUNT);
        });

        Runnable runnable = () -> {
            while (innerTransferSwitch) {
                innerTransfer();
            }
        };
        new Thread(runnable).start();

        byte[] bytes = new byte[1024];
        pipedInputStream.read(bytes);
        assertThat(bytes.length, Matchers.greaterThan(0));
        innerTransferSwitch = false;
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