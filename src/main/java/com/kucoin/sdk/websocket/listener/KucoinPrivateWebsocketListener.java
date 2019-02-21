/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.kucoin.sdk.KucoinObjectMapper;
import com.kucoin.sdk.constants.APIConstants;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.PrintCallback;
import com.kucoin.sdk.websocket.event.AccountChangeEvent;
import com.kucoin.sdk.websocket.event.KucoinEvent;
import com.kucoin.sdk.websocket.event.OrderActivateEvent;

import lombok.Data;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by chenshiwei on 2019/1/19.
 */
@Data
public class KucoinPrivateWebsocketListener extends WebSocketListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KucoinPrivateWebsocketListener.class);

    private KucoinAPICallback<KucoinEvent<OrderActivateEvent>> orderActivateCallback = new PrintCallback();
    private KucoinAPICallback<KucoinEvent<AccountChangeEvent>> accountChangeCallback = new PrintCallback();

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        LOGGER.debug("web socket open");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        LOGGER.debug("Got message: {}", text);
        JsonNode jsonObject = tree(text);
        LOGGER.debug("Parsed message: ", text);

        String type = jsonObject.get("type").asText();
        if (!type.equals("message")) {
            LOGGER.debug("Ignoring message type ({})", type);
            return;
        }

        String topic = jsonObject.get("topic").asText();
        if (topic.contains(APIConstants.API_ACTIVATE_TOPIC_PREFIX)) {
            KucoinEvent kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<OrderActivateEvent>>() {});
            orderActivateCallback.onResponse(kucoinEvent);
        } else if (topic.contains(APIConstants.API_BALANCE_TOPIC_PREFIX)) {
            KucoinEvent kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<AccountChangeEvent>>() {});
            accountChangeCallback.onResponse(kucoinEvent);
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        LOGGER.error("Error on private socket", t);
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
