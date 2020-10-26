/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket.impl;

import java.io.Closeable;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kucoin.sdk.KucoinObjectMapper;
import com.kucoin.sdk.model.InstanceServer;
import com.kucoin.sdk.rest.response.WebsocketTokenResponse;
import com.kucoin.sdk.websocket.ChooseServerStrategy;
import com.kucoin.sdk.websocket.event.KucoinEvent;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public abstract class BaseWebsocketImpl implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseWebsocketImpl.class);

    private final ChooseServerStrategy chooseServerStrategy;
    private final OkHttpClient client;
    private final WebSocketListener listener;

    private WebSocket webSocket;

    protected BaseWebsocketImpl(OkHttpClient client, WebSocketListener listener, ChooseServerStrategy chooseServerStrategy) {
      this.client = client;
      this.listener = listener;
      this.chooseServerStrategy = chooseServerStrategy;
    }

    public void connect() throws IOException {
      this.webSocket = createNewWebSocket();
    }

    protected abstract WebsocketTokenResponse requestToken() throws IOException;

    private WebSocket createNewWebSocket() throws IOException {
        WebsocketTokenResponse websocketToken = requestToken();
        InstanceServer instanceServer = chooseServerStrategy.choose(websocketToken.getInstanceServers());
        String streamingUrl = String.format("%s", instanceServer.getEndpoint()
                + "?token=" + websocketToken.getToken());
        Request request = new Request.Builder().url(streamingUrl).build();
        return client.newWebSocket(request, listener);
    }

    protected String ping(String requestId) {
        KucoinEvent<Void> ping = new KucoinEvent<>();
        ping.setId(requestId);
        ping.setType("ping");
        if (webSocket.send(serialize(ping))) {
            return requestId;
        }
        return null;
    }

    protected String subscribe(String topic, boolean privateChannel, boolean response) {
        String uuid = UUID.randomUUID().toString();
        KucoinEvent<Void> subscribe = new KucoinEvent<>();
        subscribe.setId(uuid);
        subscribe.setType("subscribe");
        subscribe.setTopic(topic);
        subscribe.setPrivateChannel(privateChannel);
        subscribe.setResponse(response);
        if (webSocket.send(serialize(subscribe))) {
            return uuid;
        }
        return null;
    }

    protected String unsubscribe(String topic, boolean privateChannel, boolean response) {
        String uuid = UUID.randomUUID().toString();
        KucoinEvent<Void> subscribe = new KucoinEvent<>();
        subscribe.setId(uuid);
        subscribe.setType("unsubscribe");
        subscribe.setTopic(topic);
        subscribe.setPrivateChannel(privateChannel);
        subscribe.setResponse(response);
        if (webSocket.send(serialize(subscribe))) {
            return uuid;
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        LOGGER.debug("Web Socket Close");
        client.dispatcher().executorService().shutdown();
    }

    private String serialize(Object o) {
      try {
        return KucoinObjectMapper.INSTANCE.writeValueAsString(o);
      } catch (JsonProcessingException e) {
        throw new RuntimeException("Failure serializing object", e);
      }
    }
}