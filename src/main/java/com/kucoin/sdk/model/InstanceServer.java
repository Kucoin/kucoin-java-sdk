/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.model;

import lombok.Data;

/**
 * Created by chenshiwei on 2019/1/15.
 */
@Data
public class InstanceServer {

    private long pingInterval;

    private String endpoint;

    private String protocol;

    private boolean encrypt;

    private long pingTimeout;
}
