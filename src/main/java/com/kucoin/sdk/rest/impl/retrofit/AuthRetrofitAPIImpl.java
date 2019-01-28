package com.kucoin.sdk.rest.impl.retrofit;

import com.kucoin.sdk.factory.RetrofitFactory;

import java.lang.reflect.ParameterizedType;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public class AuthRetrofitAPIImpl<T> extends AbstractRetrofitAPIImpl<T> {

    private volatile T apiImpl;

    @Override
    public T getAPIImpl() {
        if (apiImpl == null) {
            synchronized (getClass()) {
                if (apiImpl == null) {
                    Class<T> tClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                            .getActualTypeArguments()[0];
                    apiImpl = RetrofitFactory.getAuthRetorfit(baseUrl, apiKey, secret, passPhrase)
                            .create(tClass);
                }
            }
        }
        return apiImpl;
    }
}
