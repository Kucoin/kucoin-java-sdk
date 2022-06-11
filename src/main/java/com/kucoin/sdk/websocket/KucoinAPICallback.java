/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.websocket;

import com.kucoin.sdk.exception.KucoinApiException;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface KucoinAPICallback<T> {

    void onResponse(T response) throws KucoinApiException;
    
    void onFailure(Throwable cause);

}
