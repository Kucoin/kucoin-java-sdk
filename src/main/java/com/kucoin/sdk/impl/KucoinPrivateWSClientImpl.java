package com.kucoin.sdk.impl;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinPrivateWSClient;
import com.kucoin.sdk.constants.APIConstants;
import com.kucoin.sdk.factory.HttpClientFactory;
import com.kucoin.sdk.model.enums.PrivateChannelEnum;
import com.kucoin.sdk.rest.adapter.WebsocketPrivateAPIAdaptor;
import com.kucoin.sdk.rest.interfaces.WebsocketPrivateAPI;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.event.AccountChangeEvent;
import com.kucoin.sdk.websocket.event.KucoinEvent;
import com.kucoin.sdk.websocket.event.OrderActivateEvent;
import com.kucoin.sdk.websocket.impl.BaseWebsocketImpl;
import com.kucoin.sdk.websocket.listener.KucoinPrivateWebsocketListener;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class KucoinPrivateWSClientImpl extends BaseWebsocketImpl implements KucoinPrivateWSClient {

    private WebsocketPrivateAPI websocketPrivateAPI;

    private KucoinPrivateWebsocketListener listener;

    public KucoinPrivateWSClientImpl(KucoinClientBuilder kucoinClientBuilder) {
        this.chooseServerStrategy = kucoinClientBuilder.getChooseServerStrategy();
        this.client = HttpClientFactory.getPublicClient();
        this.websocketPrivateAPI = new WebsocketPrivateAPIAdaptor(kucoinClientBuilder.getBaseUrl(),
                kucoinClientBuilder.getApiKey(), kucoinClientBuilder.getSecret(), kucoinClientBuilder.getPassPhrase());
        this.websocketToken = this.websocketPrivateAPI.getPrivateToken();
        this.listener = new KucoinPrivateWebsocketListener();
        this.webSocket = createNewWebSocket(this.listener);
    }

    @Override
    public String onOrderActivate(KucoinAPICallback<KucoinEvent<OrderActivateEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.setOrderActivateCallback(callback);
        }
        String topic = APIConstants.API_ACTIVATE_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, true, true);
    }

    @Override
    public String onAccountBalance(KucoinAPICallback<KucoinEvent<AccountChangeEvent>> callback) {
        if (callback != null) {
            this.listener.setAccountChangeCallback(callback);
        }
        return subscribe(APIConstants.API_BALANCE_TOPIC_PREFIX, true, true);
    }

    @Override
    public String ping(String requestId) {
        return super.ping(requestId);
    }

    @Override
    public String unsubscribe(PrivateChannelEnum channelEnum, String... symbols) {
        return super.unsubscribe(channelEnum.getTopicPrefix() + Arrays.stream(symbols).collect(Collectors.joining(",")),
                true, true);
    }
}
