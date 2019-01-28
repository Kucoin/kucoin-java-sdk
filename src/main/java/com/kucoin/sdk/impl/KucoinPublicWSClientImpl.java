/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.impl;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinPublicWSClient;
import com.kucoin.sdk.rest.adapter.WebsocketPublicAPIAdaptor;
import com.kucoin.sdk.constants.APIConstants;
import com.kucoin.sdk.factory.HttpClientFactory;
import com.kucoin.sdk.model.enums.PublicChannelEnum;
import com.kucoin.sdk.rest.interfaces.WebsocketPublicAPI;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.event.KucoinEvent;
import com.kucoin.sdk.websocket.event.Level2ChangeEvent;
import com.kucoin.sdk.websocket.event.Level3ChangeEvent;
import com.kucoin.sdk.websocket.event.MatchExcutionChangeEvent;
import com.kucoin.sdk.websocket.event.TickerChangeEvent;
import com.kucoin.sdk.websocket.impl.BaseWebsocketImpl;
import com.kucoin.sdk.websocket.listener.KucoinPublicWebsocketListener;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by chenshiwei on 2019/1/17.
 */
public class KucoinPublicWSClientImpl extends BaseWebsocketImpl implements KucoinPublicWSClient {

    private WebsocketPublicAPI websocketPublicAPI;

    private KucoinPublicWebsocketListener listener;

    public KucoinPublicWSClientImpl(KucoinClientBuilder kucoinClientBuilder) {
        this.chooseServerStrategy = kucoinClientBuilder.getChooseServerStrategy();
        this.client = HttpClientFactory.getPublicClient();
        this.websocketPublicAPI = new WebsocketPublicAPIAdaptor(kucoinClientBuilder.getBaseUrl());
        this.websocketToken = this.websocketPublicAPI.getPublicToken();
        this.listener = new KucoinPublicWebsocketListener();
        this.webSocket = createNewWebSocket(this.listener);
    }

    @Override
    public String onTicker(KucoinAPICallback<KucoinEvent<TickerChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.setTickerCallback(callback);
        }
        String topic = APIConstants.API_TICKER_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel2Data(KucoinAPICallback<KucoinEvent<Level2ChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.setLevel2Callback(callback);
        }
        String topic = APIConstants.API_LEVEL2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onMatchExecutionData(KucoinAPICallback<KucoinEvent<MatchExcutionChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.setMatchDataCallback(callback);
        }
        String topic = APIConstants.API_MATCH_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel3Data(KucoinAPICallback<KucoinEvent<Level3ChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.setLevel3Callback(callback);
        }
        String topic = APIConstants.API_LEVEL3_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    public String ping(String requestId) {
        return super.ping(requestId);
    }

    @Override
    public String unsubscribe(PublicChannelEnum channelEnum, String... symbols) {
        return super.unsubscribe(channelEnum.getTopicPrefix() + Arrays.stream(symbols).collect(Collectors.joining(",")),
                false, true);
    }

}
