package com.kucoin.sdk.websocket;

import com.kucoin.sdk.exception.KucoinApiException;

/**
 * Created by chenshiwei on 2019/1/19.
 */
public class PrintCallback<T> implements KucoinAPICallback<T> {

    @Override
    public void onResponse(T response) throws KucoinApiException {
        System.out.println(response);
    }

}
