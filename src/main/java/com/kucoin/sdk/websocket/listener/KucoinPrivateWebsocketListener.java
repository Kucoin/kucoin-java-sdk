/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket.listener;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kucoin.sdk.constants.APIConstants;
import com.kucoin.sdk.exception.KucoinApiException;
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

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    {
        OBJECTMAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private KucoinAPICallback<KucoinEvent<OrderActivateEvent>> orderActivateCallback = new PrintCallback();
    private KucoinAPICallback<KucoinEvent<AccountChangeEvent>> accountChangeCallback = new PrintCallback();

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println("web socket open");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        JsonNode jsonObject = tree(text);
        String type = jsonObject.get("type").asText();
        String topic = jsonObject.get("topic").asText();
        if (!type.equals("message")) {
            System.out.println(text);
        } else if (topic.contains(APIConstants.API_ACTIVATE_TOPIC_PREFIX)) {
            KucoinEvent kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<OrderActivateEvent>>() {});
            orderActivateCallback.onResponse(kucoinEvent);
        } else if (topic.contains(APIConstants.API_BALANCE_TOPIC_PREFIX)) {
            KucoinEvent kucoinEvent = deserialize(text, new TypeReference<KucoinEvent<AccountChangeEvent>>() {});
            accountChangeCallback.onResponse(kucoinEvent);
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        throw new KucoinApiException(t.getMessage());
    }

    private JsonNode tree(String text) {
      try {
        return OBJECTMAPPER.readTree(text);
      } catch (IOException e) {
        throw new RuntimeException("Failed to deserialise message: " + text, e);
      }
    }

    private <T> T deserialize(String text, TypeReference<T> typeReference) {
      try {
        return OBJECTMAPPER.readValue(text, typeReference);
      } catch (IOException e) {
        throw new RuntimeException("Failed to deserialise message: " + text, e);
      }
    }
}
