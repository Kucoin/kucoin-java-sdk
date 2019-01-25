package com.kucoin.sdk.websocket.impl;

import com.alibaba.fastjson.JSONObject;
import com.kucoin.sdk.model.InstanceServer;
import com.kucoin.sdk.rest.response.WebsocketTokenResponse;
import com.kucoin.sdk.websocket.ChooseServerStrategy;
import com.kucoin.sdk.websocket.event.KucoinEvent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.Closeable;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public class BaseWebsocketImpl implements Closeable {

    protected ChooseServerStrategy chooseServerStrategy;

    protected OkHttpClient client;

    protected InstanceServer instanceServer;

    protected WebsocketTokenResponse websocketToken;

    protected WebSocket webSocket;

    protected WebSocket createNewWebSocket(WebSocketListener listener) {
        this.instanceServer = chooseServerStrategy.choose(websocketToken.getInstanceServers());
        String streamingUrl = String.format("%s", instanceServer.getEndpoint()
                + "?token=" + this.websocketToken.getToken());
        Request request = new Request.Builder().url(streamingUrl).build();
        return client.newWebSocket(request, listener);
    }

    protected String ping(String requestId) {
        KucoinEvent ping = new KucoinEvent();
        ping.setId(requestId);
        ping.setType("ping");
        if (webSocket.send(JSONObject.toJSONString(ping))) {
            return requestId;
        }
        return null;
    }

    protected String subscribe(String topic, boolean privateChannel, boolean response) {
        String uuid = UUID.randomUUID().toString();
        KucoinEvent subscribe = new KucoinEvent();
        subscribe.setId(uuid);
        subscribe.setType("subscribe");
        subscribe.setTopic(topic);
        subscribe.setPrivateChannel(privateChannel);
        subscribe.setResponse(response);
        if (webSocket.send(JSONObject.toJSONString(subscribe))) {
            return uuid;
        }
        return null;
    }

    protected String unsubscribe(String topic, boolean privateChannel, boolean response) {
        String uuid = UUID.randomUUID().toString();
        KucoinEvent subscribe = new KucoinEvent();
        subscribe.setId(uuid);
        subscribe.setType("unsubscribe");
        subscribe.setTopic(topic);
        subscribe.setPrivateChannel(privateChannel);
        subscribe.setResponse(response);
        if (webSocket.send(JSONObject.toJSONString(subscribe))) {
            return uuid;
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        System.out.println("Web Socket Close");
        client.dispatcher().executorService().shutdown();
    }

    public InstanceServer getInstanceServer() {
        return instanceServer;
    }
}
