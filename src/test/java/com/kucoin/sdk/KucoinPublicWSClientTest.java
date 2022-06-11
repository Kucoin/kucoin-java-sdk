/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import com.kucoin.sdk.model.enums.PublicChannelEnum;
import com.kucoin.sdk.rest.request.OrderCreateApiRequest;
import com.kucoin.sdk.rest.response.OrderCreateResponse;
import com.kucoin.sdk.rest.response.TickerResponse;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.event.*;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by chenshiwei on 2019/1/22.
 * <p>
 * Run with -Dorg.slf4j.simpleLogger.defaultLogLevel=debug for debug logging
 */
public class KucoinPublicWSClientTest {

    private static KucoinPublicWSClient kucoinPublicWSClient;
    private static KucoinRestClient kucoinRestClient;


    @BeforeClass
    public static void setupClass() throws Exception {
        kucoinPublicWSClient = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
                .buildPublicWSClient();
        kucoinRestClient = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com")
                .withApiKey("5c42a37bef83c73aa68e43c4", "7df80b16-1b95-4739-9b03-3d987599c332", "asd123456")
                // Version number of api-key
                .withApiKeyVersion(ApiKeyVersionEnum.V2.getVersion())
                .buildRestClient();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        kucoinPublicWSClient.close();
    }

