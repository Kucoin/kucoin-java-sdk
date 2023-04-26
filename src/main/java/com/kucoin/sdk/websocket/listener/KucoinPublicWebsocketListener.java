/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.kucoin.sdk.KucoinObjectMapper;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.PrintCallback;
import com.kucoin.sdk.websocket.event.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.kucoin.sdk.constants.APIConstants.*;

/**
 * Created by chenshiwei on 2019/1/10.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KucoinPublicWebsocketListener extends WebSocketListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KucoinPublicWebsocketListener.class);

    private KucoinAPICallback<KucoinEvent<TickerChangeEvent>> tickerCallback = new PrintCallback<>();
    private KucoinAPICallback<KucoinEvent<Level2ChangeEvent>> level2Callback = new PrintCallback<>();
    private KucoinAPICallback<KucoinEvent<Level2Event>> level2Depth5Callback = new PrintCallback<>();
    private KucoinAPICallback<KucoinEvent<Level2Event>> level2Depth50Callback = new PrintCallback<>();
    private KucoinAPICallback<KucoinEvent<MatchExcutionChangeEvent>> matchDataCallback = new PrintCallback<>();
    private KucoinAPICallback<KucoinEvent<Level3ChangeEvent>> level3Callback = new PrintCallback<>();
    private KucoinAPICallback<KucoinEvent<Level3Event>> level3V2Callback = new PrintCallback<>();
    private KucoinAPICallback<KucoinEvent<SnapshotEvent>> snapshotCallback = new PrintCallback<>();

    private KucoinAPICallback<String> connectedCallback;
    private KucoinAPICallback<String> disConnectedCallback;

    public KucoinPublicWebsocketListener() {}

    public KucoinPublicWebsocketListener(KucoinAPICallback<String> connectedCallback,
                                         KucoinAPICallback<String> disConnectedCallback) {
        this.connectedCallback = connectedCallback;
        this.disConnectedCallback = disConnectedCallback;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        LOGGER.debug("web socket open");
        this.connectedCallback.onResponse(response.message());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        LOGGER.debug("Got message: {}", text);
        JsonNode jsonObject = tree(text);
        LOGGER.debug("Parsed message OK");

        String type = jsonObject.get("type").asText();
        if (!type.equals("message")) {
            LOGGER.debug("Ignoring message type ({})", type);
            return;
        }

        String topic = jsonObject.get("topic").asText();
        if (topic.contains(API_TICKER_TOPIC_PREFIX)) {
            KucoinEvent<TickerChangeEvent> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<TickerChangeEvent>>() {
            });
            tickerCallback.onResponse(kucoinEvent);
        } else if (topic.contains(API_LEVEL2_TOPIC_PREFIX)) {
            KucoinEvent<Level2ChangeEvent> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<Level2ChangeEvent>>() {
            });
            level2Callback.onResponse(kucoinEvent);
        } else if (topic.contains(API_MATCH_TOPIC_PREFIX)) {
            KucoinEvent<MatchExcutionChangeEvent> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<MatchExcutionChangeEvent>>() {
            });
            matchDataCallback.onResponse(kucoinEvent);
        } else if (topic.contains(API_LEVEL3_TOPIC_PREFIX)) {
            KucoinEvent<Level3ChangeEvent> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<Level3ChangeEvent>>() {
            });
            level3Callback.onResponse(kucoinEvent);
        } else if (topic.contains(API_SNAPSHOT_PREFIX)) {
            KucoinEvent<SnapshotEvent> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<SnapshotEvent>>() {
            });
            snapshotCallback.onResponse(kucoinEvent);
        } else if (topic.contains(API_LEVEL3_V2_TOPIC_PREFIX)) {
            KucoinEvent<Level3Event> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<Level3Event>>() {
            });
            level3V2Callback.onResponse(kucoinEvent);
        } else if (topic.contains(API_DEPTH5_LEVEL2_TOPIC_PREFIX)) {
            KucoinEvent<Level2Event> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<Level2Event>>() {
            });
            level2Depth5Callback.onResponse(kucoinEvent);
        } else if (topic.contains(API_DEPTH50_LEVEL2_TOPIC_PREFIX)) {
            KucoinEvent<Level2Event> kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<Level2Event>>() {
            });
            level2Depth50Callback.onResponse(kucoinEvent);
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        LOGGER.error("Error on public socket", t);
        this.disConnectedCallback.onResponse(response.message());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        this.disConnectedCallback.onResponse(reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        this.disConnectedCallback.onResponse(reason);
    }

    private JsonNode tree(String text) {
        try {
            return KucoinObjectMapper.INSTANCE.readTree(text);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialise message: " + text, e);
        }
    }

    private <T> T deserialize(String text, TypeReference<T> typeReference) {
        try {
            return KucoinObjectMapper.INSTANCE.readValue(text, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialise message: " + text, e);
        }
    }
}
