/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.impl;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinPublicWSClient;
import com.kucoin.sdk.constants.APIConstants;
import com.kucoin.sdk.factory.HttpClientFactory;
import com.kucoin.sdk.model.enums.PublicChannelEnum;
import com.kucoin.sdk.rest.adapter.WebsocketPublicAPIAdaptor;
import com.kucoin.sdk.rest.interfaces.WebsocketPublicAPI;
import com.kucoin.sdk.rest.response.WebsocketTokenResponse;
import com.kucoin.sdk.websocket.ChooseServerStrategy;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.event.*;
import com.kucoin.sdk.websocket.impl.BaseWebsocketImpl;
import com.kucoin.sdk.websocket.listener.KucoinPublicWebsocketListener;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by chenshiwei on 2019/1/17.
 */
public class KucoinPublicWSClientImpl extends BaseWebsocketImpl implements KucoinPublicWSClient {

    private final WebsocketPublicAPI websocketPublicAPI;
    private final KucoinPublicWebsocketListener listener;

    public KucoinPublicWSClientImpl(KucoinClientBuilder kucoinClientBuilder) {
        this(
                HttpClientFactory.getPublicClient(),
                new KucoinPublicWebsocketListener(),
                kucoinClientBuilder.getChooseServerStrategy(),
                new WebsocketPublicAPIAdaptor(kucoinClientBuilder.getBaseUrl()));
    }

    public KucoinPublicWSClientImpl(KucoinClientBuilder kucoinClientBuilder,
                                    KucoinAPICallback connectedCallback, KucoinAPICallback disconnectedCallback) {
        this(
                HttpClientFactory.getPublicClient(),
                new KucoinPublicWebsocketListener(connectedCallback, disconnectedCallback),
                kucoinClientBuilder.getChooseServerStrategy(),
                new WebsocketPublicAPIAdaptor(kucoinClientBuilder.getBaseUrl()));
    }

    private KucoinPublicWSClientImpl(OkHttpClient client,
                                     KucoinPublicWebsocketListener listener,
                                     ChooseServerStrategy chooseServerStrategy,
                                     WebsocketPublicAPI websocketPublicAPI) {
        super(client, listener, chooseServerStrategy);
        this.listener = listener;
        this.websocketPublicAPI = websocketPublicAPI;
    }

    @Override
    protected WebsocketTokenResponse requestToken() throws IOException {
        return this.websocketPublicAPI.getPublicToken();
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
    public String onLevel2Data(int depth, KucoinAPICallback<KucoinEvent<Level2Event>> callback, String... symbols) {
        String topic = null;
        if (depth == 5) {
            if (callback != null) {
                this.listener.setLevel2Depth5Callback(callback);
            }
            topic = APIConstants.API_DEPTH5_LEVEL2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        } else if (depth == 50) {
            if (callback != null) {
                this.listener.setLevel2Depth50Callback(callback);
            }
            topic = APIConstants.API_DEPTH50_LEVEL2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        }
        if (topic == null) {
            return null;
        }

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
    public String onLevel3Data_V2(KucoinAPICallback<KucoinEvent<Level3Event>> callback, String... symbols) {
        if (callback != null) {
            this.listener.setLevel3V2Callback(callback);
        }
        String topic = APIConstants.API_LEVEL3_V2_TOPIC_PREFIX + Arrays.stream(symbols).collect(Collectors.joining(","));
        return subscribe(topic, false, true);
    }

    @Override
    @Deprecated
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

    @Override
    public String onSnapshot(KucoinAPICallback<KucoinEvent<SnapshotEvent>> callback, String target) {
        if (callback != null) {
            this.listener.setSnapshotCallback(callback);
        }
        String topic = APIConstants.API_SNAPSHOT_PREFIX + target;
        return subscribe(topic, false, true);
    }


}