    @Test
    public void onTicker() throws Exception {
        AtomicReference<TickerResponse> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onTicker(new KucoinAPICallback<KucoinEvent<TickerChangeEvent>>() {

			@Override
			public void onResponse(KucoinEvent<TickerChangeEvent> response) throws KucoinApiException {
	            event.set(response.getData());
	            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.TICKER, "ETH-BTC", "KCS-BTC");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC", "KCS-BTC");
        
        /*
        kucoinPublicWSClient.onTicker(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.TICKER, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");
        */

        // Make some actual executions
        buyAndSell();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void onLevel2Data() throws Exception {
        AtomicReference<Level2ChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onLevel2Data(new KucoinAPICallback<KucoinEvent<Level2ChangeEvent>>() {

			@Override
			public void onResponse(KucoinEvent<Level2ChangeEvent> response) throws KucoinApiException {
	            event.set(response.getData());
	            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL2, "ETH-BTC", "KCS-BTC");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC", "KCS-BTC");
        
        /*
        kucoinPublicWSClient.onLevel2Data(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL2, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");
        */

        // Trigger a market change
        placeOrderAndCancelOrder();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void onLevel2Depth5Data() throws Exception {
        AtomicReference<Level2Event> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onLevel2Data(5, new KucoinAPICallback<KucoinEvent<Level2Event>>() {

			@Override
			public void onResponse(KucoinEvent<Level2Event> response) throws KucoinApiException {
	            event.set(response.getData());
	            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL2_DEPTH5, "ETH-BTC", "BTC-USDT");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC", "BTC-USDT");
        
        /*
        kucoinPublicWSClient.onLevel2Data(5, response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL2_DEPTH5, "ETH-BTC", "BTC-USDT");
            gotEvent.countDown();
        }, "ETH-BTC", "BTC-USDT");
        */

        // Trigger a market change
        placeOrderAndCancelOrder();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void onLevel2Depth50Data() throws Exception {
        AtomicReference<Level2Event> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onLevel2Data(50,  new KucoinAPICallback<KucoinEvent<Level2Event>>() {

			@Override
			public void onResponse(KucoinEvent<Level2Event> response) throws KucoinApiException {
	            event.set(response.getData());
	            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL2_DEPTH50, "ETH-BTC", "BTC-USDT");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        }, "ETH-BTC", "BTC-USDT");
        
        /*
        kucoinPublicWSClient.onLevel2Data(50, response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL2_DEPTH50, "ETH-BTC", "BTC-USDT");
            gotEvent.countDown();
        }, "ETH-BTC", "BTC-USDT");
        */

        // Trigger a market change
        placeOrderAndCancelOrder();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void onMatchExecutionData() throws Exception {
        AtomicReference<MatchExcutionChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onMatchExecutionData(new KucoinAPICallback<KucoinEvent<MatchExcutionChangeEvent>>() {

			@Override
			public void onResponse(KucoinEvent<MatchExcutionChangeEvent> response) throws KucoinApiException {
	            event.set(response.getData());
	            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.MATCH, "ETH-BTC", "KCS-BTC");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC", "KCS-BTC");
        
        /*
        kucoinPublicWSClient.onMatchExecutionData(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.MATCH, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");
		*/

        // Make some actual executions
        buyAndSell();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void onLevel3Data() throws Exception {
        AtomicReference<Level3ChangeEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onLevel3Data(new KucoinAPICallback<KucoinEvent<Level3ChangeEvent>>() {

			@Override
			public void onResponse(KucoinEvent<Level3ChangeEvent> response) throws KucoinApiException {
	            event.set(response.getData());
	            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL3, "ETH-BTC", "KCS-BTC");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC", "KCS-BTC");
        
        /*
        kucoinPublicWSClient.onLevel3Data(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL3, "ETH-BTC", "KCS-BTC");
            gotEvent.countDown();
        }, "ETH-BTC", "KCS-BTC");
        */

        // Trigger a market change
        placeOrderAndCancelOrder();

        assertTrue(gotEvent.await(20, TimeUnit.SECONDS));
        System.out.println(event.get());
    }

    @Test
    public void onLevel3_V2Data() throws Exception {
        AtomicReference<Level3Event> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);

        kucoinPublicWSClient.onLevel3Data_V2(new KucoinAPICallback<KucoinEvent<Level3Event>>() {

			@Override
			public void onResponse(KucoinEvent<Level3Event> response) throws KucoinApiException {
	            event.set(response.getData());
	            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL3_V2, "ETH-BTC", "BTC-USDT");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC", "BTC-USDT");
        
        /*
        kucoinPublicWSClient.onLevel3Data_V2(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.LEVEL3_V2, "ETH-BTC", "BTC-USDT");
            gotEvent.countDown();
        }, "ETH-BTC", "BTC-USDT");
        */

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

    @Test
    public void onSnapshot() throws Exception {
        AtomicReference<SnapshotEvent> event = new AtomicReference<>();
        CountDownLatch gotEvent = new CountDownLatch(1);
        
        kucoinPublicWSClient.onSnapshot(new KucoinAPICallback<KucoinEvent<SnapshotEvent>>() {

			@Override
			public void onResponse(KucoinEvent<SnapshotEvent> response) throws KucoinApiException {
	            event.set(response.getData());
	            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.SNAPSHOT, "ETH-BTC");
	            gotEvent.countDown();
			}

			@Override
			public void onFailure(Throwable cause) {
				System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());

				//reinitializeWSConnection();	//implement this method
			}
        	
        }, "ETH-BTC");
        
        /*
        kucoinPublicWSClient.onSnapshot(response -> {
            event.set(response.getData());
            kucoinPublicWSClient.unsubscribe(PublicChannelEnum.SNAPSHOT, "ETH-BTC");
            gotEvent.countDown();
        }, "ETH-BTC");
        */
        
        placeOrderAndCancelOrder();
        gotEvent.await(20, TimeUnit.SECONDS);
        System.out.println(event.get());
    }

    private void placeOrderAndCancelOrder() throws InterruptedException, IOException {
        Thread.sleep(1000);
        OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(0.000001)).size(BigDecimal.ONE).side("buy")
                .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
        OrderCreateResponse order = kucoinRestClient.orderAPI().createOrder(request);
        kucoinRestClient.orderAPI().cancelOrder(order.getOrderId());
    }

    private void buyAndSell() throws InterruptedException, IOException {
        Thread.sleep(1000);
        OrderCreateApiRequest request1 = OrderCreateApiRequest.builder()
                .size(new BigDecimal("0.001"))
                .side("buy")
                .symbol("ETH-BTC")
                .type("market")
                .clientOid(UUID.randomUUID().toString())
                .build();
        kucoinRestClient.orderAPI().createOrder(request1);
        OrderCreateApiRequest request2 = OrderCreateApiRequest.builder()
                .size(new BigDecimal("0.001"))
                .side("sell")
                .symbol("ETH-BTC")
                .type("market")
                .clientOid(UUID.randomUUID().toString())
                .build();
        kucoinRestClient.orderAPI().createOrder(request2);
    }
}