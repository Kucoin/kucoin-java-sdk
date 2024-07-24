/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.impl;

import com.fasterxml.jackson.core.type.TypeReference;
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
            this.listener.getCallbackMap().put(APIConstants.API_TICKER_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_TICKER_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<TickerChangeEvent>>() {});
        }
        String topic = APIConstants.API_TICKER_TOPIC_PREFIX + String.join(",", symbols);
        return subscribe(topic, false, true);
    }

    @Override
    public String onCandles(KucoinAPICallback<KucoinEvent<CandlesEvent>> callback, String symbolAndType) {
        if (callback != null) {
            this.listener.getCallbackMap().put(APIConstants.API_CANDLES_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_CANDLES_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<CandlesEvent>>() {});
        }
        String topic = APIConstants.API_CANDLES_TOPIC_PREFIX + symbolAndType;
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel2Data(KucoinAPICallback<KucoinEvent<Level2ChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.getCallbackMap().put(APIConstants.API_LEVEL2_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_LEVEL2_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<Level2ChangeEvent>>() {
            });
        }
        String topic = APIConstants.API_LEVEL2_TOPIC_PREFIX + String.join(",", symbols);
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel2Data(int depth, KucoinAPICallback<KucoinEvent<Level2Event>> callback, String... symbols) {
        String topic = null;
        if (depth == 5) {
            if (callback != null) {
                this.listener.getCallbackMap().put(APIConstants.API_DEPTH5_LEVEL2_TOPIC_PREFIX, callback);
                this.listener.getTypeReferenceMap().put(APIConstants.API_DEPTH5_LEVEL2_TOPIC_PREFIX,
                        new TypeReference<KucoinEvent<Level2Event>>() {});
            }
            topic = APIConstants.API_DEPTH5_LEVEL2_TOPIC_PREFIX + String.join(",", symbols);
        } else if (depth == 50) {
            if (callback != null) {
                this.listener.getCallbackMap().put(APIConstants.API_DEPTH50_LEVEL2_TOPIC_PREFIX, callback);
                this.listener.getTypeReferenceMap().put(APIConstants.API_DEPTH50_LEVEL2_TOPIC_PREFIX,
                        new TypeReference<KucoinEvent<Level2Event>>() {});
            }
            topic = APIConstants.API_DEPTH50_LEVEL2_TOPIC_PREFIX + String.join(",", symbols);
        }
        if (topic == null) {
            return null;
        }

        return subscribe(topic, false, true);
    }

    @Override
    public String onMatchExecutionData(KucoinAPICallback<KucoinEvent<MatchExcutionChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.getCallbackMap().put(APIConstants.API_MATCH_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_MATCH_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<MatchExcutionChangeEvent>>() {});
        }
        String topic = APIConstants.API_MATCH_TOPIC_PREFIX + String.join(",", symbols);
        return subscribe(topic, false, true);
    }

    @Override
    public String onLevel3Data_V2(KucoinAPICallback<KucoinEvent<Level3Event>> callback, String... symbols) {
        if (callback != null) {
            this.listener.getCallbackMap().put(APIConstants.API_LEVEL3_V2_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_LEVEL3_V2_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<Level3Event>>() {});
        }
        String topic = APIConstants.API_LEVEL3_V2_TOPIC_PREFIX + String.join(",", symbols);
        return subscribe(topic, false, true);
    }

    @Override
    @Deprecated
    public String onLevel3Data(KucoinAPICallback<KucoinEvent<Level3ChangeEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.getCallbackMap().put(APIConstants.API_LEVEL3_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_LEVEL3_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<Level3ChangeEvent>>() {});
        }
        String topic = APIConstants.API_LEVEL3_TOPIC_PREFIX + String.join(",", symbols);
        return subscribe(topic, false, true);
    }

    @Override
    public String ping(String requestId) {
        return super.ping(requestId);
    }

    @Override
    public String unsubscribe(PublicChannelEnum channelEnum, String... symbols) {
        return super.unsubscribe(channelEnum.getTopicPrefix() + String.join(",", symbols),
                false, true);
    }

    @Override
    public String onSnapshot(KucoinAPICallback<KucoinEvent<SnapshotEvent>> callback, String target) {
        if (callback != null) {
            this.listener.getCallbackMap().put(APIConstants.API_SNAPSHOT_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_SNAPSHOT_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<SnapshotEvent>>() {});
        }
        String topic = APIConstants.API_SNAPSHOT_TOPIC_PREFIX + target;
        return subscribe(topic, false, true);
    }

    @Override
    public String onIndicatorIndex(KucoinAPICallback<KucoinEvent<IndicatorEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.getCallbackMap().put(APIConstants.API_INDICATOR_INDEX_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_INDICATOR_INDEX_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<IndicatorEvent>>() {});
        }
        String topic = APIConstants.API_INDICATOR_INDEX_TOPIC_PREFIX + String.join(",", symbols);
        return subscribe(topic, false, true);
    }

    @Override
    public String onIndicatorMarkPrice(KucoinAPICallback<KucoinEvent<IndicatorEvent>> callback, String... symbols) {
        if (callback != null) {
            this.listener.getCallbackMap().put(APIConstants.API_INDICATOR_MARKPRICE_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_INDICATOR_MARKPRICE_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<IndicatorEvent>>() {});
        }
        String topic = APIConstants.API_INDICATOR_MARKPRICE_TOPIC_PREFIX + String.join(",", symbols);
        return subscribe(topic, false, true);
    }

    @Override
    @Deprecated
    public String onMarginFundingBook(KucoinAPICallback<KucoinEvent<FundingBookEvent>> callback, String... currency) {
        if (callback != null) {
            this.listener.getCallbackMap().put(APIConstants.API_MARGIN_FUNDINGBOOK_TOPIC_PREFIX, callback);
            this.listener.getTypeReferenceMap().put(APIConstants.API_MARGIN_FUNDINGBOOK_TOPIC_PREFIX,
                    new TypeReference<KucoinEvent<FundingBookEvent>>() {});
        }
        String topic = APIConstants.API_MARGIN_FUNDINGBOOK_TOPIC_PREFIX + String.join(",", currency);
        return subscribe(topic, false, true);
    }

}
