/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket.listener;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
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

    private KucoinAPICallback<KucoinEvent<OrderActivateEvent>> orderActivateCallback = new PrintCallback();
    private KucoinAPICallback<KucoinEvent<AccountChangeEvent>> accountChangeCallback = new PrintCallback();

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println("web socket open");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        JSONObject jsonObject = JSONObject.parseObject(text);
        String type = jsonObject.getString("type");
        String topic = jsonObject.getString("topic");
        if (!type.equals("message")) {
            System.out.println(text);
        } else if (topic.contains(APIConstants.API_ACTIVATE_TOPIC_PREFIX)) {
            KucoinEvent kucoinEvent = jsonObject.toJavaObject(new TypeReference<KucoinEvent<OrderActivateEvent>>() {});
            orderActivateCallback.onResponse(kucoinEvent);
        } else if (topic.contains(APIConstants.API_BALANCE_TOPIC_PREFIX)) {
            KucoinEvent kucoinEvent = jsonObject.toJavaObject(new TypeReference<KucoinEvent<AccountChangeEvent>>() {});
            accountChangeCallback.onResponse(kucoinEvent);
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        throw new KucoinApiException(t.getMessage());
    }
}
