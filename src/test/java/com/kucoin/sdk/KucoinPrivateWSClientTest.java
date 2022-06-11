/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import com.kucoin.sdk.model.enums.PrivateChannelEnum;
import com.kucoin.sdk.rest.request.AccountTransferV2Request;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.request.StopOrderCreateRequest;
import com.kucoin.sdk.rest.response.AccountBalancesResponse;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.event.AccountChangeEvent;
import com.kucoin.sdk.websocket.event.AdvancedOrderEvent;
import com.kucoin.sdk.websocket.event.KucoinEvent;
import com.kucoin.sdk.websocket.event.OrderActivateEvent;
import com.kucoin.sdk.websocket.event.OrderChangeEvent;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by chenshiwei on 2019/1/23.
 * <p>
 * Run with -Dorg.slf4j.simpleLogger.defaultLogLevel=debug for debug logging
 */
public class KucoinPrivateWSClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(KucoinPrivateWSClientTest.class);

    private static KucoinRestClient kucoinRestClient;
    private static KucoinPrivateWSClient kucoinPrivateWSClient;

    @BeforeClass
    public static void setupClass() throws Exception {
        KucoinClientBuilder builder = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
                .withApiKey("5f927beac1cfb50006afcd3c", "943aede3-1dd2-46fe-9654-7df9f275e118", "12121212")
                // Version number of api-key
                .withApiKeyVersion(ApiKeyVersionEnum.V2.getVersion());
        kucoinRestClient = builder.buildRestClient();
        kucoinPrivateWSClient = builder.buildPrivateWSClient();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        kucoinPrivateWSClient.close();
    }

    @Test
    public void onOrderActivate() throws Exception {
        AtomicReference<OrderActivateEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPrivateWSClient.onOrderActivate(new KucoinAPICallback<KucoinEvent<OrderActivateEvent>>() {

			@Override
			public void onResponse(KucoinEvent<OrderActivateEvent> response) throws KucoinApiException {
	            LOGGER.info("Got response");
	            event.set(response.getData());
	            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ORDER, "ETH-BTC", "KCS-BTC");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC", "KCS-BTC");
        
        /*
        kucoinPrivateWSClient.onOrderActivate(response -> {
            LOGGER.info("Got response");
            event.set(response.getData());
            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ORDER, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");
        */

        Thread.sleep(1000);

        new Thread(() -> {
            while (event.get() == null) {
                try {
                    placeOrderAndCancelOrder();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        LOGGER.info("Waiting...");
        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void onOrderChange() throws Exception {
        AtomicReference<OrderChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPrivateWSClient.onOrderChange(new KucoinAPICallback<KucoinEvent<OrderChangeEvent>>() {

			@Override
			public void onResponse(KucoinEvent<OrderChangeEvent> response) throws KucoinApiException {
	            LOGGER.info("Got response");
	            event.set(response.getData());
	            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ORDER_CHANGE, "ETH-BTC", "BTC-USDT");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC", "BTC-USDT");
        
        /*
        kucoinPrivateWSClient.onOrderChange(response -> {
            LOGGER.info("Got response");
            event.set(response.getData());
            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ORDER_CHANGE, "ETH-BTC", "BTC-USDT");
            gotEvent.countDown();
        }, "ETH-BTC", "BTC-USDT");
        */

        Thread.sleep(1000);

        new Thread(() -> {
            while (event.get() == null) {
                try {
                    placeOrderAndCancelOrder();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        LOGGER.info("Waiting...");
        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void onAccountBalance() throws Exception {
        AtomicReference<AccountChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPrivateWSClient.onAccountBalance(new KucoinAPICallback<KucoinEvent<AccountChangeEvent>>() {

			@Override
			public void onResponse(KucoinEvent<AccountChangeEvent> response) throws KucoinApiException {
	            LOGGER.info("Got response");
	            event.set(response.getData());
	            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ACCOUNT);
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        });
        
        /*
        kucoinPrivateWSClient.onAccountBalance(response -> {
            LOGGER.info("Got response");
            event.set(response.getData());
            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ACCOUNT);
            gotEvent.countDown();
        });
        */

        Thread.sleep(1000);

        new Thread(() -> {
            while (event.get() == null) {
                try {
                    innerTransfer2();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

    @Test
    public void onAdvancedOrder() throws Exception {
        AtomicReference<AdvancedOrderEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPrivateWSClient.onAdvancedOrder(new KucoinAPICallback<KucoinEvent<? extends AdvancedOrderEvent>>() {

			@Override
			public void onResponse(KucoinEvent<? extends AdvancedOrderEvent> response) throws KucoinApiException {
	            LOGGER.info("Got response {}", response);
	            event.set(response.getData());
	            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ADVANCED_ORDER, "ETH-BTC");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC");
        
        /*
        kucoinPrivateWSClient.onAdvancedOrder(response -> {
            LOGGER.info("Got response {}", response);
            event.set(response.getData());
            kucoinPrivateWSClient.unsubscribe(PrivateChannelEnum.ADVANCED_ORDER, "ETH-BTC");
            gotEvent.countDown();
        }, "ETH-BTC");
        */

        Thread.sleep(1000);

        new Thread(() -> {
            while (event.get() == null) {
                try {
                    StopOrderCreateRequest request = StopOrderCreateRequest.builder()
                            .price(BigDecimal.valueOf(0.0001)).size(BigDecimal.ONE).side("buy")
                            .stop("loss").stopPrice(BigDecimal.valueOf(0.0002))
                            .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
                    OrderCreateResponse stopOrder = kucoinRestClient.stopOrderAPI().createStopOrder(request);

                    kucoinRestClient.stopOrderAPI().cancelStopOrder(stopOrder.getOrderId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        LOGGER.info("Waiting...");
        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    private void placeOrderAndCancelOrder() throws IOException {
        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy")
                .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
        OrderCreateResponse order = kucoinRestClient.orderAPI().createOrder(request);
        kucoinRestClient.orderAPI().cancelOrder(order.getOrderId());
    }

    private void innerTransfer2() throws IOException {
        List<AccountBalancesResponse> accountBalancesResponses = kucoinRestClient.accountAPI().listAccounts("USDT", null);
        assertThat(accountBalancesResponses.size(), Is.is(2));
        kucoinRestClient.accountAPI().innerTransfer2(new AccountTransferV2Request(String.valueOf(System.currentTimeMillis()),
                "USDT", "trade", "main", new BigDecimal("0.000001")));
    }

}