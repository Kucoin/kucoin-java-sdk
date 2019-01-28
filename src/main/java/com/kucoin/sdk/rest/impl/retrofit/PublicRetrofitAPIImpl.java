/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.impl.retrofit;

import com.kucoin.sdk.factory.RetrofitFactory;

import java.lang.reflect.ParameterizedType;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public class PublicRetrofitAPIImpl<T> extends AbstractRetrofitAPIImpl<T> {

    private volatile T apiImpl;

    @Override
    public T getAPIImpl() {
        if (apiImpl == null) {
            synchronized (getClass()) {
                if (apiImpl == null) {
                    Class<T> tClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                            .getActualTypeArguments()[0];
                    apiImpl = RetrofitFactory.getPublicRetorfit(baseUrl).create(tClass);
                }
            }
        }
        return apiImpl;
    }

}
