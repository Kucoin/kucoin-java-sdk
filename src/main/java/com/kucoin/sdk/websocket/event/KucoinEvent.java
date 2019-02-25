/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket.event;

import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/10.
 */
@Data
public class KucoinEvent<T> {

    private String id;

    private String type;

    private String topic;

    private Boolean privateChannel;

    private Boolean response;

    private T data;

    private String subject;

}
