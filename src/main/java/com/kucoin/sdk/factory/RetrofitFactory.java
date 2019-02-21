/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.factory;

import com.kucoin.sdk.KucoinObjectMapper;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public class RetrofitFactory {

    private static volatile boolean publicInited;
    private static Retrofit publicRetrofit;

    private static volatile boolean authInited;
    private static Retrofit authRetrofit;

    private static final Converter.Factory CONVERTER_FACTORY = JacksonConverterFactory.create(KucoinObjectMapper.INSTANCE);

    public static Retrofit getPublicRetorfit(String baseUrl) {
        if (publicInited)
            return publicRetrofit;
        synchronized (RetrofitFactory.class) {
            if (publicInited)
                return publicRetrofit;
            publicInited = true;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(CONVERTER_FACTORY)
                    .client(HttpClientFactory.getPublicClient())
                    .build();
            publicRetrofit = retrofit;
            return retrofit;
        }
    }

    public static Retrofit getAuthRetorfit(String baseUrl, String apiKey, String secret, String passPhrase) {
        if (authInited)
            return authRetrofit;
        synchronized (RetrofitFactory.class) {
            if (authInited)
                return authRetrofit;
            authInited = true;
            Retrofit retrofit =  new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(CONVERTER_FACTORY)
                    .client(HttpClientFactory.getAuthClient(apiKey, secret, passPhrase))
                    .build();
            authRetrofit = retrofit;
            return retrofit;
        }
    }
}